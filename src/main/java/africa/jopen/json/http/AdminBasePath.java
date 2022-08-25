package africa.jopen.json.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess

public class AdminBasePath {
	boolean required;
	boolean commented;
	String  comment;
	String  lineValue;

	@JsonProperty ("required")
	public boolean getRequired () {
		return this.required;
	}

	public void setRequired (boolean required) {
		this.required = required;
	}

	@JsonProperty ("commented")
	public boolean getCommented () {
		return this.commented;
	}

	public void setCommented (boolean commented) {
		this.commented = commented;
	}

	@JsonProperty ("comment")
	public String getComment () {
		return this.comment;
	}

	public void setComment (String comment) {
		this.comment = comment;
	}

	@JsonProperty ("lineValue")
	public String getLineValue () {
		return this.lineValue;
	}

	public void setLineValue (String lineValue) {
		this.lineValue = lineValue;
	}
}