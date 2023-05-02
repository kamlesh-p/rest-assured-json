
package com.test;

import static org.testng.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author kamlesh
 *
 */
public class RestAssuredTest extends TestBase {

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "testData")
	public void testName(final LinkedHashMap<String, Object> mapData) {
		System.out.println(mapData.get("_description"));
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

}
