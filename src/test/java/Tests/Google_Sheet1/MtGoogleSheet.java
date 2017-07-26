package Tests.Google_Sheet1;

import utility.Datadecider;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.eclipse.jetty.client.HttpExchange;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonObject;

import Readers.JsonReader;
import Readers.OptionReader;
import Tests.Google_Get_Access.GetAccessKey;

public class MtGoogleSheet {

	private static final String String = null;
	static OptionReader read = new OptionReader();

	static String typeis = "";

	public MtGoogleSheet() throws Exception {
		// TODO Auto-generated constructor stub

		read.writeit("currentpath", "resource/SheetUse/");
		new GetAccessKey().fetch_accesstoken();

		if (read.optionFileReader("readfrom").equals("json")) {
			typeis = "Json_Files";
		}

	}

	// for getting https://developers.google.com/oauthplayground
	// accesstoken =
	// https://developers.google.com/oauthplayground/?code=4/6Wd3DBIW0SgCgt7xxV4fwgSISa1qRe70q361yyUN3Ec
	// https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A:ZZZ?key=AIzaSyCwt2tn6wfyJY646Y-2DWYlRqVfkXqqkEg");

	public static void fetchthefile() throws Exception {

		URL url = new URL(
				"https://sheets.googleapis.com/v4/spreadsheets/" + read.optionFileReader("google_spreadsheet_name")
						+ "/values/A:ZZZ?key=AIzaSyCwt2tn6wfyJY646Y-2DWYlRqVfkXqqkEg");

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		FileWriter write = new FileWriter(
				read.optionFileReader("currentpath") +   typeis + "/actual_data_google_sheet1.json");
		// FileOutputStream write = new FileOutputStream(new
		// File("src/test/java/Tests/Google_Sheet1/actual_data_google_sheet1.json"),
		// false);
		StringBuffer output = new StringBuffer();
		String line = br.readLine();
		while (line != null) {

			output = output.append(line + "\n");

			line = br.readLine();
			System.out.println("here    " + line);

		}

		System.err.println(output);
		write.append(output);
		write.close();
	}

	public static void deletethetable(int row_start, int row_end) throws Exception {

		// URL url = new URL(
		// "https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A:F:clear?access_token=ya29.GluOBC9EiyacIoMQtgAZlFbt-Xv_SGYivFWTfNDI86JxMRHTZVaK3lI57dxUgvNuaOr_zeUKH0DCCtmWLbpUjjgoKs_vvIEwkyYJ-Ufz9eq8sMQpCHBXGpGoV9BH");

		URL url = new URL("https://sheets.googleapis.com/v4/spreadsheets/"
				+ read.optionFileReader("google_spreadsheet_name") + "/values/A" + row_start + ":Z" + row_end
				+ ":clear?access_token=" + read.optionFileReader("accesstoken_googles_spreadsheet_api"));
		try {

			System.out.println(
					"https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A"
							+ row_start + ":Z" + row_end + ":clear?access_token="
							+ read.optionFileReader("accesstoken_googles_spreadsheet_api"));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setFixedLengthStreamingMode(00);
			System.out.println(connection.getResponseMessage());

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String line = br.readLine();
			while (line != null) {

				System.out.println(line);
				line = br.readLine();

			}

			System.out.println(connection.getResponseCode());

		} catch (IOException e) {
			// ...

			e.printStackTrace();
		}

		// new MtGoogle().get_data();

	}

	public static void writetofile() throws Exception {

		URL url = new URL(
				"https://sheets.googleapis.com/v4/spreadsheets/" + read.optionFileReader("google_spreadsheet_name")
						+ "/values/A:Z:append?valueInputOption=USER_ENTERED&access_token="
						+ read.optionFileReader("accesstoken_googles_spreadsheet_api"));
		String message = new JSONParser()
				.parse(new FileReader(
						new File(read.optionFileReader("currentpath") +  typeis + "/input_data_google_sheet1.json")))
				.toString();

		System.out.println(message);
		try {

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(message);
			writer.close();

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String line = br.readLine();
			while (line != null) {

				line = br.readLine();
				// System.out.println(line);
			}

			System.out.println(connection.getResponseCode());

		} catch (IOException e) {
			// ...

			e.printStackTrace();
		}

		// new MtGoogle().get_data();

	}

	public static void modify() throws Exception {

		JSONObject range = (JSONObject) new JSONParser().parse(new FileReader(

				new File(read.optionFileReader("currentpath") + typeis + "/modify_data_google_sheet1.json")));

		read.writeit("range", range.get("range").toString());

		URL url = new URL(
				"https://sheets.googleapis.com/v4/spreadsheets/" + read.optionFileReader("google_spreadsheet_name")
						+ "/values/" + read.optionFileReader("range") + "?valueInputOption=USER_ENTERED&access_token="
						+ read.optionFileReader("accesstoken_googles_spreadsheet_api"));

		String message = new JSONParser()
				.parse(new FileReader(
						new File(read.optionFileReader("currentpath") +  typeis + "/modify_data_google_sheet1.json")))
				.toString();

		System.out.println(message);

		try {
			// instantiate the URL object with the target URL of the resource to
			// request

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");
			// connection.setFixedLengthStreamingMode(0);

			// connection.connect();

			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			// DataOutputStream writer = new
			// DataOutputStream(connection.getOutputStream());

			writer.write(message);

			writer.close();

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String line = br.readLine();
			while (line != null) {

				line = br.readLine();
				System.out.println(line);

			}

			System.out.println(connection.getResponseCode());

		} catch (IOException e) {
			// ...

			e.printStackTrace();
		}

		// new MtGoogle().get_data();

	}

	public static void main(String agrs[]) throws Exception {
		MtGoogleSheet obj = new MtGoogleSheet();

		obj.fetchthefile();
		obj.modify();
		obj.writetofile();
		obj.deletethetable(2, 5);

		obj.fetchthefile();

	}

}
