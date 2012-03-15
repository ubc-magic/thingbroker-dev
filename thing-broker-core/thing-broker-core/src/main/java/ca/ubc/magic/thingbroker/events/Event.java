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
	private long clientTimestamp;
	private long serverTimestamp;
	private long thingId;
	private Map<String, Object> data;
	private boolean save = false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getThingId() {
		return thingId;
	}

	public void setThingId(long thingId) {
		this.thingId = thingId;
	}

	public long getClientTimestamp() {
		return clientTimestamp;
	}

	public void setClientTimestamp(long clientTimestamp) {
		this.clientTimestamp = clientTimestamp;
	}

	public long getServerTimestamp() {
		return serverTimestamp;
	}

	public void setServerTimestamp(long serverTimestamp) {
		this.serverTimestamp = serverTimestamp;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

}
