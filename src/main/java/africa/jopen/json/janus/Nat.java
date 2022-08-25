package africa.jopen.json.janus;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ReflectiveAccess

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties (ignoreUnknown = true)
public class Nat {
	StunServer                 stun_server;
	StunPort                   stun_port;
	NiceDebug                  nice_debug;
	FullTrickle                full_trickle;
	IceNomination              ice_nomination;
	IceKeepaliveConncheck      ice_keepalive_conncheck;
	IceLite                    ice_lite;
	IceTcp                     ice_tcp;
	IgnoreMdns                 ignore_mdns;
	Nat11Mapping               nat_1_1_mapping;
	KeepPrivateHost            keep_private_host;
	TurnServer                 turn_server;
	TurnPort                   turn_port;
	TurnType                   turn_type;
	TurnUser                   turn_user;
	TurnPwd                    turn_pwd;
	TurnRestApi                turn_rest_api;
	TurnRestApiKey             turn_rest_api_key;
	TurnRestApiMethod          turn_rest_api_method;
	AllowForceRelay            allow_force_relay;
	IceEnforceList             ice_enforce_list;
	IceIgnoreList              ice_ignore_list;
	IgnoreUnreachableIceServer ignore_unreachable_ice_server;

	@JsonProperty ("stun_server")
	public StunServer getStun_server () {
		return this.stun_server;
	}

	public void setStun_server (StunServer stun_server) {
		this.stun_server = stun_server;
	}

	@JsonProperty ("stun_port")
	public StunPort getStun_port () {
		return this.stun_port;
	}

	public void setStun_port (StunPort stun_port) {
		this.stun_port = stun_port;
	}

	@JsonProperty ("nice_debug")
	public NiceDebug getNice_debug () {
		return this.nice_debug;
	}

	public void setNice_debug (NiceDebug nice_debug) {
		this.nice_debug = nice_debug;
	}

	@JsonProperty ("full_trickle")
	public FullTrickle getFull_trickle () {
		return this.full_trickle;
	}

	public void setFull_trickle (FullTrickle full_trickle) {
		this.full_trickle = full_trickle;
	}

	@JsonProperty ("ice_nomination")
	public IceNomination getIce_nomination () {
		return this.ice_nomination;
	}

	public void setIce_nomination (IceNomination ice_nomination) {
		this.ice_nomination = ice_nomination;
	}

	@JsonProperty ("ice_keepalive_conncheck")
	public IceKeepaliveConncheck getIce_keepalive_conncheck () {
		return this.ice_keepalive_conncheck;
	}

	public void setIce_keepalive_conncheck (IceKeepaliveConncheck ice_keepalive_conncheck) {
		this.ice_keepalive_conncheck = ice_keepalive_conncheck;
	}

	@JsonProperty ("ice_lite")
	public IceLite getIce_lite () {
		return this.ice_lite;
	}

	public void setIce_lite (IceLite ice_lite) {
		this.ice_lite = ice_lite;
	}

	@JsonProperty ("ice_tcp")
	public IceTcp getIce_tcp () {
		return this.ice_tcp;
	}

	public void setIce_tcp (IceTcp ice_tcp) {
		this.ice_tcp = ice_tcp;
	}

	@JsonProperty ("ignore_mdns")
	public IgnoreMdns getIgnore_mdns () {
		return this.ignore_mdns;
	}

	public void setIgnore_mdns (IgnoreMdns ignore_mdns) {
		this.ignore_mdns = ignore_mdns;
	}

	@JsonProperty ("nat_1_1_mapping")
	public Nat11Mapping getNat_1_1_mapping () {
		return this.nat_1_1_mapping;
	}

	public void setNat_1_1_mapping (Nat11Mapping nat_1_1_mapping) {
		this.nat_1_1_mapping = nat_1_1_mapping;
	}

	@JsonProperty ("keep_private_host")
	public KeepPrivateHost getKeep_private_host () {
		return this.keep_private_host;
	}

	public void setKeep_private_host (KeepPrivateHost keep_private_host) {
		this.keep_private_host = keep_private_host;
	}

	@JsonProperty ("turn_server")
	public TurnServer getTurn_server () {
		return this.turn_server;
	}

	public void setTurn_server (TurnServer turn_server) {
		this.turn_server = turn_server;
	}

	@JsonProperty ("turn_port")
	public TurnPort getTurn_port () {
		return this.turn_port;
	}

	public void setTurn_port (TurnPort turn_port) {
		this.turn_port = turn_port;
	}

	@JsonProperty ("turn_type")
	public TurnType getTurn_type () {
		return this.turn_type;
	}

	public void setTurn_type (TurnType turn_type) {
		this.turn_type = turn_type;
	}

	@JsonProperty ("turn_user")
	public TurnUser getTurn_user () {
		return this.turn_user;
	}

	public void setTurn_user (TurnUser turn_user) {
		this.turn_user = turn_user;
	}

	@JsonProperty ("turn_pwd")
	public TurnPwd getTurn_pwd () {
		return this.turn_pwd;
	}

	public void setTurn_pwd (TurnPwd turn_pwd) {
		this.turn_pwd = turn_pwd;
	}

	@JsonProperty ("turn_rest_api")
	public TurnRestApi getTurn_rest_api () {
		return this.turn_rest_api;
	}

	public void setTurn_rest_api (TurnRestApi turn_rest_api) {
		this.turn_rest_api = turn_rest_api;
	}

	@JsonProperty ("turn_rest_api_key")
	public TurnRestApiKey getTurn_rest_api_key () {
		return this.turn_rest_api_key;
	}

	public void setTurn_rest_api_key (TurnRestApiKey turn_rest_api_key) {
		this.turn_rest_api_key = turn_rest_api_key;
	}

	@JsonProperty ("turn_rest_api_method")
	public TurnRestApiMethod getTurn_rest_api_method () {
		return this.turn_rest_api_method;
	}

	public void setTurn_rest_api_method (TurnRestApiMethod turn_rest_api_method) {
		this.turn_rest_api_method = turn_rest_api_method;
	}

	@JsonProperty ("allow_force_relay")
	public AllowForceRelay getAllow_force_relay () {
		return this.allow_force_relay;
	}

	public void setAllow_force_relay (AllowForceRelay allow_force_relay) {
		this.allow_force_relay = allow_force_relay;
	}

	@JsonProperty ("ice_enforce_list")
	public IceEnforceList getIce_enforce_list () {
		return this.ice_enforce_list;
	}

	public void setIce_enforce_list (IceEnforceList ice_enforce_list) {
		this.ice_enforce_list = ice_enforce_list;
	}

	@JsonProperty ("ice_ignore_list")
	public IceIgnoreList getIce_ignore_list () {
		return this.ice_ignore_list;
	}

	public void setIce_ignore_list (IceIgnoreList ice_ignore_list) {
		this.ice_ignore_list = ice_ignore_list;
	}

	@JsonProperty ("ignore_unreachable_ice_server")
	public IgnoreUnreachableIceServer getIgnore_unreachable_ice_server () {
		return this.ignore_unreachable_ice_server;
	}

	public void setIgnore_unreachable_ice_server (IgnoreUnreachableIceServer ignore_unreachable_ice_server) {
		this.ignore_unreachable_ice_server = ignore_unreachable_ice_server;
	}
}