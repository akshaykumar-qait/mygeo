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

import utility.Datadecider;

import com.google.api.services.sheets.v4.Sheets;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
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

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.eclipse.jetty.client.HttpExchange;

public class MtGoogle {

	// for getting https://developers.google.com/oauthplayground

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

	public static void mainT(String[] args) throws Exception {

		// https://docs.google.com/spreadsheets/d/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/edit?usp=sharing
		// https://docs.google.com/spreadsheets/d/1HluvvKnFcWTXf-ybcR97te2bTjVRUDWy9JdYU9f8ObU/edit?usp=sharing
		// https://docs.google.com/spreadsheets/d/1HluvvKnFcWTXf-ybcR97te2bTjVRUDWy9JdYU9f8ObU/edit?usp=sharing
		// URL url = new URL(
		// "https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A1:C5?key=%20AIzaSyCwt2tn6wfyJY646Y-2DWYlRqVfkXqqkEg");

		URL url = new URL(
				"https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A:F?access_token=ya29.GluOBC9EiyacIoMQtgAZlFbt-Xv_SGYivFWTfNDI86JxMRHTZVaK3lI57dxUgvNuaOr_zeUKH0DCCtmWLbpUjjgoKs_vvIEwkyYJ-Ufz9eq8sMQpCHBXGpGoV9BH");

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

	public static void maint(String[] args) throws Exception {

		// URL url = new URL(
		// "https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A:F:clear?access_token=ya29.GluOBC9EiyacIoMQtgAZlFbt-Xv_SGYivFWTfNDI86JxMRHTZVaK3lI57dxUgvNuaOr_zeUKH0DCCtmWLbpUjjgoKs_vvIEwkyYJ-Ufz9eq8sMQpCHBXGpGoV9BH");

		URL url = new URL(
				"https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A:B:clear?access_token=ya29.GluOBKprFqJj0fJvEoBUQamvF3JaKq73G8THuEGfkAHKwiXyc7H3NwEwulOJPhYheHFhBvf6YpCzdV0bOSapMxcL2gwhl5awzZK1uFrUzWD2WTCn5kvNDlRuZcw_");

		// Object data = "67868";
		// String message = "{ \"spreadsheetId \":
		// \"1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0\",\"tableRange\":
		// \"A1:A1\",\"updates\": "+ data +" }";
		//
		// System.out.println(message);
		//
		try {
			// instantiate the URL object with the target URL of the resource to
			// request

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setFixedLengthStreamingMode(00);
			// connection.connect();

			//
			// OutputStreamWriter writer = new
			// OutputStreamWriter(connection.getOutputStream());
			//
			// writer.write(message);
			//
			// writer.close();
			//

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

	public static void main(String[] args) throws Exception {

		// URL url = new URL(
		// "https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A:F:clear?access_token=ya29.GluOBC9EiyacIoMQtgAZlFbt-Xv_SGYivFWTfNDI86JxMRHTZVaK3lI57dxUgvNuaOr_zeUKH0DCCtmWLbpUjjgoKs_vvIEwkyYJ-Ufz9eq8sMQpCHBXGpGoV9BH");

		
		HttpExchange httpExchange;
		URL url = new URL(
				"https://sheets.googleapis.com/v4/spreadsheets/1n6DeLGHKvUd-_mziCo5Q5Juuq6w4cCpw2mqY46oCZL0/values/A1:A1:append?valueInputOption=USER_ENTERED&access_token=ya29.GluOBKprFqJj0fJvEoBUQamvF3JaKq73G8THuEGfkAHKwiXyc7H3NwEwulOJPhYheHFhBvf6YpCzdV0bOSapMxcL2gwhl5awzZK1uFrUzWD2WTCn5kvNDlRuZcw_");

		String message =
				"{" + " \"range\": \"A1:A1\", " + "  \"majorDimension\": \"ROWS\", " + "  \"values\": [ "
				+ "  [\"shadab\",\"nishant\"]" + "]" + "}";

		System.out.println(message);

		try {
			// instantiate the URL object with the target URL of the resource to
			// request

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
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

	String get_data() {

		try {

			OAuthClient client = new OAuthClient(new URLConnectionClient());
			OAuthClientRequest request = OAuthClientRequest.tokenLocation("https://accounts.google.com/o/oauth2/token")
					.setGrantType(GrantType.CLIENT_CREDENTIALS)
					.setClientId("901927711216-7oeeh9dm5sgsdli1r5c631i40t48qe4t.apps.googleusercontent.com")
					.setClientSecret("08uAyVE58BjyvyiJD5vux33t")
					// .setScope() here if you want to set the token scope
					.buildQueryMessage();

			String token = client.accessToken(request, OAuthJSONAccessTokenResponse.class).getAccessToken();

			System.out.println(token);

			return token;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
}
