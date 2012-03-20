/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.subscriptions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.ubc.magic.thingbroker.events.Event;
import ca.ubc.magic.thingbroker.subscriptions.Subscription;
import ca.ubc.magic.utils.JSONUtils;

/**
 *
 * @author Mike Blackstock
 *
 */
public class JmsSubscription implements MessageListener {

	static final Log logger = LogFactory.getLog(JmsSubscription.class);

	private static final int QUEUE_CAPACITY = 100;
	
	private Subscription subscription;
	private Session subSession;
	private MessageConsumer consumer;
	private LinkedBlockingQueue<Event> messageQueue;
	
	public JmsSubscription(Subscription sub, Session subSession, MessageConsumer consumer) {
		this.messageQueue = new LinkedBlockingQueue<Event>(QUEUE_CAPACITY);
		this.subscription = sub;
		this.subSession = subSession;
		this.consumer = consumer;	
	}
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	public Session getSubSession() {
		return subSession;
	}
	public void setSubSession(Session subSession) {
		this.subSession = subSession;
	}
	public MessageConsumer getConsumer() {
		return consumer;
	}
	public void setConsumer(MessageConsumer consumer) {
		this.consumer = consumer;
	}
	public List<Event> getEvents(long waitTime) {
		Event newEvent = null;
		try {
			newEvent = messageQueue.poll(waitTime, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.error("interrupted waiting for events", e);
		}

		ArrayList<Event> events = new ArrayList<Event>();
		if (newEvent != null) {
			events.add(newEvent);
		}		
		messageQueue.drainTo(events);
		return events;
	}
	public void onMessage(Message message) {
		try {
			MapMessage ac = (MapMessage) message;
			// create the event and put it on the queue

			Event newEvent = new Event();
			newEvent.setClientTimestamp(ac.getLong("client_timestamp"));
			newEvent.setServerTimestamp(ac.getLong("server_timestamp"));
			newEvent.setThingId(ac.getLong("thing_id"));
			newEvent.setData(JSONUtils.parseJSON(ac.getString("data")));
			
			// try to put data on queue, if full, just drop it
			messageQueue.offer(newEvent);
		} catch (JMSException jmse) {
			logger.error("JMSException", jmse);
		} catch (ClassCastException cce) {
			logger.error("ClassCastException", cce);
		}
	}
	
	public void cleanUp() {
		try {
			this.consumer.close();
			this.subSession.close();
		} catch (JMSException e) {
			logger.error("error while closing JMS consumer or session");
		}
	}
}
