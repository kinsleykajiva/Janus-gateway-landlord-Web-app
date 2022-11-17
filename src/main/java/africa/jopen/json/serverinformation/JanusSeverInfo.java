package africa.jopen.json.serverinformation;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class JanusSeverInfo {
	String       janus;
	String       transaction;
	String       name;
	int          version;
	String       version_string;
	String       author;
	String       commitHash;
	String       compileTime;
	boolean      logToStdout;
	boolean      logToFile;
	boolean      data_channels;
	boolean      acceptingNewSessions;
	int          sessionTimeout;
	int          reclaimSessionTimeout;
	int          candidatesTimeout;
	String       serverName;
	String       localIp;
	boolean      ipv6;
	boolean      iceLite;
	boolean      iceTcp;
	String       iceNomination;
	boolean      iceKeepaliveConncheck;
	boolean      fullTrickle;
	boolean      mdnsEnabled;
	int          minNackQueue;
	boolean      nackOptimizations;
	int          twccPeriod;
	int          dtlsMtu;
	int          staticEventLoops;
	boolean      api_secret;
	boolean      auth_token;
	boolean      event_handlers;
	boolean      opaqueid_in_api;
	Dependencies dependencies;
	Transports   transports;
	Events       events;
	Loggers      loggers;
	Plugins      plugins;

	@JsonProperty ("janus")
	public String getJanus () {
		return this.janus;
	}

	public void setJanus (String janus) {
		this.janus = janus;
	}

	@JsonProperty ("transaction")
	public String getTransaction () {
		return this.transaction;
	}

	public void setTransaction (String transaction) {
		this.transaction = transaction;
	}

	@JsonProperty ("name")
	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	@JsonProperty ("version")
	public int getVersion () {
		return this.version;
	}

	public void setVersion (int version) {
		this.version = version;
	}

	@JsonProperty ("version_string")
	public String getVersion_string () {
		return this.version_string;
	}

	public void setVersion_string (String version_string) {
		this.version_string = version_string;
	}

	@JsonProperty ("author")
	public String getAuthor () {
		return this.author;
	}

	public void setAuthor (String author) {
		this.author = author;
	}

	@JsonProperty ("commit-hash")
	public String getCommitHash () {
		return this.commitHash;
	}

	public void setCommitHash (String commitHash) {
		this.commitHash = commitHash;
	}

	@JsonProperty ("compile-time")
	public String getCompileTime () {
		return this.compileTime;
	}

	public void setCompileTime (String compileTime) {
		this.compileTime = compileTime;
	}

	@JsonProperty ("log-to-stdout")
	public boolean getLogToStdout () {
		return this.logToStdout;
	}

	public void setLogToStdout (boolean logToStdout) {
		this.logToStdout = logToStdout;
	}

	@JsonProperty ("log-to-file")
	public boolean getLogToFile () {
		return this.logToFile;
	}

	public void setLogToFile (boolean logToFile) {
		this.logToFile = logToFile;
	}

	@JsonProperty ("data_channels")
	public boolean getData_channels () {
		return this.data_channels;
	}

	public void setData_channels (boolean data_channels) {
		this.data_channels = data_channels;
	}

	@JsonProperty ("accepting-new-sessions")
	public boolean getAcceptingNewSessions () {
		return this.acceptingNewSessions;
	}

	public void setAcceptingNewSessions (boolean acceptingNewSessions) {
		this.acceptingNewSessions = acceptingNewSessions;
	}

	@JsonProperty ("session-timeout")
	public int getSessionTimeout () {
		return this.sessionTimeout;
	}

	public void setSessionTimeout (int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	@JsonProperty ("reclaim-session-timeout")
	public int getReclaimSessionTimeout () {
		return this.reclaimSessionTimeout;
	}

	public void setReclaimSessionTimeout (int reclaimSessionTimeout) {
		this.reclaimSessionTimeout = reclaimSessionTimeout;
	}

	@JsonProperty ("candidates-timeout")
	public int getCandidatesTimeout () {
		return this.candidatesTimeout;
	}

	public void setCandidatesTimeout (int candidatesTimeout) {
		this.candidatesTimeout = candidatesTimeout;
	}

	@JsonProperty ("server-name")
	public String getServerName () {
		return this.serverName;
	}

	public void setServerName (String serverName) {
		this.serverName = serverName;
	}

	@JsonProperty ("local-ip")
	public String getLocalIp () {
		return this.localIp;
	}

	public void setLocalIp (String localIp) {
		this.localIp = localIp;
	}

	@JsonProperty ("ipv6")
	public boolean getIpv6 () {
		return this.ipv6;
	}

	public void setIpv6 (boolean ipv6) {
		this.ipv6 = ipv6;
	}

	@JsonProperty ("ice-lite")
	public boolean getIceLite () {
		return this.iceLite;
	}

	public void setIceLite (boolean iceLite) {
		this.iceLite = iceLite;
	}

	@JsonProperty ("ice-tcp")
	public boolean getIceTcp () {
		return this.iceTcp;
	}

	public void setIceTcp (boolean iceTcp) {
		this.iceTcp = iceTcp;
	}

	@JsonProperty ("ice-nomination")
	public String getIceNomination () {
		return this.iceNomination;
	}

	public void setIceNomination (String iceNomination) {
		this.iceNomination = iceNomination;
	}

	@JsonProperty ("ice-keepalive-conncheck")
	public boolean getIceKeepaliveConncheck () {
		return this.iceKeepaliveConncheck;
	}

	public void setIceKeepaliveConncheck (boolean iceKeepaliveConncheck) {
		this.iceKeepaliveConncheck = iceKeepaliveConncheck;
	}

	@JsonProperty ("full-trickle")
	public boolean getFullTrickle () {
		return this.fullTrickle;
	}

	public void setFullTrickle (boolean fullTrickle) {
		this.fullTrickle = fullTrickle;
	}

	@JsonProperty ("mdns-enabled")
	public boolean getMdnsEnabled () {
		return this.mdnsEnabled;
	}

	public void setMdnsEnabled (boolean mdnsEnabled) {
		this.mdnsEnabled = mdnsEnabled;
	}

	@JsonProperty ("min-nack-queue")
	public int getMinNackQueue () {
		return this.minNackQueue;
	}

	public void setMinNackQueue (int minNackQueue) {
		this.minNackQueue = minNackQueue;
	}

	@JsonProperty ("nack-optimizations")
	public boolean getNackOptimizations () {
		return this.nackOptimizations;
	}

	public void setNackOptimizations (boolean nackOptimizations) {
		this.nackOptimizations = nackOptimizations;
	}

	@JsonProperty ("twcc-period")
	public int getTwccPeriod () {
		return this.twccPeriod;
	}

	public void setTwccPeriod (int twccPeriod) {
		this.twccPeriod = twccPeriod;
	}

	@JsonProperty ("dtls-mtu")
	public int getDtlsMtu () {
		return this.dtlsMtu;
	}

	public void setDtlsMtu (int dtlsMtu) {
		this.dtlsMtu = dtlsMtu;
	}

	@JsonProperty ("static-event-loops")
	public int getStaticEventLoops () {
		return this.staticEventLoops;
	}

	public void setStaticEventLoops (int staticEventLoops) {
		this.staticEventLoops = staticEventLoops;
	}

	@JsonProperty ("api_secret")
	public boolean getApi_secret () {
		return this.api_secret;
	}

	public void setApi_secret (boolean api_secret) {
		this.api_secret = api_secret;
	}

	@JsonProperty ("auth_token")
	public boolean getAuth_token () {
		return this.auth_token;
	}

	public void setAuth_token (boolean auth_token) {
		this.auth_token = auth_token;
	}

	@JsonProperty ("event_handlers")
	public boolean getEvent_handlers () {
		return this.event_handlers;
	}

	public void setEvent_handlers (boolean event_handlers) {
		this.event_handlers = event_handlers;
	}

	@JsonProperty ("opaqueid_in_api")
	public boolean getOpaqueid_in_api () {
		return this.opaqueid_in_api;
	}

	public void setOpaqueid_in_api (boolean opaqueid_in_api) {
		this.opaqueid_in_api = opaqueid_in_api;
	}

	@JsonProperty ("dependencies")
	public Dependencies getDependencies () {
		return this.dependencies;
	}

	public void setDependencies (Dependencies dependencies) {
		this.dependencies = dependencies;
	}

	@JsonProperty ("transports")
	public Transports getTransports () {
		return this.transports;
	}

	public void setTransports (Transports transports) {
		this.transports = transports;
	}

	@JsonProperty ("events")
	public Events getEvents () {
		return this.events;
	}

	public void setEvents (Events events) {
		this.events = events;
	}

	@JsonProperty ("loggers")
	public Loggers getLoggers () {
		return this.loggers;
	}

	public void setLoggers (Loggers loggers) {
		this.loggers = loggers;
	}

	@JsonProperty ("plugins")
	public Plugins getPlugins () {
		return this.plugins;
	}

	public void setPlugins (Plugins plugins) {
		this.plugins = plugins;
	}
}