package Demo;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Sample {
	
	 
	@Test
	public static void getWhatherDetails(){
		
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com/todos/";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response= httpRequest.get("1");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
	}

}
