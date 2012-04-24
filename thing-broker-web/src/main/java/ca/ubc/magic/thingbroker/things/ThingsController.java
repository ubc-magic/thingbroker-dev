/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.things;

import java.util.List;

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
@RequestMapping("/things")
public class ThingsController {
	private static final Logger logger = LoggerFactory
			.getLogger(ThingsController.class);

	private final ThingService thingService;

	public ThingsController(ThingService eventService) {
		this.thingService = eventService;
	}
	
	/**
	 * Thing list page.
	 * 
	 * TODO: move it to another controller. 
	 * 
	 * @param thingName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{thingName:[.[a-z0-9_-]\\*+]*}")
	public String list(@PathVariable String thingName, ModelMap model) {
		logger.debug("list or view individual thing");
		List<Thing> things = thingService.getThings(thingName);
		if (things.size() == 1) {
			model.addAttribute("thingName", things.get(0).getName());

			model.addAttribute("thing", things.get(0));
			return "thing_show";
		}
		model.addAttribute("thingName", thingName);
		// if there is zero or one thing, use the thing list.
		model.addAttribute("things", things);
		return "thing_list";
	}
	
	/**
	 * List all things
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping()
	public String listAll(ModelMap model) {
		logger.debug("list all things");
		return list("*", model);
	}
	
	/**
	 * Add a thing
	 * 
	 * @param action
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(@RequestParam(value="action", required=true) String action, ModelMap model) {
		logger.debug("create thing");
		model.addAttribute("action", action);
		return "thing_create";
	}
	
	/**
	 * Edit a thing
	 * 
	 * @param thingName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{thingName:[.[a-z0-9_-]+]*}", params = "form", produces = "text/html")
	public String editForm(@PathVariable String thingName, ModelMap model) {
		logger.debug("edit thing");

		model.addAttribute("thingName", thingName);
		return "thing_edit";
	}


}
