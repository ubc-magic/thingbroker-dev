package ca.ubc.magic.thingbroker.events;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

import ca.ubc.magic.thingbroker.events.impl.EventServiceImpl;
import ca.ubc.magic.thingbroker.things.impl.ThingRepository;

public class EventServiceImplTest {
	
	EventService eventService;
	ConnectionFactory connectionFactory;
	static BrokerService broker;

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
		
	}

	@Before
	public void setup() {
	
//		ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory();
//		amqConnectionFactory.setBrokerURL("tcp://localhost:61616");
		ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
		connectionFactory = amqConnectionFactory;
		JmsTemplate jmsTemplate = new JmsTemplate(amqConnectionFactory);
		ThingRepository thingRepository = mock(ThingRepository.class);
		when(thingRepository.getId("test-thing")).thenReturn(1L);
		eventService = new EventServiceImpl(jmsTemplate, thingRepository);
	}

	@After
	public void destroy() {

	}

	@Test
	public void testSendEvent() {
		
		Event testEvent = new Event();
		testEvent.setClientTimestamp(System.currentTimeMillis());
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("value", 45.0);
		testEvent.setData(data);
		List<Event> events = new ArrayList<Event>();
		events.add(testEvent);
		eventService.sendEvents("test-thing", events, false);
		
	}

}
