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
	 * Subscribe to things; thing names may contain wildcards.
	 * 
	 * PUT /subs/{name}
	 * 
	 * @param name
	 * @param listener - optional listener.  If not null, calls listener when new data is sent to the thing.
	 * @return the id of the subscription
	 */
	long subscribe(List<String> names);
	
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
	 * GET /subs/data/{id}
	 * 
	 * @param id
	 * @return
	 */
	List<Event> getEvents(long id, long waitTime) throws SubscriptionExpiredException;
	
	/**
	 * Get subscription info.
	 * 
	 * GET /subs/{id}
	 * 
	 * @param id
	 * @return
	 */
	Subscription getSubscription(long id) throws SubscriptionExpiredException;
}
