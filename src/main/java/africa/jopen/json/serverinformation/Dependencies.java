package africa.jopen.json.serverinformation;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Dependencies{
    @JsonProperty("glib2")
    public String getGlib2() {
        return this.glib2; }
    public void setGlib2(String glib2) {
        this.glib2 = glib2; }
    String glib2;
    @JsonProperty("jansson")
    public String getJansson() {
        return this.jansson; }
    public void setJansson(String jansson) {
        this.jansson = jansson; }
    String jansson;
    @JsonProperty("libnice")
    public String getLibnice() {
        return this.libnice; }
    public void setLibnice(String libnice) {
        this.libnice = libnice; }
    String libnice;
    @JsonProperty("libsrtp")
    public String getLibsrtp() {
        return this.libsrtp; }
    public void setLibsrtp(String libsrtp) {
        this.libsrtp = libsrtp; }
    String libsrtp;
    @JsonProperty("libcurl")
    public String getLibcurl() {
        return this.libcurl; }
    public void setLibcurl(String libcurl) {
        this.libcurl = libcurl; }
    String libcurl;
    @JsonProperty("crypto")
    public String getCrypto() {
        return this.crypto; }
    public void setCrypto(String crypto) {
        this.crypto = crypto; }
    String crypto;
}