package africa.jopen.json.simpleevents;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class General{
	@JsonProperty("enabled")
	public Enabled getEnabled() {
		return this.enabled; }
	public void setEnabled(Enabled enabled) {
		this.enabled = enabled; }
	Enabled enabled;
	@JsonProperty("events")
	public Events getEvents() {
		return this.events; }
	public void setEvents(Events events) {
		this.events = events; }
	Events events;
	@JsonProperty("grouping")
	public Grouping getGrouping() {
		return this.grouping; }
	public void setGrouping(Grouping grouping) {
		this.grouping = grouping; }
	Grouping grouping;
	@JsonProperty("json")
	public Json getJson() {
		return this.json; }
	public void setJson(Json json) {
		this.json = json; }
	Json json;
	@JsonProperty("compress")
	public Compress getCompress() {
		return this.compress; }
	public void setCompress(Compress compress) {
		this.compress = compress; }
	Compress compress;
	@JsonProperty("compression")
	public Compression getCompression() {
		return this.compression; }
	public void setCompression(Compression compression) {
		this.compression = compression; }
	Compression compression;
	@JsonProperty("backend")
	public Backend getBackend() {
		return this.backend; }
	public void setBackend(Backend backend) {
		this.backend = backend; }
	Backend backend;
	@JsonProperty("backend_user")
	public BackendUser getBackend_user() {
		return this.backend_user; }
	public void setBackend_user(BackendUser backend_user) {
		this.backend_user = backend_user; }
	BackendUser backend_user;
	@JsonProperty("backend_pwd")
	public BackendPwd getBackend_pwd() {
		return this.backend_pwd; }
	public void setBackend_pwd(BackendPwd backend_pwd) {
		this.backend_pwd = backend_pwd; }
	BackendPwd backend_pwd;
	@JsonProperty("max_retransmissions")
	public MaxRetransmissions getMax_retransmissions() {
		return this.max_retransmissions; }
	public void setMax_retransmissions(MaxRetransmissions max_retransmissions) {
		this.max_retransmissions = max_retransmissions; }
	MaxRetransmissions max_retransmissions;
	@JsonProperty("retransmissions_backoff")
	public RetransmissionsBackoff getRetransmissions_backoff() {
		return this.retransmissions_backoff; }
	public void setRetransmissions_backoff(RetransmissionsBackoff retransmissions_backoff) {
		this.retransmissions_backoff = retransmissions_backoff; }
	RetransmissionsBackoff retransmissions_backoff;
}

