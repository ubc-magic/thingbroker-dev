/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.events;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests to send and get (historical) events
 * 
 * @author Mike Blackstock
 * 
 */
@Controller
@RequestMapping("/events")
public class EventsController {
	private static final Logger logger = LoggerFactory
			.getLogger(EventsController.class);

	private final EventService eventService;

	public EventsController(EventService eventService) {
		this.eventService = eventService;
	}
	
	/**
	 * Event test page.
	 * 
	 * TODO: move it to another controller. 
	 * 
	 * @param thingName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{thingName:[.[a-z0-9_-]+]*}", method = RequestMethod.GET)
	public String eventPage(@PathVariable String thingName, ModelMap model) {
		model.addAttribute("thingName", thingName);
		return "events";
	}

}
