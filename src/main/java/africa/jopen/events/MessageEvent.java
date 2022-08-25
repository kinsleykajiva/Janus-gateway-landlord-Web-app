package africa.jopen.events;


public class MessageEvent {
	public static final int MESSAGE_NEW_SIP_EVENT = 1;


	String message   = "";
	int    eventType = 0;

	public MessageEvent (int type, String message) {
		this.message = message;
		this.eventType = type;
	}

	public String getMessage () {
		return message;
	}

	public int getEventType () {
		return eventType;
	}
}
