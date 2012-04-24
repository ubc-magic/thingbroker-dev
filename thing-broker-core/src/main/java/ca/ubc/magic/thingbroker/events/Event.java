/**
 * Copyright (c) 2012 Media and Graphics Interdisciplinary Centre
 * University of British Columbia.
 */
package ca.ubc.magic.thingbroker.events;

import java.util.Map;

/**
 * Events are id, timestamps and name/value pairs sent to things.  Note that values are typed 
 * and can be numbers (double), strings and (eventually) other types.
 * 
 * @author Mike Blackstock
 *
 */
public class Event {

	private long id;
	private Long clientTimestamp;
	private long serverTimestamp;
	private long thingId;
	private Map<String, Object> data;
	private boolean save = false;

	/**
	 * Unique id of the event once its been assigned by the repository.
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Id of the thing this event belongs to in the thing repository.
	 */
	public long getThingId() {
		return thingId;
	}

	public void setThingId(long thingId) {
		this.thingId = thingId;
	}

	/**
	 * The timestamp for the event sent by the client (sensor, phone, etc.)
	 */
	public Long getClientTimestamp() {
		return clientTimestamp;
	}

	public void setClientTimestamp(Long clientTimestamp) {
		this.clientTimestamp = clientTimestamp;
	}

	/**
	 * The timestamp for when the event was received by the server.
	 */
	public long getServerTimestamp() {
		return serverTimestamp;
	}

	public void setServerTimestamp(long serverTimestamp) {
		this.serverTimestamp = serverTimestamp;
	}

	/**
	 * The event data.
	 */
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * true if the event will be saved in the event repository.
	 */
	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [id=");
		builder.append(id);
		builder.append(", clientTimestamp=");
		builder.append(clientTimestamp);
		builder.append(", serverTimestamp=");
		builder.append(serverTimestamp);
		builder.append(", thingId=");
		builder.append(thingId);
		builder.append(", data=");
		builder.append(data);
		builder.append(", save=");
		builder.append(save);
		builder.append("]");
		return builder.toString();
	}

}
