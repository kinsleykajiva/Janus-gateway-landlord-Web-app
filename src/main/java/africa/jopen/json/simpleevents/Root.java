package africa.jopen.json.simpleevents;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Root{
	@JsonProperty("general")
	public General getGeneral() {
		return this.general; }
	public void setGeneral(General general) {
		this.general = general; }
	General general;
}
