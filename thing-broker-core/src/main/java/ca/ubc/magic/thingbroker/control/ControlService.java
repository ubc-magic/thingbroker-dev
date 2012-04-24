package ca.ubc.magic.thingbroker.control;

import java.util.List;
import java.util.Map;

/**
 * The control service is for sending and receiving control and service messages from things.
 * 
 * @author Mike Blackstock
 *
 */
public interface ControlService {

	// ~--- application interface - calling things

	/**
	 * Send a control request to the thing.  If a response is requested, wait until it is complete.
	 * 
	 * @param request
	 * @param responseFlag
	 * @return response if the response flag is set, or null.
	 */
	Map<String, Object> sendRequest(String name, Map<String, Object> request, boolean responseFlag);

	// ~--- controller interface - implementing control services
	
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
