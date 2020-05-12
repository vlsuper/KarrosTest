package com.karros.auto;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class APIEndPoint {

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "1" }, { "2" }, { "3" }  };
    }

    @Test(description = "Verify Post API with valid value", dataProvider = "data-provider")
    public void getPost(String postID){

        System.out.println("Verify Post API with valid value");

        RestAssured.baseURI="https://my-json-server.typicode.com/typicode/demo/posts";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET,"/"+postID);
        System.out.println("Sending request with post id = '"+postID+
                "' and receive response: " + response.getBody().asString());

        // get header
        Headers allHeaders = response.headers();

        // Validate Response code is 200
        System.out.println("Response status code is " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200,response.getStatusCode() + " code is return.");

        // Validate content-type is json
        Assert.assertEquals(allHeaders.getValue("Content-Type"),"application/json; charset=utf-8",
                "Correct content-type");

        // parse response to json path
        JsonPath jsonPathValue = response.jsonPath();

        Integer id = jsonPathValue.get("id");
        String title = jsonPathValue.get("title");

        // Validate response body
        Assert.assertEquals(id.toString(),postID, "Correct post id: "+ postID);
        Assert.assertEquals(title, "Post "+postID, "Correct post title: Post "+postID);

    }

    @Test(description = "Verify Post API with wrong format value")
    public void getPostWithIncorrectFormat(){
        System.out.println("Verify Post API with wrong format value - " +
                "https://my-json-server.typicode.com/typicode/demo/posts/abc");

        RestAssured.baseURI="https://my-json-server.typicode.com/typicode/demo/posts";

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,"/abc");
        Headers allHeaders = response.headers();
        String responseBody = response.getBody().asString();

        Integer actualStatusCode = response.getStatusCode();
        System.out.println("Response status code is " + actualStatusCode);
        Assert.assertEquals(actualStatusCode.intValue(),404,"Correct status code return");
        Assert.assertEquals(allHeaders.getValue("Content-Type"),"application/json; charset=utf-8",
                "Correct content-type");
        System.out.println("Response Body is =>" + responseBody);
    }

    @Test(description = "Verify Post API with not existing value")
    public void getPostWithNotInvalidKey(){
        System.out.println("Verify Post API with not existing value - " +
                "https://my-json-server.typicode.com/typicode/demo/posts/1234234");

        RestAssured.baseURI="https://my-json-server.typicode.com/typicode/demo/posts1";

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,"/1234234");
        Headers allHeaders = response.headers();
        String responseBody = response.getBody().asString();

        Integer actualStatusCode = response.getStatusCode();
        System.out.println("Response status code is " + actualStatusCode);
        Assert.assertEquals(actualStatusCode.intValue(),404,"Correct status code return");
        Assert.assertEquals(allHeaders.getValue("Content-Type"),"application/json; charset=utf-8",
                "Correct content-type");
        System.out.println("Response Body is =>" + responseBody);
    }
}
