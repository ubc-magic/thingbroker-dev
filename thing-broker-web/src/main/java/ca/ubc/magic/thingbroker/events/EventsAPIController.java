package ca.ubc.magic.thingbroker.events;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/v1/events")
public class EventsAPIController {

	private static final Logger logger = LoggerFactory
			.getLogger(EventsController.class);

	private final EventService eventService;

	public EventsAPIController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@RequestMapping(value = "/{thingName:[.[a-z0-9_-]+]*}", method = RequestMethod.POST)
	@ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void sendEvent(@PathVariable String thingName,
			@RequestParam(value = "persist", required=false) Boolean persist,
			HttpServletRequest request) {
		persist = persist == null ? false : persist;
		// create event from parameters
		Event event = createEventFromRequest(request);
		logger.debug("send event:" + event);
		eventService.sendEvent(thingName, event, persist);
	}

	private Event createEventFromRequest(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = request.getParameterMap();

		Map<String, Object> eventMap = new HashMap<String, Object>();

		// create Map to send to sensor, note that each parameter must be
		// unique, so we only get the first value with that parameter
		Long timeStamp = null;
		for (String mapKey : params.keySet()) {
			if (mapKey.toLowerCase().equals("timestamp")) {
				timeStamp = Long.parseLong(params.get(mapKey)[0]);
			} else if (mapKey.toLowerCase().equals("persist")) {
				// ignore - this parameter tells us to save the event
			} else {
				Object value = parseParam(params.get(mapKey)[0]);
				eventMap.put(mapKey, value);
			}
		}
		Event event = new Event();
		event.setServerTimestamp(System.currentTimeMillis());
		event.setClientTimestamp(timeStamp == null ? event.getServerTimestamp() : timeStamp);
		event.setData(eventMap);
		return event;
	}

	private Object parseParam(String parameter) {
		Object value = null;
		try {
			value = Double.parseDouble(parameter);
		} catch (NumberFormatException e) {
			value = parameter;
		}
		return value;
	}

}
