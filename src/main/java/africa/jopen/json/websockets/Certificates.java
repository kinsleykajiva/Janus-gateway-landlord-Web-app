package africa.jopen.json.websockets;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Certificates{
    @JsonProperty("cert_pem")
    public CertPem getCert_pem() {
        return this.cert_pem; }
    public void setCert_pem(CertPem cert_pem) {
        this.cert_pem = cert_pem; }
    CertPem cert_pem;
    @JsonProperty("cert_key")
    public CertKey getCert_key() {
        return this.cert_key; }
    public void setCert_key(CertKey cert_key) {
        this.cert_key = cert_key; }
    CertKey cert_key;
    @JsonProperty("cert_pwd")
    public CertPwd getCert_pwd() {
        return this.cert_pwd; }
    public void setCert_pwd(CertPwd cert_pwd) {
        this.cert_pwd = cert_pwd; }
    CertPwd cert_pwd;
    @JsonProperty("ciphers")
    public Ciphers getCiphers() {
        return this.ciphers; }
    public void setCiphers(Ciphers ciphers) {
        this.ciphers = ciphers; }
    Ciphers ciphers;
}