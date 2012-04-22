/**
 * Copyright (c) 2012 Media and Graphics Interdisciplinary Centre
 * University of British Columbia.
 */
package ca.ubc.magic.thingbroker.state;

/**
 * StateFields contain the latest value of all event fields sent to a thing, as well as other
 * information about these event fields, currently the order that these fields appear.
 * 
 * @author Mike Blackstock
 *
 */
public class StateField {
	
	private long timestamp;
	
	private String name;
	
	private Object value;
	
	private int index;
	
	/**
	 * The time the state was last updated in an event.
	 */
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * The name of the state.
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * The index (order) of the state field.
	 */
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * The value of the state.
	 */
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
