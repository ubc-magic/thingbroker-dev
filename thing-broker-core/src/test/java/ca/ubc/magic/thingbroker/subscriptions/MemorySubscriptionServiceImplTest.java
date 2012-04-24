/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.subscriptions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

import ca.ubc.magic.thingbroker.events.Event;
import ca.ubc.magic.thingbroker.events.EventService;
import ca.ubc.magic.thingbroker.events.impl.EventServiceImpl;
import ca.ubc.magic.thingbroker.subscriptions.impl.MemorySubscriptionServiceImpl;
import ca.ubc.magic.thingbroker.things.impl.ThingRepository;

/**
 *
 * @author Mike Blackstock
 *
 */
public class MemorySubscriptionServiceImplTest {

	private static final Log logger = LogFactory.getLog(MemorySubscriptionServiceImplTest.class);
	static BrokerService broker;
	EventService eventService;
	MemorySubscriptionServiceImpl subscriptionService;
	ConnectionFactory connectionFactory;
	
	@BeforeClass
	public static void startServer() {
//		broker = new BrokerService();
//		// configure the broker
//		broker.setBrokerName("test");
//		try {
//			broker.addConnector("tcp://localhost:61616");
//			broker.start();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	@AfterClass
	public static void stopServer() {
//		try {
//			broker.stop();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
	
	@Before
	public void setup() {
	
		ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
//		amqConnectionFactory.setBrokerURL("tcp://localhost:61616");
		connectionFactory = amqConnectionFactory;
		JmsTemplate jmsTemplate = new JmsTemplate(amqConnectionFactory);
		ThingRepository thingRepository = mock(ThingRepository.class);
		when(thingRepository.getId("test-thing")).thenReturn(1L);
		eventService = new EventServiceImpl(jmsTemplate, thingRepository);
		subscriptionService = new MemorySubscriptionServiceImpl(connectionFactory);
	}
	
	@After
	public void destroy() {
		try {
			// close the connection
			subscriptionService.destroy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSubscriptReceive() {
		
		// subscribe, then start a thread to send
		
		final long subId = subscriptionService.subscribe("test-thing");
	
		Thread receiver = new Thread(new Runnable() {
			public void run() {
				int numEvents = 0;
				do {
					List<Event> newEvents = subscriptionService.getEvents(subId, 10);
					logger.debug("subscriber "+subId+newEvents);
					numEvents += newEvents.size();
				} while (numEvents < 5);
			}
		});
		
		final long subId2 = subscriptionService.subscribe("test-thing");
		Thread receive2 = new Thread(new Runnable() {
			public void run() {
				int numEvents = 0;
				do {
					List<Event> newEvents = subscriptionService.getEvents(subId2, 10);
					logger.debug("subscriber "+subId2+newEvents);
					numEvents += newEvents.size();
				} while (numEvents < 5);
			}
		});
		
		// now start a thread to send events
		Thread sender = new Thread(new Runnable() {
			public void run() {
				for (int i=0; i<5; i++) {
					Event testEvent = new Event();
					testEvent.setClientTimestamp(System.currentTimeMillis());
					LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
					data.put("value", i*1.0);
					testEvent.setData(data);
					List<Event> events = new ArrayList<Event>();
					events.add(testEvent);
					eventService.sendEvents("test-thing", events, false);	
				}
			}
		});
		receiver.start();
		receive2.start();

		sender.start();
		try {
			receiver.join();
			receive2.join();
			sender.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		subscriptionService.unsubscribe(subId);
		subscriptionService.unsubscribe(subId2);
		

	}
	
}
