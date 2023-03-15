
package com.test;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author kamlesh
 *
 */
public class RestAssuredTest {

	@BeforeSuite
	private void beforeSuite() {
		RestAssured.baseURI = "https://reqres.in/";
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "testData")
	public void testName(final HashMap<String, Object> mapData) {
		System.out.println("========" + mapData.get("_description") + "========");
		Map<String, Object> body = getRequestData(mapData);
		// API call
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body).when().log().all()
				.post("/api/users");
		// log response
		System.out.println("======== Response ========");
		System.out.println("StatusCode: " + response.statusCode());
		System.out.println("======== Response  Header ========");
		System.out.println(response.headers());
		System.out.println("======== Response  Body ========");
		System.out.println(response.asPrettyString());
		// response validation
		Map<String, Object> responseData = getResponseData(mapData);
		assertEquals(response.getStatusCode(), responseData.get("statusCode"));
		assertEquals(response.jsonPath().getString("name"), String.valueOf(body.get("name")));
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
