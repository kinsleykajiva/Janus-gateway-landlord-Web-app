package africa.jopen.json.janus;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@ReflectiveAccess

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nat11Mapping{
    @JsonProperty("required")
    public boolean getRequired() {
        return this.required; }
    public void setRequired(boolean required) {
        this.required = required; }
    boolean required;
    @JsonProperty("commented")
    public boolean getCommented() {
        return this.commented; }
    public void setCommented(boolean commented) {
        this.commented = commented; }
    boolean commented;
    @JsonProperty("comment")
    public String getComment() {
        return this.comment; }
    public void setComment(String comment) {
        this.comment = comment; }
    String comment;
    @JsonProperty("lineValue")
    public String getLineValue() {
        return this.lineValue; }
    public void setLineValue(String lineValue) {
        this.lineValue = lineValue; }
    String lineValue;
}