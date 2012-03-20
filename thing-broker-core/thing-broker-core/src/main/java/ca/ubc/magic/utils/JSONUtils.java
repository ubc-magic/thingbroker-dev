/**
 * Copyright (c) 2010 Sense Tecnic Systems Inc.  All rights reserved.
 */
package ca.ubc.magic.utils;

import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Utilities for some typical JSON operations
 * 
 * @author mike
 * 
 */
public class JSONUtils {
	static private final Log logger = LogFactory.getLog(JSONUtils.class);

	/**
	 * Generate JSON string from a java object
	 * 
	 * @param objectMap
	 *            LinkedHashMap java object containing options
	 * @return JSON string
	 */
	static public String generateJSON(Object javaObject) {
		String jsonString = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonString = mapper.writeValueAsString(javaObject);
		} catch (Exception e) {
			logger.error("Error while generating JSON string, returning null:"
					+ e);
			jsonString = null;
		}

		return jsonString;
	}

	/**
	 * Generate JSON string from LinkedHashMap of Java objects
	 * 
	 * @param objectMap
	 *            LinkedHashMap java object containing options
	 * @return JSON string
	 */
	static public String generateJSON(LinkedHashMap<String, Object> objectMap) {
		String jsonString = null;
		if (objectMap != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				jsonString = mapper.writeValueAsString(objectMap);
			} catch (Exception e) {
				logger.error("Error while generating JSON string, returning null:" + e);
				jsonString = null;
			}
		}
		return jsonString;
	}

	/**
	 * Parse JSON string to a LinkedHashMap
	 * 
	 * @param jsonString
	 *            JSON string to parse
	 * @return LinkedHashMap java object
	 */
	static public LinkedHashMap<String, Object> parseJSON(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		LinkedHashMap<String, Object> parsedMap = null;
		TypeReference<LinkedHashMap<String, Object>> typeRef = new TypeReference<LinkedHashMap<String, Object>>() {
		};
		try {
			parsedMap = mapper.readValue(jsonString, typeRef);
		} catch (Exception e) {
			logger
					.error("Error while parsing JSON string, returning null:"
							+ e);
			parsedMap = null;
		}

		return parsedMap;
	}

}
