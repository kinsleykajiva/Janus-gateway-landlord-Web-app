package africa.jopen.json.websockets;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Admin {
	AdminWs          admin_ws;
	AdminWsPort      admin_ws_port;
	AdminWsInterface admin_ws_interface;
	AdminWsIp        admin_ws_ip;
	AdminWsAcl       admin_ws_acl;

	@JsonProperty ("admin_ws")
	public AdminWs getAdmin_ws () {
		return this.admin_ws;
	}

	public void setAdmin_ws (AdminWs admin_ws) {
		this.admin_ws = admin_ws;
	}

	@JsonProperty ("admin_ws_port")
	public AdminWsPort getAdmin_ws_port () {
		return this.admin_ws_port;
	}

	public void setAdmin_ws_port (AdminWsPort admin_ws_port) {
		this.admin_ws_port = admin_ws_port;
	}

	@JsonProperty ("admin_ws_interface")
	public AdminWsInterface getAdmin_ws_interface () {
		return this.admin_ws_interface;
	}

	public void setAdmin_ws_interface (AdminWsInterface admin_ws_interface) {
		this.admin_ws_interface = admin_ws_interface;
	}

	@JsonProperty ("admin_ws_ip")
	public AdminWsIp getAdmin_ws_ip () {
		return this.admin_ws_ip;
	}

	public void setAdmin_ws_ip (AdminWsIp admin_ws_ip) {
		this.admin_ws_ip = admin_ws_ip;
	}

	@JsonProperty ("admin_ws_acl")
	public AdminWsAcl getAdmin_ws_acl () {
		return this.admin_ws_acl;
	}

	public void setAdmin_ws_acl (AdminWsAcl admin_ws_acl) {
		this.admin_ws_acl = admin_ws_acl;
	}
}
