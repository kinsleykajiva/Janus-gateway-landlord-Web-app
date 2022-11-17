package africa.jopen.json.janus;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess

public class Events {
	Broadcast         broadcast;
	CombineMediaStats combine_media_stats;
	Disable           disable;
	StatsPeriod       stats_period;

	@JsonProperty ("broadcast")
	public Broadcast getBroadcast () {
		return this.broadcast;
	}

	public void setBroadcast (Broadcast broadcast) {
		this.broadcast = broadcast;
	}

	@JsonProperty ("combine_media_stats")
	public CombineMediaStats getCombine_media_stats () {
		return this.combine_media_stats;
	}

	public void setCombine_media_stats (CombineMediaStats combine_media_stats) {
		this.combine_media_stats = combine_media_stats;
	}

	@JsonProperty ("disable")
	public Disable getDisable () {
		return this.disable;
	}

	public void setDisable (Disable disable) {
		this.disable = disable;
	}

	@JsonProperty ("stats_period")
	public StatsPeriod getStats_period () {
		return this.stats_period;
	}

	public void setStats_period (StatsPeriod stats_period) {
		this.stats_period = stats_period;
	}
}