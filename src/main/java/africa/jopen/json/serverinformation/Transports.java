package africa.jopen.json.serverinformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Transports {
	JanusTransportHttp       janusTransportHttp;
	JanusTransportWebsockets janusTransportWebsockets;

	@JsonProperty ("janus.transport.http")
	public JanusTransportHttp getJanusTransportHttp () {
		return this.janusTransportHttp;
	}

	public void setJanusTransportHttp (JanusTransportHttp janusTransportHttp) {
		this.janusTransportHttp = janusTransportHttp;
	}

	@JsonProperty ("janus.transport.websockets")
	public JanusTransportWebsockets getJanusTransportWebsockets () {
		return this.janusTransportWebsockets;
	}

	public void setJanusTransportWebsockets (JanusTransportWebsockets janusTransportWebsockets) {
		this.janusTransportWebsockets = janusTransportWebsockets;
	}
}
