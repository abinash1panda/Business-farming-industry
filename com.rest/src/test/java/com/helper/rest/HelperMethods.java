package com.helper.rest;
import java.io.IOException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helper.rest.Community.Promotions;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static org.testng.Assert.assertEquals;

import java.util.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HelperMethods {
    /*
    Verify the http response status returned. Check Status Code is 200?
    We can use Rest Assured library's response's getStatusCode method
    */
    public static void checkStatusIs200 (Response res) {
		/*
		 * if(res.getStatusCode()==200) { System.out.println("hello"); }
		 */
        assertEquals(res.getStatusCode(),200,"Status Check Failed!" );
    }
 
    /*
	 * validate response field.Name 
	 */
    public static String getName (JsonPath jp) {
        String name = jp.getString("Name");
        return name;
    }
    
    /*
	 * validate response field.canRelist 
	 */
    
    public static String canRelist (JsonPath jp) {
        String canRelist = jp.getString("CanRelist");
        System.out.println("canRelist" +  canRelist);
        return canRelist;
    }
    
    /*
	 * validate promotion list from response 
	 */
 
    public static String[] getArrayFromJsonObject (Response res) throws JsonParseException, JsonMappingException, IOException {
    	
    	String[] thisIsAStringArray = null;
    	String responseString = res.asString();
    	JsonPath js = new JsonPath(responseString);
    	String name = js.get("Promotions[1].Name");
    	String description = js.get("Promotions[1].Description");
    	boolean searchDescription =  description.contains("2x larger image");
    	if(searchDescription) {
    	System.out.println(searchDescription);
    	if (!name.isEmpty() && !description.isEmpty()) {
    	    thisIsAStringArray = new String[] {name,description};
    	} else {
    	    thisIsAStringArray = new String[2];
    	}
    	}
    	return thisIsAStringArray;
    }
    	
    	
    	
    	
    	
    	
    	
    }
 
   
