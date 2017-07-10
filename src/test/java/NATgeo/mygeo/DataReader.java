package NATgeo.mygeo;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader {

	public String readit(String key, String type) {

		JSONParser parser = new JSONParser();
		String resFile = "";
		JSONObject mainJsonObj = null;
		try {
			
			System.err.println("type is"+type);
			
			if(type.equals("ids"))
			{
				resFile="resources/Test1/ids.json";
			}
			else if(type.equals("locators"))
			{
				resFile="resources/Test1/locators.json";
			}
			else if(type.equals("password"))
			{
				resFile="resources/Test1/passwords.json";
			}
			else if(type.equals("urls"))
			{
				resFile="resources/Test1/urls.json";
			}
			
			mainJsonObj = (JSONObject) parser.parse(new FileReader(resFile));
		

		System.err.println(mainJsonObj);
		
		JSONArray locator = (JSONArray) mainJsonObj.get(type);
		JSONObject locators = (JSONObject) locator.get(0);

		
		return locators.get(key).toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
