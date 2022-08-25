package africa.jopen.json.sip;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess


public class JanusSip {
	General general;

	@JsonProperty ("general")
	public General getGeneral () {
		return this.general;
	}

	public void setGeneral (General general) {
		this.general = general;
	}
}