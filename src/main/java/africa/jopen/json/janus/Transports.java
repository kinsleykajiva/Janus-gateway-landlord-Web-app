package africa.jopen.json.janus;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess

public class Transports{
    @JsonProperty("disable")
    public Disable getDisable() {
        return this.disable; }
    public void setDisable(Disable disable) {
        this.disable = disable; }
    Disable disable;
}
