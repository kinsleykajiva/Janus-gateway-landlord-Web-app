package africa.jopen.json.serverinformation;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Transports{
    @JsonProperty("janus.transport.http")
    public JanusTransportHttp getJanusTransportHttp() {
        return this.janusTransportHttp; }
    public void setJanusTransportHttp(JanusTransportHttp janusTransportHttp) {
        this.janusTransportHttp = janusTransportHttp; }
    JanusTransportHttp janusTransportHttp;
    @JsonProperty("janus.transport.websockets")
    public JanusTransportWebsockets getJanusTransportWebsockets() {
        return this.janusTransportWebsockets; }
    public void setJanusTransportWebsockets(JanusTransportWebsockets janusTransportWebsockets) {
        this.janusTransportWebsockets = janusTransportWebsockets; }
    JanusTransportWebsockets janusTransportWebsockets;
}
