package pageObject.Helper;

import pageObject.Constants;
import pageObject.RequestMethod;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiHelper 
{
	/* This class contains helper function. */
	
	public Response TriggerRequest(RequestMethod requestMethod, String url, String payLoad)
	{				
		RequestSpecification request = RestAssured.given();				
		request.header(Constants.ContentType, Constants.ApplicationJson);
		//request.queryParam("id", "3");
		
		if(payLoad != null) 
		{
			request.body(payLoad);					
		}
		
		Response response = null;

		switch(requestMethod) 
		{
		case Delete:
			response = request.delete(url);
			break;
		case Get:
			response = request.get(url);
			break;
		case Patch:
			response = request.patch(url);
			break;
		case Post:
			response = request.post(url);
			break;
		case Put:
			response = request.put(url);
			break;
		default:
            throw new UnsupportedOperationException("Request method is not supported.");		
		}
		return response;
		
	}

}
