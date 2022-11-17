package africa.jopen.controllers;

import africa.jopen.utils.XUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured (SecurityRule.IS_AUTHENTICATED)
@Controller ("/api/home")
public class JanusGatewayLandlordWebAppController {

	@Get (uri = "/", produces = "text/plain")
	public String index () {
		return "Example Response";
	}

	@Get (uri = "/whats-going-on", produces = "text/plain")
	public HttpResponse knownIssues () {
		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(XUtils.getKnownIssuesSinceStartUp().toString())
				;
	}


}