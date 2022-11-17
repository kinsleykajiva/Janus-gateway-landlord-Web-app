package africa.jopen.websockets;


import africa.jopen.events.MessageEvent;
import africa.jopen.utils.XUtils;
import io.micronaut.http.MediaType;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Secured (SecurityRule.IS_ANONYMOUS)
@ServerWebSocket ("/ws/janus/{tail}/{logs}")
public class JanusLogsWebsockets {

	private static final Logger logger = LoggerFactory.getLogger(JanusLogsWebsockets.class);

	public final WebSocketBroadcaster broadcaster;

	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

	public JanusLogsWebsockets (WebSocketBroadcaster broadcaster) {
		this.broadcaster = broadcaster;
		EventBus.getDefault().register(this);
	}

	@OnOpen
	public Publisher<String> onOpen (String tail, String logs, WebSocketSession session) {

		System.out.println("onOpen: " + session.getId());
		sessions.put(session.getId(), session);
		System.out.println("onOpen: " + sessions.size());
		logger.info("onOpen: " + sessions.size());

		XUtils.setKnownIssuesSinceStartUp("websocket--client-count", String.valueOf(sessions.size()));
		return broadcaster.broadcast(new JSONObject()
						.put("type", "joined")
						.put("success", true)
						.put("logs", logs)
						.put("tail", tail).toString()
				, MediaType.APPLICATION_JSON_TYPE);

	}

	@OnMessage
	public Publisher<String> onMessage (String tail, String logs, String message, WebSocketSession session) {
		System.out.println("onOpen: " + message);
		return broadcaster.broadcast(String.format("[%s] %s", logs, message), MediaType.APPLICATION_JSON_TYPE);
	}

	@OnClose
	public Publisher<String> onClose (String tail, String logs, WebSocketSession session) {
		System.out.println("[onClose] " + tail + " " + logs + session.getId());
		sessions.remove(session.getId());
		XUtils.setKnownIssuesSinceStartUp("websocket--client-count", String.valueOf(sessions.size()));
		return broadcaster.broadcast(String.format("[%s] Leaving %s!", logs, tail), MediaType.APPLICATION_JSON_TYPE);
	}

	@Subscribe (sticky = true, threadMode = ThreadMode.BACKGROUND)
	@OnMessage
	public void onEvent (MessageEvent event) {
		System.out.println("onEvent: " + event);
		logger.info("onEvent: " + event);
		if (MessageEvent.MESSAGE_NEW_SIP_EVENT == event.getEventType()) {
			for (var entry : sessions.entrySet()) {
				entry.getValue().sendAsync(
						new JSONObject()
								.put("success", true)
								.put("type", "new")
								.put("data", new JSONObject(event.getMessage()))
								.toString()
						, MediaType.APPLICATION_JSON_TYPE);
			}

		}
	}


}
