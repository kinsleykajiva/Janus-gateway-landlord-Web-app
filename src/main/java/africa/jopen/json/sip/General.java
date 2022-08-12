package africa.jopen.json.sip;



import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess

public class General{
    @JsonProperty("local_ip")
    public LocalIp getLocal_ip() {
        return this.local_ip; }
    public void setLocal_ip(LocalIp local_ip) {
        this.local_ip = local_ip; }
    LocalIp local_ip;
    @JsonProperty("sdp_ip")
    public SdpIp getSdp_ip() {
        return this.sdp_ip; }
    public void setSdp_ip(SdpIp sdp_ip) {
        this.sdp_ip = sdp_ip; }
    SdpIp sdp_ip;
    @JsonProperty("keepalive_interval")
    public KeepaliveInterval getKeepalive_interval() {
        return this.keepalive_interval; }
    public void setKeepalive_interval(KeepaliveInterval keepalive_interval) {
        this.keepalive_interval = keepalive_interval; }
    KeepaliveInterval keepalive_interval;
    @JsonProperty("behind_nat")
    public BehindNat getBehind_nat() {
        return this.behind_nat; }
    public void setBehind_nat(BehindNat behind_nat) {
        this.behind_nat = behind_nat; }
    BehindNat behind_nat;
    @JsonProperty("user_agent")
    public UserAgent getUser_agent() {
        return this.user_agent; }
    public void setUser_agent(UserAgent user_agent) {
        this.user_agent = user_agent; }
    UserAgent user_agent;
    @JsonProperty("register_ttl")
    public RegisterTtl getRegister_ttl() {
        return this.register_ttl; }
    public void setRegister_ttl(RegisterTtl register_ttl) {
        this.register_ttl = register_ttl; }
    RegisterTtl register_ttl;
    @JsonProperty("rtp_port_range")
    public RtpPortRange getRtp_port_range() {
        return this.rtp_port_range; }
    public void setRtp_port_range(RtpPortRange rtp_port_range) {
        this.rtp_port_range = rtp_port_range; }
    RtpPortRange rtp_port_range;
    @JsonProperty("events")
    public Events getEvents() {
        return this.events; }
    public void setEvents(Events events) {
        this.events = events; }
    Events events;
    @JsonProperty("dscp_audio_rtp")
    public DscpAudioRtp getDscp_audio_rtp() {
        return this.dscp_audio_rtp; }
    public void setDscp_audio_rtp(DscpAudioRtp dscp_audio_rtp) {
        this.dscp_audio_rtp = dscp_audio_rtp; }
    DscpAudioRtp dscp_audio_rtp;
    @JsonProperty("dscp_video_rtp")
    public DscpVideoRtp getDscp_video_rtp() {
        return this.dscp_video_rtp; }
    public void setDscp_video_rtp(DscpVideoRtp dscp_video_rtp) {
        this.dscp_video_rtp = dscp_video_rtp; }
    DscpVideoRtp dscp_video_rtp;
}
