package africa.jopen.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured (SecurityRule.IS_ANONYMOUS)
@Controller ("/")
public class IndexbAppController {

	@Get (uri = "/", produces = "text/plain")
	public String index () {
		return "HealthCheck";
	}


}