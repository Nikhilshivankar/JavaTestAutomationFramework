package api_TESTING;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Test {

    @BeforeClass
    public void setup() {
        // Specify the base URL for ReqRes API testing
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testGetUsersList() {
        RequestSpecification httpRequest = RestAssured.given();
        
        // Add query parameters
        httpRequest.queryParam("page", "2");
        
        Response response = httpRequest.get("/api/users");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Verify status code is 200");
        
        String responseBody = response.getBody().asString();
        System.out.println("GET /api/users Response Body: " + responseBody);
        
        JsonPath jsonPath = response.jsonPath();
        int totalUsers = jsonPath.getList("data").size();
        Assert.assertTrue(totalUsers > 0, "Users list should not be empty");
        
        String email = jsonPath.getString("data[0].email");
        Assert.assertTrue(email.contains("@"), "Email should contain @");
    }

    @Test
    public void testGetSingleUser() {
        RequestSpecification httpRequest = RestAssured.given();
        
        Response response = httpRequest.get("/api/users/2");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Verify status code is 200");
        
        JsonPath jsonPath = response.jsonPath();
        String firstName = jsonPath.getString("data.first_name");
        String lastName = jsonPath.getString("data.last_name");
        
        Assert.assertEquals(firstName, "Janet", "Verify first name");
        Assert.assertEquals(lastName, "Weaver", "Verify last name");
    }

    @Test
    public void testCreateUser() {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);
        
        // Build JSON payload
        Map<String, Object> body = new HashMap<>();
        body.put("name", "morpheus");
        body.put("job", "leader");
        httpRequest.body(body);
        
        Response response = httpRequest.post("/api/users");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201, "Verify status code is 201 (Created)");
        
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.getString("name");
        String id = jsonPath.getString("id");
        
        Assert.assertEquals(name, "morpheus", "Verify name matches");
        Assert.assertNotNull(id, "Verify user ID is generated");
    }

    @Test
    public void testUpdateUser() {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);
        
        Map<String, Object> body = new HashMap<>();
        body.put("name", "morpheus");
        body.put("job", "zion resident");
        httpRequest.body(body);
        
        Response response = httpRequest.put("/api/users/2");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Verify status code is 200");
        
        JsonPath jsonPath = response.jsonPath();
        String job = jsonPath.getString("job");
        String updatedAt = jsonPath.getString("updatedAt");
        
        Assert.assertEquals(job, "zion resident", "Verify job matches updated value");
        Assert.assertNotNull(updatedAt, "Verify updatedAt timestamp exists");
    }

    @Test
    public void testDeleteUser() {
        RequestSpecification httpRequest = RestAssured.given();
        
        Response response = httpRequest.delete("/api/users/2");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204, "Verify status code is 204 (No Content)");
    }
}
