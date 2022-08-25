package africa.jopen.json.janus;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;
import lombok.AllArgsConstructor;
import lombok.Data;

@ReflectiveAccess
@Data
@AllArgsConstructor
@JsonIgnoreProperties (ignoreUnknown = true)
public class JanusObject {

	General      general;
	Certificates certificates;
	Media        media;
	Nat          nat;
	Loggers      loggers;
	Plugins      plugins;
	Transports   transports;
	Events       events;

	public JanusObject () {
	}

	@JsonProperty ("general")
	public General getGeneral () {
		return this.general;
	}

	public void setGeneral (General general) {
		this.general = general;
	}

	@JsonProperty ("certificates")
	public Certificates getCertificates () {
		return this.certificates;
	}

	public void setCertificates (Certificates certificates) {
		this.certificates = certificates;
	}

	@JsonProperty ("media")
	public Media getMedia () {
		return this.media;
	}

	public void setMedia (Media media) {
		this.media = media;
	}

	@JsonProperty ("nat")
	public Nat getNat () {
		return this.nat;
	}

	public void setNat (Nat nat) {
		this.nat = nat;
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
}
