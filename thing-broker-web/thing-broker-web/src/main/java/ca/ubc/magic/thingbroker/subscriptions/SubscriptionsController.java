/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.subscriptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ubc.magic.thingbroker.events.EventsController;

/**
 *
 * @author Mike Blackstock
 *
 */
@Controller
@RequestMapping("/subs")
public class SubscriptionsController {
	private static final Logger logger = LoggerFactory
			.getLogger(EventsController.class);
	
	private final SubscriptionService subService;
	
	public SubscriptionsController(SubscriptionService subService) {
		this.subService = subService;
	}

	/**
	 * Subscriptions test page.
	 * 
	 * @param thingName
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String subscriptionPage(ModelMap model) {
		model.addAttribute("subs", subService.getSubscriptions());
		return "subs";
	}
	
	/**
	 * Get events waiting on the subscription
	 * 
	 * @param subId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{subId}/events", method = RequestMethod.GET)
	public String getEvents(@PathVariable Long subId, ModelMap model) {
		model.addAttribute("sub", subService.getSubscription(subId));
		model.addAttribute("events", subService.getEvents(subId, 10));
		return "subEvents";
	}
	
	/**
	 * Test subscribing.
	 * @param thingName
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String createSubscription(@RequestParam(value="thing", required=true) String thingName, ModelMap model) {
		subService.subscribe(thingName);
		model.addAttribute("subs", subService.getSubscriptions());
		return "subs";
	}

}
