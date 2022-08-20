package africa.jopen.websockets;


import africa.jopen.utils.XUtils;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

@ServerWebSocket("/ws/janus/{tail}/{logs}")
public class JanusLogsWebsockets {

    private static final Logger LOG = LoggerFactory.getLogger(JanusLogsWebsockets.class);

    private final WebSocketBroadcaster broadcaster;
    private Thread tailThread;

    public JanusLogsWebsockets(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;

        /**Here we are running this function in a thread but a sing thread, all new connections will be sharing this connection to make sure we dont recreate an existing but to share this.
         * This thread is ever running as it will still produce logs
         * */
        if (this.broadcaster != null) {
            if (tailThread == null) {

                tailThread = new Thread(() -> XUtils.executeBashCommand("sudo snap logs janus-gateway -f", broadcaster));

                tailThread.start();
            }

        }

    }

    @OnOpen
    public Publisher<String> onOpen(String tail, String logs, WebSocketSession session) {
        log("onOpen", session, logs, tail);
        if (tail.equals("all")) {
            return broadcaster.broadcast(String.format("[%s] Now making announcements!", logs), isValid(tail));
        }
        return broadcaster.broadcast(String.format("[%s] Joined %s!", logs, tail), isValid(tail));
    }

    @OnMessage
    public Publisher<String> onMessage(String tail, String logs, String message, WebSocketSession session) {
        log("onMessage", session, logs, tail);
        return broadcaster.broadcast(String.format("[%s] %s", logs, message), isValid(tail));
    }

    @OnClose
    public Publisher<String> onClose(String tail, String logs, WebSocketSession session) {
        log("onClose", session, logs, tail);
        return broadcaster.broadcast(String.format("[%s] Leaving %s!", logs, tail), isValid(tail));
    }

    private void log(String event, WebSocketSession session, String logs, String tail) {
        LOG.info("* WebSocket: {} received for session {} from '{}' regarding '{}'",
                event, session.getId(), logs, tail);
    }

    private Predicate<WebSocketSession> isValid(String tail) {
        return s -> tail.equals("all") //broadcast to all users
                    || "all".equals(s.getUriVariables().get("tail", String.class, null)) //"all" subscribes to every tail
                    || tail.equalsIgnoreCase(s.getUriVariables().get("tail", String.class, null)); //intra-tail chat
    }
}
