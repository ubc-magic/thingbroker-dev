package ca.ubc.magic.thingbroker.subscriptions;

import java.util.List;

/**
 * A thing-event subscription
 * 
 * @author Mike Blackstock
 *
 */
public class Subscription {


	private long id;				// subscription id
	//private Account account;		// owner of the subscription
	private String callbackUrl;		// callback URL for subscription
	private List<String> things;	// list of things subscribed to
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<String> getThings() {
		return things;
	}
	public void setThings(List<String> things) {
		this.things = things;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

}
