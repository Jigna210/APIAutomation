package apitests;

import pageObject.Helper.*;

import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/* This is Base class for https://jsonplaceholder.typicode.com inherited by Test Class. */

public class BaseClass 
{
	ApiHelper helper;
	public BaseClass()
	{
		BaseUrl();
		helper = new ApiHelper();
	}
	
	public void BaseUrl()
	{
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";		
	}
	public void verifyHttpStatusCode(Response response, int expectedHttpCode)
	{
		int actualHttpCode = response.getStatusCode();
		System.out.println("Response code: " +actualHttpCode);
		assertEquals(actualHttpCode, expectedHttpCode);			
	}
}
