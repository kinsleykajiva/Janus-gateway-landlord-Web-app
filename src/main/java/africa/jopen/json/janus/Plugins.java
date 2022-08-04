package africa.jopen.json.janus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

import java.util.ArrayList;

@ReflectiveAccess
public class Plugins{
    @JsonProperty("disable")
    public Disable getDisable() {
        return this.disable; }
    public void setDisable(Disable disable) {
        this.disable = disable; }
    Disable disable;
}
