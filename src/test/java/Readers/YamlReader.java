package Readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlReader {

	public String readit(String Key, String type) {

		
		
		String resFile = "";
		
		
		try {
			
			System.err.println("type is"+type);
			
			if(type.equals("ids"))
			{
				resFile="resources/Yaml_Files/ids.yaml";
			}
			else if(type.equals("locators"))
			{
				resFile="resources/Yaml_Files/locators.yaml";
			}
			else if(type.equals("password"))
			{
				resFile="resources/Yaml_Files/passwords.yaml";
			}
			else if(type.equals("urls"))
			{
				resFile="resources/Yaml_Files/urls.yaml";
			}
			
		File file = new File(resFile);

		InputStream is = null;
		
			is = new FileInputStream(file);

			Yaml yaml = new Yaml();

			Map<String, String> yamlParsers = (Map<String, String>) yaml.load(is);

			return yamlParsers.get(Key).toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
