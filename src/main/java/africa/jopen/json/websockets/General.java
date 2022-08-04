package africa.jopen.json.websockets;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess

public class General{
    @JsonProperty("events")
    public Events getEvents() {
        return this.events; }
    public void setEvents(Events events) {
        this.events = events; }
    Events events;
    @JsonProperty("json")
    public Json getJson() {
        return this.json; }
    public void setJson(Json json) {
        this.json = json; }
    Json json;
    @JsonProperty("pingpong_trigger")
    public PingpongTrigger getPingpong_trigger() {
        return this.pingpong_trigger; }
    public void setPingpong_trigger(PingpongTrigger pingpong_trigger) {
        this.pingpong_trigger = pingpong_trigger; }
    PingpongTrigger pingpong_trigger;
    @JsonProperty("pingpong_timeout")
    public PingpongTimeout getPingpong_timeout() {
        return this.pingpong_timeout; }
    public void setPingpong_timeout(PingpongTimeout pingpong_timeout) {
        this.pingpong_timeout = pingpong_timeout; }
    PingpongTimeout pingpong_timeout;
    @JsonProperty("ws")
    public Ws getWs() {
        return this.ws; }
    public void setWs(Ws ws) {
        this.ws = ws; }
    Ws ws;
    @JsonProperty("ws_port")
    public WsPort getWs_port() {
        return this.ws_port; }
    public void setWs_port(WsPort ws_port) {
        this.ws_port = ws_port; }
    WsPort ws_port;
    @JsonProperty("ws_interface")
    public WsInterface getWs_interface() {
        return this.ws_interface; }
    public void setWs_interface(WsInterface ws_interface) {
        this.ws_interface = ws_interface; }
    WsInterface ws_interface;
    @JsonProperty("ws_ip")
    public WsIp getWs_ip() {
        return this.ws_ip; }
    public void setWs_ip(WsIp ws_ip) {
        this.ws_ip = ws_ip; }
    WsIp ws_ip;
    @JsonProperty("wss")
    public Wss getWss() {
        return this.wss; }
    public void setWss(Wss wss) {
        this.wss = wss; }
    Wss wss;
    @JsonProperty("wss_port")
    public WssPort getWss_port() {
        return this.wss_port; }
    public void setWss_port(WssPort wss_port) {
        this.wss_port = wss_port; }
    WssPort wss_port;
    @JsonProperty("wss_interface")
    public WssInterface getWss_interface() {
        return this.wss_interface; }
    public void setWss_interface(WssInterface wss_interface) {
        this.wss_interface = wss_interface; }
    WssInterface wss_interface;
    @JsonProperty("wss_ip")
    public WssIp getWss_ip() {
        return this.wss_ip; }
    public void setWss_ip(WssIp wss_ip) {
        this.wss_ip = wss_ip; }
    WssIp wss_ip;
    @JsonProperty("ws_logging")
    public WsLogging getWs_logging() {
        return this.ws_logging; }
    public void setWs_logging(WsLogging ws_logging) {
        this.ws_logging = ws_logging; }
    WsLogging ws_logging;
    @JsonProperty("ws_acl")
    public WsAcl getWs_acl() {
        return this.ws_acl; }
    public void setWs_acl(WsAcl ws_acl) {
        this.ws_acl = ws_acl; }
    WsAcl ws_acl;
}
