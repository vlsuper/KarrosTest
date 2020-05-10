package com.karros.auto;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIEndPoint {

    @Test(description = "Verify API endpoint")
    public void getPost(){
        RestAssured.baseURI="https://my-json-server.typicode.com/typicode/demo/posts";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET,"/1");

        Headers allHeaders = response.headers();

        // Iterate over all the Headers
//        for(Header header : allHeaders)
//        {
//            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
//        }

        String responseBody = response.getBody().asString();
        // Validate Response code
        Assert.assertEquals(response.getStatusCode(),200,"Correct status code return");
        Assert.assertEquals(allHeaders.getValue("Content-Type"),"application/json; charset=utf-8",
                "Correct content-type");
        System.out.println("Response Body is =>" + responseBody);
        System.out.println("Server " + allHeaders.getValue("Server"));


        response = httpRequest.request(Method.GET,"/2");
        allHeaders = response.headers();
        responseBody = response.getBody().asString();
        Assert.assertEquals(response.getStatusCode(),200,"Correct status code return");
        Assert.assertEquals(allHeaders.getValue("Content-Type"),"application/json; charset=utf-8",
                "Correct content-type");
        System.out.println("Response Body is =>" + responseBody);

        response = httpRequest.request(Method.GET,"/3");
        allHeaders = response.headers();
        responseBody = response.getBody().asString();
        Assert.assertEquals(response.getStatusCode(),200,"Correct status code return");
        Assert.assertEquals(allHeaders.getValue("Content-Type"),"application/json; charset=utf-8",
                "Correct content-type");
        System.out.println("Response Body is =>" + responseBody);

        response = httpRequest.request(Method.GET,"/abc");
        allHeaders = response.headers();
        responseBody = response.getBody().asString();
        Assert.assertEquals(response.getStatusCode(),404,"Correct status code return");
        Assert.assertEquals(allHeaders.getValue("Content-Type"),"application/json; charset=utf-8",
                "Correct content-type");
        System.out.println("Response Body is =>" + responseBody);

        response = httpRequest.request(Method.GET,"/2342");
        allHeaders = response.headers();
        responseBody = response.getBody().asString();
        Assert.assertEquals(response.getStatusCode(),404,"Correct status code return");
        Assert.assertEquals(allHeaders.getValue("Content-Type"),"application/json; charset=utf-8",
                "Correct content-type");
        System.out.println("Response Body is =>" + responseBody);
    }
}
