package com.test;

import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

/**
 * @author kamlesh
 *
 */
public class TestBase {

	@BeforeSuite
	protected void beforeSuite() {
		RestAssured.baseURI = "https://reqres.in/";
	}

	protected Map<String, Object> getRequestData(final Map<String, Object> mapData) {
		return getMapData(mapData, "request");
	}

	protected Map<String, Object> getResponseData(final Map<String, Object> mapData) {
		return getMapData(mapData, "response");
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Object> getMapData(final Map<String, Object> map, final String key) {
		Object response = map.get(key);
		if (response != null) {
			return (Map<String, Object>) response;
		}
		return Map.of();
	}

}