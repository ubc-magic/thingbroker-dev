package ca.ubc.magic.thingbroker.things;

import java.util.Map;

import ca.ubc.magic.thingbroker.state.State;

public class Thing {
	
	private String name;
	
	private Map<String, Object> metaData;
	
	private Map<String, State> state;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Metadata: information about the thing.
	 * 
	 * @return
	 */
	public Map<String, Object> getMetaData() {
		return metaData;
	}

	public void setMetaData(Map<String, Object> metaData) {
		this.metaData = metaData;
	}

	/**
	 * State: last time stamped data values (state) of the thing.
	 * 
	 * @return
	 */
	public Map<String, State> getState() {
		return state;
	}

	/**
	 * Update the last timestamped data values (state) of the thing.
	 * 
	 * @param state
	 */
	public void setState(Map<String, State> state) {
		this.state = state;
	}
}
