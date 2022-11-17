package africa.jopen.json.janus;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess

public class Media {
	Ipv6              ipv6;
	Ipv6Linklocal     ipv6_linklocal;
	MinNackQueue      min_nack_queue;
	RtpPortRange      rtp_port_range;
	DtlsMtu           dtls_mtu;
	NoMediaTimer      no_media_timer;
	SlowlinkThreshold slowlink_threshold;
	TwccPeriod        twcc_period;
	DtlsTimeout       dtls_timeout;
	NackOptimizations nack_optimizations;
	Dscp              dscp;

	@JsonProperty ("ipv6")
	public Ipv6 getIpv6 () {
		return this.ipv6;
	}

	public void setIpv6 (Ipv6 ipv6) {
		this.ipv6 = ipv6;
	}

	@JsonProperty ("ipv6_linklocal")
	public Ipv6Linklocal getIpv6_linklocal () {
		return this.ipv6_linklocal;
	}

	public void setIpv6_linklocal (Ipv6Linklocal ipv6_linklocal) {
		this.ipv6_linklocal = ipv6_linklocal;
	}

	@JsonProperty ("min_nack_queue")
	public MinNackQueue getMin_nack_queue () {
		return this.min_nack_queue;
	}

	public void setMin_nack_queue (MinNackQueue min_nack_queue) {
		this.min_nack_queue = min_nack_queue;
	}

	@JsonProperty ("rtp_port_range")
	public RtpPortRange getRtp_port_range () {
		return this.rtp_port_range;
	}

	public void setRtp_port_range (RtpPortRange rtp_port_range) {
		this.rtp_port_range = rtp_port_range;
	}

	@JsonProperty ("dtls_mtu")
	public DtlsMtu getDtls_mtu () {
		return this.dtls_mtu;
	}

	public void setDtls_mtu (DtlsMtu dtls_mtu) {
		this.dtls_mtu = dtls_mtu;
	}

	@JsonProperty ("no_media_timer")
	public NoMediaTimer getNo_media_timer () {
		return this.no_media_timer;
	}

	public void setNo_media_timer (NoMediaTimer no_media_timer) {
		this.no_media_timer = no_media_timer;
	}

	@JsonProperty ("slowlink_threshold")
	public SlowlinkThreshold getSlowlink_threshold () {
		return this.slowlink_threshold;
	}

	public void setSlowlink_threshold (SlowlinkThreshold slowlink_threshold) {
		this.slowlink_threshold = slowlink_threshold;
	}

	@JsonProperty ("twcc_period")
	public TwccPeriod getTwcc_period () {
		return this.twcc_period;
	}

	public void setTwcc_period (TwccPeriod twcc_period) {
		this.twcc_period = twcc_period;
	}

	@JsonProperty ("dtls_timeout")
	public DtlsTimeout getDtls_timeout () {
		return this.dtls_timeout;
	}

	public void setDtls_timeout (DtlsTimeout dtls_timeout) {
		this.dtls_timeout = dtls_timeout;
	}

	@JsonProperty ("nack_optimizations")
	public NackOptimizations getNack_optimizations () {
		return this.nack_optimizations;
	}

	public void setNack_optimizations (NackOptimizations nack_optimizations) {
		this.nack_optimizations = nack_optimizations;
	}

	@JsonProperty ("dscp")
	public Dscp getDscp () {
		return this.dscp;
	}

	public void setDscp (Dscp dscp) {
		this.dscp = dscp;
	}
}