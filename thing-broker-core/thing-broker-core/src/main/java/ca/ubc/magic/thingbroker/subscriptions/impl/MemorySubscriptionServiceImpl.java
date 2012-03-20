/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.subscriptions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.ubc.magic.thingbroker.ThingBrokerException;
import ca.ubc.magic.thingbroker.events.Event;
import ca.ubc.magic.thingbroker.subscriptions.Subscription;
import ca.ubc.magic.thingbroker.subscriptions.SubscriptionExpiredException;
import ca.ubc.magic.thingbroker.subscriptions.SubscriptionService;

/**
 *
 * @author Mike Blackstock
 *
 */
public class MemorySubscriptionServiceImpl implements SubscriptionService{
	private static final Log logger = LogFactory.getLog(MemorySubscriptionServiceImpl.class);
	private static long nextId = 0;

	private Map<Long, JmsSubscription> subscriptions;
	private Connection connection;

	public MemorySubscriptionServiceImpl(ConnectionFactory connectionFactory) {
		this.subscriptions = new ConcurrentHashMap<Long, JmsSubscription>();
		this.connection = null;
		// TODO: may move this to after properties set
		try {
			this.connection = connectionFactory.createConnection();
			this.connection.start();
		} catch (JMSException e) {
			logger.error(e.getMessage());
		}
	}
	
	public long subscribe(String name) {
		Subscription sub = new Subscription();
		sub.setId(nextId++);
		List<String> thingList = new ArrayList<String>();
		thingList.add(name);
		sub.setThing(thingList);
		
		try {
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination topic = session.createTopic("thingbroker."+name);
			MessageConsumer consumer = session.createConsumer(topic);
			JmsSubscription s = new JmsSubscription(sub, session, consumer);
			consumer.setMessageListener(s);
			subscriptions.put(sub.getId(), s);
		} catch (JMSException e) {
			logger.error(e.getMessage());
			nextId--;
			throw new ThingBrokerException("JMS Exception occurred when subscribing", e);
		}

		return sub.getId();
	}

	public void addToSubscription(String name) {
	    throw new UnsupportedOperationException("TODO");
	}

	public void unsubscribe(long subId) {
		JmsSubscription sub = subscriptions.get(subId);
		sub.cleanUp();
		subscriptions.remove(subId);
	}

	public List<Event> getEvents(long id, long waitTime)
			throws SubscriptionExpiredException {
		JmsSubscription sub = subscriptions.get(id);
		return sub.getEvents(waitTime);
	}

	public Subscription getSubscription(long id)
			throws SubscriptionExpiredException {
		return subscriptions.get(id).getSubscription();
	}

}
