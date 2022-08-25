package africa.jopen.json.serverinformation;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Plugins {
	JanusPluginAudiobridge janusPluginAudiobridge;
	JanusPluginRecordplay  janusPluginRecordplay;
	JanusPluginTextroom    janusPluginTextroom;
	JanusPluginNosip       janusPluginNosip;
	JanusPluginVoicemail   janusPluginVoicemail;
	JanusPluginSip         janusPluginSip;
	JanusPluginVideocall   janusPluginVideocall;
	JanusPluginStreaming   janusPluginStreaming;
	JanusPluginEchotest    janusPluginEchotest;
	JanusPluginVideoroom   janusPluginVideoroom;

	@JsonProperty ("janus.plugin.audiobridge")
	public JanusPluginAudiobridge getJanusPluginAudiobridge () {
		return this.janusPluginAudiobridge;
	}

	public void setJanusPluginAudiobridge (JanusPluginAudiobridge janusPluginAudiobridge) {
		this.janusPluginAudiobridge = janusPluginAudiobridge;
	}

	@JsonProperty ("janus.plugin.recordplay")
	public JanusPluginRecordplay getJanusPluginRecordplay () {
		return this.janusPluginRecordplay;
	}

	public void setJanusPluginRecordplay (JanusPluginRecordplay janusPluginRecordplay) {
		this.janusPluginRecordplay = janusPluginRecordplay;
	}

	@JsonProperty ("janus.plugin.textroom")
	public JanusPluginTextroom getJanusPluginTextroom () {
		return this.janusPluginTextroom;
	}

	public void setJanusPluginTextroom (JanusPluginTextroom janusPluginTextroom) {
		this.janusPluginTextroom = janusPluginTextroom;
	}

	@JsonProperty ("janus.plugin.nosip")
	public JanusPluginNosip getJanusPluginNosip () {
		return this.janusPluginNosip;
	}

	public void setJanusPluginNosip (JanusPluginNosip janusPluginNosip) {
		this.janusPluginNosip = janusPluginNosip;
	}

	@JsonProperty ("janus.plugin.voicemail")
	public JanusPluginVoicemail getJanusPluginVoicemail () {
		return this.janusPluginVoicemail;
	}

	public void setJanusPluginVoicemail (JanusPluginVoicemail janusPluginVoicemail) {
		this.janusPluginVoicemail = janusPluginVoicemail;
	}

	@JsonProperty ("janus.plugin.sip")
	public JanusPluginSip getJanusPluginSip () {
		return this.janusPluginSip;
	}

	public void setJanusPluginSip (JanusPluginSip janusPluginSip) {
		this.janusPluginSip = janusPluginSip;
	}

	@JsonProperty ("janus.plugin.videocall")
	public JanusPluginVideocall getJanusPluginVideocall () {
		return this.janusPluginVideocall;
	}

	public void setJanusPluginVideocall (JanusPluginVideocall janusPluginVideocall) {
		this.janusPluginVideocall = janusPluginVideocall;
	}

	@JsonProperty ("janus.plugin.streaming")
	public JanusPluginStreaming getJanusPluginStreaming () {
		return this.janusPluginStreaming;
	}

	public void setJanusPluginStreaming (JanusPluginStreaming janusPluginStreaming) {
		this.janusPluginStreaming = janusPluginStreaming;
	}

	@JsonProperty ("janus.plugin.echotest")
	public JanusPluginEchotest getJanusPluginEchotest () {
		return this.janusPluginEchotest;
	}

	public void setJanusPluginEchotest (JanusPluginEchotest janusPluginEchotest) {
		this.janusPluginEchotest = janusPluginEchotest;
	}

	@JsonProperty ("janus.plugin.videoroom")
	public JanusPluginVideoroom getJanusPluginVideoroom () {
		return this.janusPluginVideoroom;
	}

	public void setJanusPluginVideoroom (JanusPluginVideoroom janusPluginVideoroom) {
		this.janusPluginVideoroom = janusPluginVideoroom;
	}
}
