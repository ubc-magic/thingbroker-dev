package ca.ubc.magic.thingbroker.things;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/things")
public class ThingsApiController {

	private static final Logger logger = LoggerFactory
			.getLogger(ThingsController.class);

	private final ThingService thingService;

	public ThingsApiController(ThingService thingService) {
		this.thingService = thingService;
	}

}
