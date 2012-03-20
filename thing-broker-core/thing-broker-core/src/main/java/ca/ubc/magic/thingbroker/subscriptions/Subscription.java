package ca.ubc.magic.thingbroker.subscriptions;

import java.util.List;

/**
 * A thing-event subscription
 * 
 * @author Mike Blackstock
 *
 */
public class Subscription {

	private long id;
	private List<String> things;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<String> getThing() {
		return things;
	}
	public void setThing(List<String> thing) {
		this.things = thing;
	}

}