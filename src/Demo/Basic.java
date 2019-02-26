package Demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Basic {

//	public static int code ;
	
	
	public static void testWithCountryName(String url) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse ;
		try {
			
			httpResponse=	 httpClient.execute(httpGet);
			
			int value = httpResponse.getStatusLine().getStatusCode();
			
			System.out.println("Resonse code is: "+value);
			
			String responseString =EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			
			
			JSONObject responseJsonObj = new JSONObject(responseString);

				System.out.println("Response JSON from Api--> "  + responseJsonObj );			
			
			Header[] headerArray = httpResponse.getAllHeaders();
			
			HashMap allHeaders = new HashMap<String,String>();
			
			for(Header header : headerArray){
				allHeaders.put(header.getName(), header.getValue());
			}
			
			System.out.println("Headers Array---> "+ allHeaders);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void testSampleRestApi() {

		Data.tcData = ExcelData.getData("E:\\workSpace\\DemoRest\\DataExl\\Book1.xlsx", "Sheet1", 1);

		RestAssured.baseURI = ConfigData.GetData("urlWeather");

		Response response = (Response) RestAssured.given()
				.param(Data.tcData.get("Param"), Data.tcData.get("Country_Name"))
				.param(Data.tcData.get("Param_Id"), Data.tcData.get("Key")).when().get().then().assertThat()
				.statusCode(200)
				// .contentType(ContentType.JSON)
				.extract().response();

		//XmlPath xml = Commons.rawToXml(response) ;
		
		Date date = new Date();
		String sdt = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(date);
		System.out.println("Tested date and time: "+ sdt);
		System.out.println("Response time: "+ response.getTime());

		System.out.println("Pass: Test ");
		//System.out.println("Response is: " + xml);
	}


	public static void testWithIsoCode() {
             
		Response response = RestAssured.get(ConfigData.GetData("url"));		
		
		Data.code = response.getStatusCode();
		
		System.out.println("Status code is: "+ Data.code);	
		
		Assert.assertEquals(Data.code, Commons.responseCodes());
	}
	
  public static void testBody(String url){	
	  
	  Response resp = null ;
	  String dat = null ;	  
	  try{
		  resp = RestAssured.get(url);
		  dat =resp.then().statusCode(Commons.responseCodes()).log().all().toString();
	  }catch(AssertionError ae){
		  System.out.println("We catching the error because response code are not matched, we got this response code: "+ Commons.responseCode() + " it response code should be 200");
	  }
		String data= resp.toString();	
		
		Date date = new Date();
		String sdt = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(date);
		
		System.out.println("Data is: "+ dat);
		System.out.println("Tested date and time: "+ sdt);
		System.out.println("Response time: "+ resp.getTime());
	}

	public static void toGetTheContentOfWebService() {
             
		given().get("https://restcountries.eu/rest/v2").then().statusCode(200).log().all();
	}

	public static void testWithCapitalCity() {

	}
	
	

}
