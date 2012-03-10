/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package com.sensetecnic.thingbroker.things;

/**
 * StateField contains the latest value of a data field sent to a thing as well as additional
 * information.
 * 
 * @author Mike Blackstock
 *
 */
public class StateField {
	long timestamp;
	String name;
	Object value;
}
