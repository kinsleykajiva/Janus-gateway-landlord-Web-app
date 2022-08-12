package africa.jopen.json.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Admin{
    @JsonProperty("admin_base_path")
    public AdminBasePath getAdmin_base_path() {
        return this.admin_base_path; }
    public void setAdmin_base_path(AdminBasePath admin_base_path) {
        this.admin_base_path = admin_base_path; }
    AdminBasePath admin_base_path;
    @JsonProperty("admin_http")
    public AdminHttp getAdmin_http() {
        return this.admin_http; }
    public void setAdmin_http(AdminHttp admin_http) {
        this.admin_http = admin_http; }
    AdminHttp admin_http;
    @JsonProperty("admin_port")
    public AdminPort getAdmin_port() {
        return this.admin_port; }
    public void setAdmin_port(AdminPort admin_port) {
        this.admin_port = admin_port; }
    AdminPort admin_port;
    @JsonProperty("admin_interface")
    public AdminInterface getAdmin_interface() {
        return this.admin_interface; }
    public void setAdmin_interface(AdminInterface admin_interface) {
        this.admin_interface = admin_interface; }
    AdminInterface admin_interface;
    @JsonProperty("admin_ip")
    public AdminIp getAdmin_ip() {
        return this.admin_ip; }
    public void setAdmin_ip(AdminIp admin_ip) {
        this.admin_ip = admin_ip; }
    AdminIp admin_ip;
    @JsonProperty("admin_https")
    public AdminHttps getAdmin_https() {
        return this.admin_https; }
    public void setAdmin_https(AdminHttps admin_https) {
        this.admin_https = admin_https; }
    AdminHttps admin_https;
    @JsonProperty("admin_secure_port")
    public AdminSecurePort getAdmin_secure_port() {
        return this.admin_secure_port; }
    public void setAdmin_secure_port(AdminSecurePort admin_secure_port) {
        this.admin_secure_port = admin_secure_port; }
    AdminSecurePort admin_secure_port;
    @JsonProperty("admin_secure_interface")
    public AdminSecureInterface getAdmin_secure_interface() {
        return this.admin_secure_interface; }
    public void setAdmin_secure_interface(AdminSecureInterface admin_secure_interface) {
        this.admin_secure_interface = admin_secure_interface; }
    AdminSecureInterface admin_secure_interface;
    @JsonProperty("admin_secure_ip")
    public AdminSecureIp getAdmin_secure_ip() {
        return this.admin_secure_ip; }
    public void setAdmin_secure_ip(AdminSecureIp admin_secure_ip) {
        this.admin_secure_ip = admin_secure_ip; }
    AdminSecureIp admin_secure_ip;
    @JsonProperty("admin_acl")
    public AdminAcl getAdmin_acl() {
        return this.admin_acl; }
    public void setAdmin_acl(AdminAcl admin_acl) {
        this.admin_acl = admin_acl; }
    AdminAcl admin_acl;
}
