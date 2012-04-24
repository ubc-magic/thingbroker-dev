package ca.ubc.magic.thingbroker.query;

import java.util.List;

public interface QueryService {

	/**
	 * Return results from the "Thing Query Language".
	 * There are only two tables: things and events.
	 * 
	 * metadata and state are always part of the thing table
	 * 
	 * Data model includes:
	 * 	things, events only.
	 *  things have fields, metadata and state
	 *  
	 *  select name, metadata from things where metadata.{field}>5 and state.{field}.timestamp > X
	 *  select {field} from events where events.thingid in (select id from things where metadata.lng > 49.0)
	 * 
	 * @param thingQuery
	 * @return
	 */
	List<Object> query(String thingQuery);
	
}
