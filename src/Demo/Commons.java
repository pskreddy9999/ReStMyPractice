package Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Commons {
	static StringBuffer response;
	//static int responseCode;
	static BufferedReader in;
	static String readLine = null;
	static HttpURLConnection connection;
	static int statusCode;

	public static XmlPath rawToXml(Response r){
		
		String respo= r.asString();
		
		XmlPath xml = new XmlPath(respo);
		
		return xml;
	}
  public static JsonPath rqwToJson(Response r){
	  
	  String respon= r.asString();
	  JsonPath json= new JsonPath(respon);
	  return json;
  }

	public static String timestamp() {

		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
  public static int responseCode() {	  
	  
	  int values[] = { 200, 400, 500, 502, 501, 401, 404 };
       
	  int it = values.length-1;
	  
	  for(int i=0;i<it;i++){
		  
		  if (Data.code == i) {				
				//Data.code = values[i];
				System.out.println("Response code we got: " + Data.code);
				
				break;
			} else {				
				System.out.println("We got wrong response code: " + Data.code);	
				break;
			}		  
	  }	  
		return Data.code;
  }
  
  public static void waitMethod(){
	  
	  try {
		Thread.sleep(4000);
		System.out.println("We are waiting for to excute the second test");
	} catch (InterruptedException e) {
		
		System.out.println("We are waited for perticular time to excute the second test but still it throws excepion");
	}
  }
	  
	  
  
  public static int responseCodes() {
	  
	 // int responseCode =  RestAssured.get().statusCode();
	  
	  
	  if(Data.code == 200){
		  System.out.println("Response code is: " + 200);
	  }else if(Data.code == 401){
		  System.out.println("Response code is: " + 401);
	  }else if(Data.code == 400){
		  System.out.println("Response code is: " + 400);
	  } else if(Data.code == 404){
		  System.out.println("Response code is: " + 404);
	  }
	  else{
		System.out.println("Response code is not from the list: "+ Data.code);  
	  }
	return Data.code;
	  
  }
}
