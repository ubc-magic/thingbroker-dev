/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.subscriptions;

import ca.ubc.magic.thingbroker.ThingBrokerException;

/**
 *
 * @author Mike Blackstock
 *
 */
@SuppressWarnings("serial")
public class SubscriptionExpiredException extends ThingBrokerException {

	public SubscriptionExpiredException(Throwable cause) {
		super(cause);
	}

}
