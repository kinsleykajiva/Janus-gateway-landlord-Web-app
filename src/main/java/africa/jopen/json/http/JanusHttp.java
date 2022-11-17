package africa.jopen.json.http;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess

public class JanusHttp {
	General      general;
	Admin        admin;
	Cors         cors;
	Certificates certificates;

	@JsonProperty ("general")
	public General getGeneral () {
		return this.general;
	}

	public void setGeneral (General general) {
		this.general = general;
	}

	@JsonProperty ("admin")
	public Admin getAdmin () {
		return this.admin;
	}

	public void setAdmin (Admin admin) {
		this.admin = admin;
	}

	@JsonProperty ("cors")
	public Cors getCors () {
		return this.cors;
	}

	public void setCors (Cors cors) {
		this.cors = cors;
	}

	@JsonProperty ("certificates")
	public Certificates getCertificates () {
		return this.certificates;
	}

	public void setCertificates (Certificates certificates) {
		this.certificates = certificates;
	}
}
