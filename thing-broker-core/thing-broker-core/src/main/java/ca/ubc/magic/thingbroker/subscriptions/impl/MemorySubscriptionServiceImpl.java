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
import org.springframework.beans.factory.DisposableBean;

import ca.ubc.magic.thingbroker.ThingBrokerException;
import ca.ubc.magic.thingbroker.events.Event;
import ca.ubc.magic.thingbroker.subscriptions.EventHandler;
import ca.ubc.magic.thingbroker.subscriptions.Subscription;
import ca.ubc.magic.thingbroker.subscriptions.SubscriptionExpiredException;
import ca.ubc.magic.thingbroker.subscriptions.SubscriptionService;

/**
 *
 * @author Mike Blackstock
 *
 */
public class MemorySubscriptionServiceImpl implements SubscriptionService, DisposableBean {
	private static final Log logger = LogFactory.getLog(MemorySubscriptionServiceImpl.class);
	private static long nextId = 0;

	private Map<Long, JmsSubscription> subscriptions;
	private Connection connection;

	public MemorySubscriptionServiceImpl(ConnectionFactory connectionFactory) {
		this.subscriptions = new ConcurrentHashMap<Long, JmsSubscription>();
		this.connection = null;
		try {
			// we start the connection -- adding sessions doesn't seem to be a problem
			this.connection = connectionFactory.createConnection();
			this.connection.start();
		} catch (JMSException e) {
			logger.error(e.getMessage());
			throw new ThingBrokerException("JMS Exception on startup", e);
		}
	}
	
	public long subscribe(String name) {
		return doSubscribe(name, null, null);
	}

	public long subscribe(String name, String url) {
		UrlEventHandler urlHandler = new UrlEventHandler();
		return doSubscribe(name, url, urlHandler);
	}

	public long subscribe(String name, EventHandler eventHandler) {
		return doSubscribe(name, null, eventHandler);
	}
	
	private long doSubscribe(String name, String url, EventHandler eventHandler) {
		Subscription sub = new Subscription();
		sub.setId(nextId+1);
		sub.setCallbackUrl(url);
		List<String> thingList = new ArrayList<String>();
		thingList.add(name);
		sub.setThings(thingList);
		try {
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination topic = session.createTopic("thingbroker."+name);
			MessageConsumer consumer = session.createConsumer(topic);
			JmsSubscription s = new JmsSubscription(sub, session, consumer, eventHandler);
			consumer.setMessageListener(s);
			subscriptions.put(sub.getId(), s);
		} catch (JMSException e) {
			logger.error(e.getMessage());
			throw new ThingBrokerException("JMS Exception occurred when subscribing", e);
		}
		nextId++;
		return sub.getId();
	}

	public void addToSubscription(long id, String name) {
		// add another topic to an existing subscription
		// this may be done by having the JmsSubscription's session have multiple consumers, then shut them all down.
		// currently not supported.
		
		// sub get session
		// consumer.sub.createConsumer(topic)
		// s.addConsumer(consumer) - this will call consumer.setMessageListener(this), and add it to the list of consuemrs
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

	public List<Subscription> getSubscriptions() {
		List<Subscription> subs = new ArrayList<Subscription>();
		for (Long key: subscriptions.keySet()) {
			subs.add(subscriptions.get(key).getSubscription());
		}
		return subs;
	}

	public Subscription getSubscription(long id)
			throws SubscriptionExpiredException {
		return subscriptions.get(id).getSubscription();
	}

	public void destroy() throws Exception {
		connection.close();
	}

}
