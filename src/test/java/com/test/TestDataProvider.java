
package com.test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import io.restassured.path.json.JsonPath;

/**
 * @author kamlesh
 *
 */
public class TestDataProvider {

	@DataProvider(name = "testData")
	public Object[] getTestData() {
		JsonPath path = JsonPath.from(new File("./src/test/resources/testData.json"));
		List<LinkedHashMap<String, Object>> maps = path.getList("data");
		Object[] hashMaps = maps.toArray();
		return hashMaps;
	}

}
