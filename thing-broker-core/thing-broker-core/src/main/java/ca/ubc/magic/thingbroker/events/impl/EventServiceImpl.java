package ca.ubc.magic.thingbroker.events.impl;

import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import ca.ubc.magic.thingbroker.ThingNotFoundException;
import ca.ubc.magic.thingbroker.events.Event;
import ca.ubc.magic.thingbroker.events.EventService;
import ca.ubc.magic.thingbroker.things.impl.ThingRepository;
import ca.ubc.magic.utils.JSONUtils;

public class EventServiceImpl implements EventService {
	static final Log logger = LogFactory.getLog(EventServiceImpl.class);

	private final JmsTemplate jmsTemplate;
	private final ThingRepository thingRepository;
	
	public EventServiceImpl(JmsTemplate jmsTemplate, ThingRepository thingRepository) {
		this.jmsTemplate = jmsTemplate;
		this.thingRepository = thingRepository;
	}

	public Event updateData(long id, Event data) {
		// TODO Auto-generated method stub
	    throw new UnsupportedOperationException("TODO");
	}

	public Event deleteData(long id) {
		// TODO Auto-generated method stub
	    throw new UnsupportedOperationException("TODO");
	}

	public List<Event> queryEvents(String name, String query) {
		// TODO Auto-generated method stub
	    throw new UnsupportedOperationException("TODO");
	}

	public void sendEvents(String name, List<Event> events, boolean persist) {
		// get the thing id from the repository
		try {
			long thingId = thingRepository.getId(name);
			for (Event event: events) {
				sendToBroker(name, thingId, event, persist);
			}
		} catch (EmptyResultDataAccessException e) {		
			// TODO: do we create one as in the MB1 and 2, or ?
			throw new ThingNotFoundException("attempt to send events to non-existent thing", e);
		}
		
	}
	
	public void sendEvent(String name, Event event, boolean persist) {
		try {
			long thingId = thingRepository.getId(name);
			sendToBroker(name, thingId, event, persist);
		} catch (EmptyResultDataAccessException e) {		
			// TODO: do we create one as in the MB1 and 2, or ?
			throw new ThingNotFoundException("attempt to send events to non-existent thing", e);
		}
	}
	
	public void addIndex(String name, String fieldName) {
		// TODO Auto-generated method stub
	    throw new UnsupportedOperationException("TODO");
	}

	/**
	 * Send an event to the message broker.
	 * 
	 * @param thingName
	 * @param thingId
	 * @param event
	 */
	private void sendToBroker(String thingName, final long thingId, final Event event, final boolean persist) {
		Destination destination = new ActiveMQTopic("thingbroker." + thingName);
		logger.debug("sending "+event+" to broker");
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setLong("server_timestamp", System.currentTimeMillis());
				message.setLong("client_timestamp", event.getClientTimestamp());
				message.setBoolean("persist", persist);
				message.setLong("thing_id", thingId);
				String json = JSONUtils.generateJSON(event.getData());
				message.setString("data", json);
				return message;
			}
		});
	}


}
