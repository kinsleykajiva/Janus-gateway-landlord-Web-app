package africa.jopen.json.sip;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess

public class General {
	LocalIp           local_ip;
	SdpIp             sdp_ip;
	LocalMediaIp      local_media_ip;
	KeepaliveInterval keepalive_interval;
	BehindNat         behind_nat;
	UserAgent         user_agent;
	RegisterTtl       register_ttl;
	RtpPortRange      rtp_port_range;
	Events            events;
	DscpAudioRtp      dscp_audio_rtp;
	DscpVideoRtp      dscp_video_rtp;
	SipsCertsDir      sips_certs_dir;
	SipTimerT1x64     sip_timer_t1x64;

	@JsonProperty ("local_ip")
	public LocalIp getLocal_ip () {
		return this.local_ip;
	}

	public void setLocal_ip (LocalIp local_ip) {
		this.local_ip = local_ip;
	}

	@JsonProperty ("sdp_ip")
	public SdpIp getSdp_ip () {
		return this.sdp_ip;
	}

	public void setSdp_ip (SdpIp sdp_ip) {
		this.sdp_ip = sdp_ip;
	}

	@JsonProperty ("local_media_ip")
	public LocalMediaIp getLocal_media_ip () {
		return this.local_media_ip;
	}

	public void setLocal_media_ip (LocalMediaIp local_media_ip) {
		this.local_media_ip = local_media_ip;
	}

	@JsonProperty ("keepalive_interval")
	public KeepaliveInterval getKeepalive_interval () {
		return this.keepalive_interval;
	}

	public void setKeepalive_interval (KeepaliveInterval keepalive_interval) {
		this.keepalive_interval = keepalive_interval;
	}

	@JsonProperty ("behind_nat")
	public BehindNat getBehind_nat () {
		return this.behind_nat;
	}

	public void setBehind_nat (BehindNat behind_nat) {
		this.behind_nat = behind_nat;
	}

	@JsonProperty ("user_agent")
	public UserAgent getUser_agent () {
		return this.user_agent;
	}

	public void setUser_agent (UserAgent user_agent) {
		this.user_agent = user_agent;
	}

	@JsonProperty ("register_ttl")
	public RegisterTtl getRegister_ttl () {
		return this.register_ttl;
	}

	public void setRegister_ttl (RegisterTtl register_ttl) {
		this.register_ttl = register_ttl;
	}

	@JsonProperty ("rtp_port_range")
	public RtpPortRange getRtp_port_range () {
		return this.rtp_port_range;
	}

	public void setRtp_port_range (RtpPortRange rtp_port_range) {
		this.rtp_port_range = rtp_port_range;
	}

	@JsonProperty ("events")
	public Events getEvents () {
		return this.events;
	}

	public void setEvents (Events events) {
		this.events = events;
	}

	@JsonProperty ("dscp_audio_rtp")
	public DscpAudioRtp getDscp_audio_rtp () {
		return this.dscp_audio_rtp;
	}

	public void setDscp_audio_rtp (DscpAudioRtp dscp_audio_rtp) {
		this.dscp_audio_rtp = dscp_audio_rtp;
	}

	@JsonProperty ("dscp_video_rtp")
	public DscpVideoRtp getDscp_video_rtp () {
		return this.dscp_video_rtp;
	}

	public void setDscp_video_rtp (DscpVideoRtp dscp_video_rtp) {
		this.dscp_video_rtp = dscp_video_rtp;
	}

	@JsonProperty ("sips_certs_dir")
	public SipsCertsDir getSips_certs_dir () {
		return this.sips_certs_dir;
	}

	public void setSips_certs_dir (SipsCertsDir sips_certs_dir) {
		this.sips_certs_dir = sips_certs_dir;
	}

	@JsonProperty ("sip_timer_t1x64")
	public SipTimerT1x64 getSip_timer_t1x64 () {
		return this.sip_timer_t1x64;
	}

	public void setSip_timer_t1x64 (SipTimerT1x64 sip_timer_t1x64) {
		this.sip_timer_t1x64 = sip_timer_t1x64;
	}
}