package Readers;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.testng.annotations.Test;

import Tests.Google_Get_Access.GetAccessKey;

public class SpreadSheetReader {
	private static final String String = null;
	static OptionReader read = new OptionReader();

	static String typeis = "";

	public SpreadSheetReader() throws Exception {
		// TODO Auto-generated constructor stub

//		read.writeit("currentpath", "resource/SheetUse/");
//		new GetAccessKey().fetch_accesstoken();
//
//		if (read.optionFileReader("readfrom").equals("json")) {
//			typeis = "Json_Files";
//		}

	}

	
	public StringBuffer fetchthefile() throws Exception {

		URL url = new URL(
				"https://sheets.googleapis.com/v4/spreadsheets/" + read.optionFileReader("google_spreadsheet_name")
						+ "/values/A:ZZZ?key=AIzaSyCwt2tn6wfyJY646Y-2DWYlRqVfkXqqkEg");

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

		StringBuffer output = new StringBuffer();
		String line = br.readLine();
		while (line != null) {

			output = output.append(line + "\n");

			line = br.readLine();
			System.out.println("here    " + line);

		}

		
		System.err.println(output);
		
		return output;
	}
	
	

}
