package africa.jopen.controllers;


import africa.jopen.configs.Janus;
import africa.jopen.configs.eventhandlers.SampleEventHandler;
import africa.jopen.configs.plugins.Sip;
import africa.jopen.configs.transports.Http;
import africa.jopen.configs.transports.Websockets;
import africa.jopen.utils.JanusUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.json.JSONObject;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

@Secured (SecurityRule.IS_AUTHENTICATED)
@Controller ("/api/access/janus")
public class JanusController {
	private final Logger logger      = getLogger(JanusController.class.getSimpleName());
	private final Janus  janusConfig = new Janus();


	@Get (uri = "/current-ssettings")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse getCurrentSettings () throws JsonProcessingException {


		var res  = janusConfig.loadCurrentSettings();
		var jcfg = janusConfig.loadCurrentSettingsJCFG();

		ObjectMapper mapper  = new ObjectMapper();
		String       jsonStr = mapper.writeValueAsString(res);


		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", true)
								.put("message", "Get current settings")
								.put("jcfg", jcfg)
								.put("data", new JSONObject(jsonStr)
								).toString()
				);
	}

	@Get (uri = "/check-if-janus-installed")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse checkIfjanusIsInstalled () {

		var result = JanusUtils.checkIfJanusIsInstalled().replaceAll("\\s+", " ");

		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", true)
								.put("message", "Checking if janus is install on the sever")
								.put("data",
										new JSONObject()
												.put("check", result)
												.put("explanation", result.contains("Not Found") ? "Janus is not installed please install using '/install-janus-server'" :
														"Janus Gateway Server is installed on the server")
								).toString()
				);
	}

	@Post (uri = "/install-janus-server")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse installJanusSever () {
		JanusUtils.installJanus();
		return HttpResponse
				.ok()
				.contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", true)
								.put("message", "Installed Janus")
								.put("data",
										"null"
								).toString()
				);
	}

	@Post (uri = "/restart-janus-server")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse restartJanusSever () {

		var res = JanusUtils.restartJanus();

		return HttpResponse
				.ok()
				.contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", res)
								.put("message", "Restart Janus Instance Attempt")
								.put("data",
										new JSONObject()
												.put("explanation", res ? "Has Restarted Janus Instance " :
														"Failed to restart the instance,please try again or ssh into the instance if this persists")
								).toString()
				);
	}

	@Post (uri = "/reset")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse saveDefaults () {

		janusConfig.saveToLocalBackCopy();

		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(new JSONObject()
						.put("success", true)
						.put("message", "Reset to Defaults").toString())
				;
	}

	@Post (uri = "/load-afresh/reset")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse saveLoadAfresh () {
		new Janus().saveToLocalBackCopy();
		new Http().saveFromDefaults();
		new Websockets().saveFromDefaults();
		new Sip().saveFromDefaults();
		new SampleEventHandler().saveFromDefaults();

		return HttpResponse
				.ok()
				.contentType(MediaType.TEXT_JSON_TYPE)
				.body(new JSONObject()
						.put("success", true)
						.put("message", "Reset to Defaults:afresh").toString())
				;
	}

	@Post (uri = "/update")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse update (HttpHeaders httpHeaders, @Body String jsonBody) {

		var bodyy = new JSONObject(jsonBody);
		//  logInfo("Start" + bodyy);
		boolean hasSaved = janusConfig.updateToBuild(jsonBody);
		if (hasSaved) {
			JanusUtils.savePlugin(Janus.FileName);
			if (JanusUtils.restartJanus()) {
				return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
						.body(new JSONObject()
								.put("success", true)
								.put("message", "Updated & Restarted Janus for " + Janus.FileName + " configs").toString())
						;
			} else {
				return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
						.body(new JSONObject()
								.put("success", true)
								.put("message", "Updated Janus for " + Janus.FileName + " configs , But failed to restart Janus sever").toString())
						;
			}


		}

		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(new JSONObject()
						.put("success", false)
						.put("message", "Failed to full execute,please review logs").toString())
				;
	}


}
