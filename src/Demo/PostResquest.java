package Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class PostResquest {
	
	static ConfigData testb;
	static HttpURLConnection postConnect;
	static String POST_PARAMS;
	static URL sericeUrl;

	public static void postMethod() throws IOException {

		POST_PARAMS = " { \n" +

				"   \"userId\":101, \r\n" +

				"   \"id\":101, \r\n" +

				"   \"Title\": \"Test Title\", \r\n" +

				"    \"Body\": \"Test Body\" " + " \n }";

		System.out.println(POST_PARAMS);

		url("https://jsonplaceholder.typicode.com/posts");
		
		resouceName("POST");
		
		int responseCode = postConnect.getResponseCode();
		System.out.println("post response code : " + responseCode);
		System.out.println();
		System.out.println("post response message : " + postConnect.getResponseMessage());

		if (responseCode == HttpURLConnection.HTTP_CREATED) {
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnect.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
		} else {
			System.out.println("post method not worked properly");
		}
	}

	public static void url(String nameOfUrl) {

		try {
			sericeUrl = new URL(nameOfUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void resouceName(String resourceName) throws IOException {
		
		postConnect = (HttpURLConnection)sericeUrl.openConnection();
		
		postConnect.setRequestMethod(resourceName);
		postConnect.setRequestProperty("userId", "a1bcedfgh");
		postConnect.setRequestProperty("content-type", "application/json");
		
		postConnect.setDoOutput(true);
		OutputStream os = postConnect.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();


	}

}
