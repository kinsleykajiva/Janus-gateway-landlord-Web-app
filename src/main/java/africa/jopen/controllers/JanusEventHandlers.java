package africa.jopen.controllers;


import africa.jopen.database.mongodb.LazyMongoDB;
import com.github.wnameless.json.flattener.JsonFlattener;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.json.JSONObject;


@Controller ("/api/janus/events")
public class JanusEventHandlers {



	@Get (uri = "/feeds/event")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse getEvents() {
		var          db    =  LazyMongoDB.getInstance();
		var jArray = db.getEvents("sip_events");


		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", true)
								.put("message", "Saved Events")
								.put("data",new JSONObject()
										.put("sip_events",jArray))
								.toString()
				);

	}



	@Post (uri = "/new")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse newEvents(HttpHeaders httpHeaders, @Body String jsonBody) {
		var db =  LazyMongoDB.getInstance();
		String jsonStr = JsonFlattener.flatten(jsonBody);
		db.saveEvent("sip_events",jsonStr);

		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", true)
								.put("message", "Saved Event")
								.toString()
				);

	}






}
