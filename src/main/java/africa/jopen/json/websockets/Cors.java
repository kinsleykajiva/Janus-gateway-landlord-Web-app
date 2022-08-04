package africa.jopen.json.websockets;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Cors{
    @JsonProperty("allow_origin")
    public AllowOrigin getAllow_origin() {
        return this.allow_origin; }
    public void setAllow_origin(AllowOrigin allow_origin) {
        this.allow_origin = allow_origin; }
    AllowOrigin allow_origin;
    @JsonProperty("enforce_cors")
    public EnforceCors getEnforce_cors() {
        return this.enforce_cors; }
    public void setEnforce_cors(EnforceCors enforce_cors) {
        this.enforce_cors = enforce_cors; }
    EnforceCors enforce_cors;
}

