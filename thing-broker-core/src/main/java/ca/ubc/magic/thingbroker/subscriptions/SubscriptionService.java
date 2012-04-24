package ca.ubc.magic.thingbroker.subscriptions;

import java.util.List;

import ca.ubc.magic.thingbroker.events.Event;

/**
 * Service for subscribing to thing events.
 * 
 * @author Mike Blackstock
 *
 */
public interface SubscriptionService {

	/**
	 * Subscribe to thing; thing name may contain wildcards.
	 * Subscription will maintain a queue of received events.
	 * 
	 * POST /subs?thing={name}
	 * 
	 * @param subId subscription id, if 
	 * @param name
	 * @return
	 */
	long subscribe(String name);
	
	/**
	 * Subscribe to thing(s) and send events to the specified URL
	 * 
	 * @param name
	 * @param url
	 * @return
	 */
	long subscribe(String name, String url);
	
	/**
	 * Subscribe to thing; thing name may contain wildcards.
	 * Subscription will not maintain a queue, but will call eventHandler on every
	 * event received.
	 * 
	 * @param name
	 * @param eventHandler event handler for receiving events
	 * @return
	 */
	long subscribe(String name, EventHandler eventHandler);
	
	/**
	 * add another thing to an existing subscription; thing may contain wildcards
	 * 
	 * PUT /subs/{id}?thing={name}
	 * 
	 * @param subId subscription id, if 
	 * @param name
	 * @return
	 */
	void addToSubscription(long id, String name);
	
	/**
	 * Unsubscribe from the subscription id.  This effectively deletes the subscription.
	 * 
	 * DELETE /subs/{id}
	 * 
	 * deletes subscription
	 * 
	 * @param id
	 * @return
	 */
	void unsubscribe(long subId);
	
	/**
	 * Get pending data from the subscription.  Note that if the subscription is not
	 * used after some period of time, it will expire.
	 * 
	 * GET /subs/{id}/events?wait_time={wait}
	 * 
	 * @param id
	 * @return
	 */
	List<Event> getEvents(long id, long waitTime) throws SubscriptionExpiredException;
	
	
	/**
	 * Get all subscriptions.
	 * 
	 * @return
	 */
	List<Subscription> getSubscriptions();
	
	/**
	 * Get subscription.
	 * 
	 * GET /subs/{id}
	 * 
	 * @param id
	 * @return
	 */
	Subscription getSubscription(long id) throws SubscriptionExpiredException;
}
