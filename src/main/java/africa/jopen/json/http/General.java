package africa.jopen.json.http;

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
    @JsonProperty("base_path")
    public BasePath getBase_path() {
        return this.base_path; }
    public void setBase_path(BasePath base_path) {
        this.base_path = base_path; }
    BasePath base_path;
    @JsonProperty("http")
    public Http getHttp() {
        return this.http; }
    public void setHttp(Http http) {
        this.http = http; }
    Http http;
    @JsonProperty("port")
    public Port getPort() {
        return this.port; }
    public void setPort(Port port) {
        this.port = port; }
    Port port;
    @JsonProperty("interface")
    public Interface getMyinterface() {
        return this.myinterface; }
    public void setMyinterface(Interface myinterface) {
        this.myinterface = myinterface; }
    Interface myinterface;
    @JsonProperty("ip")
    public Ip getIp() {
        return this.ip; }
    public void setIp(Ip ip) {
        this.ip = ip; }
    Ip ip;
    @JsonProperty("https")
    public Https getHttps() {
        return this.https; }
    public void setHttps(Https https) {
        this.https = https; }
    Https https;
    @JsonProperty("secure_port")
    public SecurePort getSecure_port() {
        return this.secure_port; }
    public void setSecure_port(SecurePort secure_port) {
        this.secure_port = secure_port; }
    SecurePort secure_port;
    @JsonProperty("secure_interface")
    public SecureInterface getSecure_interface() {
        return this.secure_interface; }
    public void setSecure_interface(SecureInterface secure_interface) {
        this.secure_interface = secure_interface; }
    SecureInterface secure_interface;
    @JsonProperty("secure_ip")
    public SecureIp getSecure_ip() {
        return this.secure_ip; }
    public void setSecure_ip(SecureIp secure_ip) {
        this.secure_ip = secure_ip; }
    SecureIp secure_ip;
    @JsonProperty("acl")
    public Acl getAcl() {
        return this.acl; }
    public void setAcl(Acl acl) {
        this.acl = acl; }
    Acl acl;
    @JsonProperty("mhd_connection_limit")
    public MhdConnectionLimit getMhd_connection_limit() {
        return this.mhd_connection_limit; }
    public void setMhd_connection_limit(MhdConnectionLimit mhd_connection_limit) {
        this.mhd_connection_limit = mhd_connection_limit; }
    MhdConnectionLimit mhd_connection_limit;
    @JsonProperty("mhd_debug")
    public MhdDebug getMhd_debug() {
        return this.mhd_debug; }
    public void setMhd_debug(MhdDebug mhd_debug) {
        this.mhd_debug = mhd_debug; }
    MhdDebug mhd_debug;
}
