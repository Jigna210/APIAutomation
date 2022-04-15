package apitests;

import org.testng.annotations.*;
import io.restassured.path.json.JsonPath;
import pageObject.Constants;
import pageObject.RequestMethod;
import io.restassured.response.Response;
import pageObject.Utility;

/* This class contains test cases for https://jsonplaceholder.typicode.com using RestAssured. */

public class SearchUser extends BaseClass
{
	@Test(enabled = false)
	public void GetUserTest() 
	{
		/* Search for the user */ 
		Response res = helper.TriggerRequest(RequestMethod.Get, "/users/3", null);
		verifyHttpStatusCode(res, Constants.Ok);					
		System.out.println(res.asString());	
		
	}
	
	@Test
	public void GetPostTest() 
	{		
		/* Use the details fetched to make a search for the posts written by the user */ 
		Response res = helper.TriggerRequest(RequestMethod.Get, "users/3", null);
		verifyHttpStatusCode(res, Constants.Ok);					
		// System.out.println(res.asString());	
		JsonPath jsp = new JsonPath(res.asString());
		int usrId = jsp.getInt("id");
		System.out.println("user id of Samantha: "+usrId);
		
		Response res2 = helper.TriggerRequest(RequestMethod.Get, "posts", null);
		verifyHttpStatusCode(res, Constants.Ok);
		JsonPath jsp2 = new JsonPath(res2.asString());
		// System.out.println("List of userId from Posts: "+jsp2.getList("title").get(21));
		for (int i=0; i < 50; i++)
		{
			if (jsp2.getList("userId").get(i).equals(usrId))
			{
				System.out.println("Post Title= "+ jsp2.getList("title").get(i));
				System.out.println("Post Body= "+ jsp2.getList("body").get(i));
			}
		}
	}
	
	@Test
	public void GetCommentsTest() 
	{		
		/* For each post, fetch the comments and validate if the email in the comment
			section are in the proper format. */ 
		Response response = helper.TriggerRequest(RequestMethod.Get, "comments", null);
		verifyHttpStatusCode(response, Constants.Ok);
		JsonPath jsp = new JsonPath(response.asString());		
		Utility utl = new Utility();		
		
		/* We can use jsp.getList("postId").size(), For Testing purpose user i<10*/
		for (int i=0; i < 10; i++)
		{
			
			System.out.println((jsp.getList("postId").get(i)));	
			System.out.println((jsp.getList("body").get(i)));	
			Object value = jsp.getList("email").get(i);
			System.out.println("The Email address" + value + " is " + (utl.validatedEmailAddress(value) ? "Valid Email Address" : "Invalid Email Address"));

		}	


	}
}