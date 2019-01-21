package com.test.rest;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.core.utils.RestUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.helper.rest.HelperMethods;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GetAPITest {

	private Response res = null; //Response
	private JsonPath jp = null; //JsonPath

	/*
    We should do setup operations, get JSON response from the API and put it into JsonPath object
    Then we will do query and manipulations with JsonPath class’s methods.
    We can do all of the preparation operations after @Before TestNG annotation.
	 */
	@BeforeMethod
	public void setup (){
		//Test Setup
		RestUtil.setBaseURI("https://api.tmsandbox.co.nz"); //Setup Base URI
		RestUtil.setBasePath("/v1/Categories"); //Setup Base Path
		//In this example, I assigned full path manually in below code line.
		RestUtil.path = "/6327/Details.json?catalogue=false";
		RestUtil.setContentType(ContentType.JSON); //Setup Content Type
		res = RestUtil.getResponse(); //Get response
		jp = RestUtil.getJsonPath(res); //Set JsonPath
	}


	@Test 
	public void T01_StatusCodeTest() { //Verify the http response status returned. Check Status Code is 200? 
		HelperMethods.checkStatusIs200(res); 
	}

	@Test 
	public void T02_searchName() { //Verify the response contained the relevant search term (519218045)
		assertEquals(HelperMethods.getName(jp).toString(),"Carbon credits","not found a match"); }

	@Test 
	public void T03_canRelist() { //Verify that extra 4 video entries were returned as related videos
		assertEquals(HelperMethods.canRelist(jp),"true","not found a match"); 
	}
	@Test
	public void T04_printPromotionAttributes() throws JsonParseException, JsonMappingException, IOException {
		String[] finalArrray = HelperMethods.getArrayFromJsonObject(res);
		if(finalArrray.length > 0) {
			for(int i=0; i < finalArrray.length;i++) {
				System.out.println("lets assert" + i + finalArrray[i]);
				if(i==0) {
					assertEquals(finalArrray[i],"Gallery","String didn't match");
				}
				else {
					assertEquals(finalArrray[i],"Good position in category \n" + 
							"2x larger image in desktop site search results","String didn't match");
				}
			}

		}

	}


	@AfterTest
	public void afterTest (){
		//Reset Values
		RestUtil.resetBaseURI();
		RestUtil.resetBasePath();
	}


}
