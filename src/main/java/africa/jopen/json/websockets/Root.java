package africa.jopen.json.websockets;
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
    @JsonProperty("admin")
    public Admin getAdmin() {
        return this.admin; }
    public void setAdmin(Admin admin) {
        this.admin = admin; }
    Admin admin;
    @JsonProperty("cors")
    public Cors getCors() {
        return this.cors; }
    public void setCors(Cors cors) {
        this.cors = cors; }
    Cors cors;
    @JsonProperty("certificates")
    public Certificates getCertificates() {
        return this.certificates; }
    public void setCertificates(Certificates certificates) {
        this.certificates = certificates; }
    Certificates certificates;
}
