package apiTesting;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apiTest {
	
	String  userId = "1";
	
	
	@Test
	void getMail() {
		String user  = "/users?id=" + userId ;
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		
		// Request object 
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response  = httpRequest.request(Method.GET,user);
		
		JsonPath j =  response.jsonPath();
		System.out.println(j.get("email"));

		

	}

	@Test
	void checkPostsID() {
		
		// specify the base URI

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		
		// Request object 
		RequestSpecification httpRequest = RestAssured.given();
		

		
		// response 
		String posts = "/posts?userId=" +userId ;
		Response response  = httpRequest.request(Method.GET,posts);

		Boolean flag  = true;
		JsonPath j = new JsonPath(response.asString());
	      String id = j.getString("id");
	      
	      String[] items = id.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

	      int[] results = new int[items.length];

	      for (int i = 0; i < items.length; i++) {
	          try {
	              results[i] = Integer.parseInt(items[i]);
	              if (results[i]<1 || results[i]>100) {
	            	  flag = false ;
	              }
	              
	          } catch (NumberFormatException nfe) {
	          };
	      }
	      
	     Assert.assertEquals(flag, true);
	      
	    
	}
	
	
	
	@Test 
	void postWithUserID() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject ();
		requestParams.put ("id", userId);
		requestParams.put ("title", "title Test data ");
		requestParams.put ("body", "body test data");
		

		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		
		Response response  = httpRequest.request(Method.POST,"/posts");
		
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.statusCode(), 201);

		
	}
}
