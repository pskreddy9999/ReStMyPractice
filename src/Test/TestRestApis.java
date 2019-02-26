package Test;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Demo.Basic;
import Demo.Commons;
import Demo.ConfigData;

public class TestRestApis extends ConfigData {
	
	ConfigData data;
	static String url;
	@BeforeMethod
	public void setUp(){
		data = new ConfigData();
		String urlapi = ConfigData.GetData("url");
		String serviceurl = ConfigData.GetData("serviceURl");
		
	   url =urlapi+ serviceurl;		
	}
	
	@Test(priority = 1)
	public static void testApis() throws IOException{
		

		
		Basic.testWithIsoCode();
		Basic.testBody(ConfigData.GetData("url"));		

	}
  
	/*@Test(priority =0)
	public static void testApi() throws IOException{
		
		Basic.testSampleRestApi();
		Basic.testBody(ConfigData.GetData("GetBodyUrl"));
	}*/
	
	
	
//	Basic.testSampleRestApi();
//	PostResquest.postMethod();
	//Basic.testWithCountryName(ConfigData.GetData("url"));
//	Basic.testWithNameOFCoutry();
//	Basic basic = new Basic();
//	Basic.testWithCountryName(url);
}
