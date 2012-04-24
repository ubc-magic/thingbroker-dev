package ca.ubc.magic.thingbroker;

@SuppressWarnings("serial")
public class ThingNotFoundException extends ThingBrokerException {

	public ThingNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
