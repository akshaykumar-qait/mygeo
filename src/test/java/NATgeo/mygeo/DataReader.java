package NATgeo.mygeo;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader {

	public String readit(String key, String type) {

		JSONParser parser = new JSONParser();

		JSONObject mainJsonObj = null;
		try {
			mainJsonObj = (JSONObject) parser.parse(new FileReader("resources/data.json"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.err.println(mainJsonObj);
		
		JSONArray locator = (JSONArray) mainJsonObj.get(type);
		JSONObject locators = (JSONObject) locator.get(0);

		return locators.get(key).toString();

	}

}
