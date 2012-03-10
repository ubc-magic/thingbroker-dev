package com.sensetecnic.thingbroker.control;

import java.util.List;
import java.util.Map;

/**
 * Service for external thing controllers.
 * 
 * @author Mike Blackstock
 *
 */
public interface ControlImplService {

	
	/**
	 * Returns an unique subscriber ID for receiving control requests
	 * 
	 * @param name name of the thing to get control requests from
	 * @return
	 */
	long subscribe(String name);
	
	/**
	 * Unsubscribes the subscriber with the given ID 
	 * 
	 * @param id The subscriber ID
	 */
	void unSubscribe(long id);
	
	/**
	 * Returns a list of pending control requests.
	 * 
	 * @param waitTime The polling time for each control command request
	 * @return
	 * @throws ControlServiceException 
	 */
	List<Map<String, Object>> getControlRequests(long id, int waitTime);
	
	/**
	 * Send a response message to a received request.
	 * 
	 * @param response
	 * @param requestId
	 */
	void sendResponse(Map<String, Object> response, long requestId);

}
