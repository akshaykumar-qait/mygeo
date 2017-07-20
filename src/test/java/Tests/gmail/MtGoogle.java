package Tests.gmail;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class MtGoogle {

	/** Application name. */
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/sheets.googleapis.com-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws Exception
	 */
	public static Credential authorize() throws Exception {
		// Load client secrets.

		File f = new File("resource/client_secret.json");

		BufferedReader b = new BufferedReader(new FileReader(f));

		String readLine = "";
		boolean flag = false;

		String arrsplit[], temp[] = null;
		while ((readLine = b.readLine()) != null) {

			System.out.println(readLine);
		}

		// System.out.println(iin);

		// InputStream in = new BufferedInputStream(new FileReader(f));
		//
		// MtGoogle.class.getResourceAsStream("resource/client_secret.json");
		//
		// System.out.println(in);

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(f));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * 
	 * @return an authorized Sheets API client service
	 * @throws Exception
	 */
	public static Sheets getSheetsService() throws Exception {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
				.build();
	}

	public static void mains(String[] args) throws Exception {
		
		
		//https://docs.google.com/spreadsheets/d/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/edit?usp=sharing
		//https://docs.google.com/spreadsheets/d/1HluvvKnFcWTXf-ybcR97te2bTjVRUDWy9JdYU9f8ObU/edit?usp=sharing
		//https://docs.google.com/spreadsheets/d/1HluvvKnFcWTXf-ybcR97te2bTjVRUDWy9JdYU9f8ObU/edit?usp=sharing
		URL url = new URL(
				"https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A1:C?key=%20AIzaSyCwt2tn6wfyJY646Y-2DWYlRqVfkXqqkEg");

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

		String line = br.readLine();
		while (line != null) {

			line = br.readLine();
			System.out.println(line);

		}

	}

	public static void main(String[] args) throws Exception {
		
		URL url = new URL(
				"https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A:F?key=%20AIzaSyCwt2tn6wfyJY646Y-2DWYlRqVfkXqqkEg");
Object data = "67868";
		String message = "{ \"spreadsheetId \": \"1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0\",\"tableRange\": \"A1:A1\",\"updates\":  "+ data +" }";

		System.out.println(message);
		
		try {
			// instantiate the URL object with the target URL of the resource to
			// request

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");

			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			writer.write(message);

			writer.close();
		
			System.out.println(connection.getResponseCode());
		

		} catch (IOException e) {
			// ...
			
			e.printStackTrace();
		}

	}

}
