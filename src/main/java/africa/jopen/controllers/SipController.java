package africa.jopen.controllers;

import africa.jopen.configs.plugins.Sip;
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

@Secured (SecurityRule.IS_AUTHENTICATED)
@Controller ("/api/access/sip")
public class SipController {


	private final Sip janusSipConfig = new Sip();


	@Get (uri = "/current-ssettings")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse getCurrentSettings () throws JsonProcessingException {

		var          res     = janusSipConfig.loadCurrentSettings();
		var          jcfg    = janusSipConfig.loadCurrentSettingsJCFG();
		ObjectMapper mapper  = new ObjectMapper();
		String       jsonStr = mapper.writeValueAsString(res);


		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", true)
								.put("message", "Get current settings")
								.put("jcfg", jcfg)
								.put("data", new JSONObject(jsonStr)).toString()
				);
	}

	@Post (uri = "/update")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse update (HttpHeaders httpHeaders, @Body String jsonBody) {

		boolean hasSaved = janusSipConfig.updateToBuild(jsonBody);
		if (hasSaved) {
			JanusUtils.savePlugin(Sip.FileName);
			if (JanusUtils.restartJanus()) {
				return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
						.body(new JSONObject()
								.put("success", true)
								.put("message", "Updated & Restarted Janus for " + Sip.FileName + " configs").toString())
						;
			} else {
				return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
						.body(new JSONObject()
								.put("success", true)
								.put("message", "Updated Janus for " + Sip.FileName + " configs , But failed to restart Janus sever").toString())
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
