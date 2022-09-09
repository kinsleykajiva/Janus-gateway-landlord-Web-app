package africa.jopen.controllers;


import africa.jopen.configs.eventhandlers.SampleEventHandler;
import africa.jopen.database.mongodb.LazyMongoDB;
import africa.jopen.events.MessageEvent;
import africa.jopen.utils.HttpClientUtils;
import africa.jopen.utils.JanusUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.JsonFlattener;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Secured (SecurityRule.IS_ANONYMOUS)
@Controller ("/api/janus/events")
public class JanusEventHandlers {
	ExecutorService executorService1 = Executors.newSingleThreadExecutor();
	private final SampleEventHandler sampleEventHandler = new SampleEventHandler();
	Logger logger = Logger.getLogger(JanusEventHandlers.class.getSimpleName());

	@Get (uri = "/current-ssettings")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse getCurrentSettings () throws JsonProcessingException {

		var          res     = sampleEventHandler.loadCurrentSettings();
		var          jcfg    = sampleEventHandler.loadCurrentSettingsJCFG();
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

		boolean hasSaved = sampleEventHandler.updateToBuild(jsonBody);
		if (hasSaved) {
			JanusUtils.savePlugin(SampleEventHandler.FileName);
			if (JanusUtils.restartJanus()) {
				return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
						.body(new JSONObject()
								.put("success", true)
								.put("message", "Updated & Restarted Janus for " + SampleEventHandler.FileName + " configs").toString())
						;
			} else {
				return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
						.body(new JSONObject()
								.put("success", true)
								.put("message", "Updated Janus for " + SampleEventHandler.FileName + " configs , But failed to restart Janus sever").toString())
						;
			}


		}

		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(new JSONObject()
						.put("success", false)
						.put("message", "Failed to full execute,please review logs").toString())
				;
	}

	@Get (uri = "/feeds/event")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse getEvents () {
		var db     = LazyMongoDB.getInstance();
		var jArray = db.getEvents("sip_events");


		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", true)
								.put("message", "Saved Events")
								.put("data", new JSONObject()
										.put("sip_events", jArray))
								.toString()
				);

	}
	@Get (uri = "/feeds/event-filter/{filterColum}/{value}",value = "/bob/{name}/params")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse getEventsWithFilters ( @QueryValue String filterColum,  @QueryValue String value  ) {
		var db     = LazyMongoDB.getInstance();
		var jArray = db.getEventsFilter("sip_events" , filterColum,value );


		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", true)
								.put("message", "Saved Events")
								.put("data", new JSONObject()
										.put("sip_events", jArray))
								.toString()
				);

	}


	@Post (uri = "/new")
	@Produces (MediaType.TEXT_PLAIN)
	public HttpResponse newEvents (HttpHeaders httpHeaders, @Body String jsonBody)  {
		var    db      = LazyMongoDB.getInstance();
		executorService1.submit(()->{
			try {
				Map<String,String> map = new HashMap<>();
				map.put("events", jsonBody);
				ObjectMapper objectMapper = new ObjectMapper();
				String requestBody = objectMapper
						.writerWithDefaultPrettyPrinter()
						.writeValueAsString(map);
				logger.info("XRequest body: " + requestBody);
				HttpClientUtils.simplePost("http://localhost:3100/janus/events", requestBody);
			} catch (Exception e) {
				logger.severe("Exception sending json body: " + e.getMessage());
			}
		});

		String jsonStr = JsonFlattener.flatten(jsonBody);
		if (EventBus.getDefault().hasSubscriberForEvent(MessageEvent.class)) {
			logger.info("Sub found");
			EventBus.getDefault().post(new MessageEvent(MessageEvent.MESSAGE_NEW_SIP_EVENT, jsonStr));
		}
		assert db != null;
		db.saveEvent("sip_events", jsonStr,jsonBody/*this is the original json string*/);
		return HttpResponse.ok().contentType(MediaType.TEXT_JSON_TYPE)
				.body(
						new JSONObject()
								.put("success", true)
								.put("message", "Saved Event")
								.toString()
				);

	}


}
