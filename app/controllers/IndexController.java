package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.CacheService;

import java.util.HashMap;
import java.util.Map;

public class IndexController extends BaseController
{
	private final CacheService cacheService;

	@Inject
	IndexController(CacheService cacheService)
	{
		this.cacheService = cacheService;
	}

	public Result index()
	{
		return ok("INDEX");
	}

	public Result get(String key)
	{
		return ok(this.cacheService.get(key));
	}

	public Result set(Http.Request request)
	{
		Map<String, Boolean> response = new HashMap<>();
		JsonNode requestJson = request.body().asJson();
		String key = "";
		String value = "";
		Integer expiry = (60 * 60);

		if(requestJson.has("key") && requestJson.has("value"))
		{
			key = requestJson.get("key").asText();
			value = requestJson.get("value").asText();

			if(requestJson.has("expiry"))
			{
				expiry = Integer.valueOf(requestJson.get("expiry").asText());
			}

			response.put("success", this.cacheService.set(key, value, expiry));
		}
		else
		{
			throw new RuntimeException("Bad Request");
		}

		return ok(Json.toJson(response));
	}

	public Result delete(String key)
	{
		Map<String, Boolean> response = new HashMap<>();
		response.put("success", this.cacheService.delete(key));
		return ok(Json.toJson(response));
	}

	public Result exists(String key)
	{
		Map<String, Boolean> response = new HashMap<>();
		response.put("exists", this.cacheService.exists(key));
		return ok(Json.toJson(response));
	}
}