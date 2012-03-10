package com.sensetecnic.thingbroker.control;

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

}
