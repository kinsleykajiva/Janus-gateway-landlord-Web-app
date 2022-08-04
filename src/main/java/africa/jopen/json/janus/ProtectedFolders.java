package africa.jopen.json.janus;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

import java.util.ArrayList;
@ReflectiveAccess

public class ProtectedFolders{
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
    public ArrayList<String> getLineValue() {
        return this.lineValue; }
    public void setLineValue(ArrayList<String> lineValue) {
        this.lineValue = lineValue; }
    ArrayList<String> lineValue;
}
