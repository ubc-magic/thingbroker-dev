/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.subscriptions;

import ca.ubc.magic.thingbroker.events.Event;

/**
 *
 * @author Mike Blackstock
 *
 */
public interface EventHandler {
	
	void handleEvent(Event event);

}
