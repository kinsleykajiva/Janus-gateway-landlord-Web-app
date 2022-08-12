package africa.jopen.json.serverinformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class JanusPluginEchotest{
    @JsonProperty("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }
    String name;
    @JsonProperty("author")
    public String getAuthor() {
        return this.author; }
    public void setAuthor(String author) {
        this.author = author; }
    String author;
    @JsonProperty("description")
    public String getDescription() {
        return this.description; }
    public void setDescription(String description) {
        this.description = description; }
    String description;
    @JsonProperty("version_string")
    public String getVersion_string() {
        return this.version_string; }
    public void setVersion_string(String version_string) {
        this.version_string = version_string; }
    String version_string;
    @JsonProperty("version")
    public int getVersion() {
        return this.version; }
    public void setVersion(int version) {
        this.version = version; }
    int version;
}