package ca.ubc.magic.thingbroker.events;

import java.util.List;

/**
 * Thing event service interface for sending, getting, updating deleting historical data.
 * 
 * @author Mike Blackstock
 *
 */
public interface EventService {
	
	/**
	 * PUT /events/{id}
	 * 
	 * @param id
	 * @param data
	 * @return
	 */
	Event updateData(long id, Event data);
	
	/**
	 * 
	 * DELETE /events/{id}
	 * 
	 * Delete the event
	 * 
	 * @param id
	 * @return
	 */
	Event deleteData(long id);

	/**
	 * Historical data query.  Wildcards supported in thing name
	 * 
	 * GET /events/{name}?q=query&wait={wait}
	 * 
	 * @param name
	 * @param query
	 * @param waitTime
	 * @return
	 */
	List<Event> queryEvents(String name, String query);
	
	/**
	 * Send data to thing.
	 * 
	 * POST /events/{name}?_save={true|false}
	 * POST /events/{name}?field1=data1&field2=data2
	 * 
	 * @param data events to send
	 * @param save true if event should be saved
	 */
	void sendEvents(String name, List<Event> data, boolean save);
	
	/**
	 * Add an index to a data field so it can be queried.  Wildcards supported.
	 * 
	 * @param name
	 * @param fieldName
	 */
	void addIndex(String name, String fieldName);
		
}
