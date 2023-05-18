package JsonPlaceHolder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PlaceHolder {
    @BeforeClass
    public static void setUP(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
    @Test
    public void id_should_not_completed(){
        Response response= given()
                .basePath("todos")
                .when()
                .get("2")
                .then()
                .extract().response();
       Assert.assertEquals(response.getStatusCode(),200);
       Assert.assertEquals(response.jsonPath().getString("completed"),"false");
        response.prettyPrint();


    }@Test
    public void id_Should_completed(){
        Response response= given()
                .basePath("todos")
                .when()
                .get("4")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("completed"),"true");
        response.prettyPrint();
    }
    @Test
    public void verify_lat_and_lag(){
        Response response= given()
                .basePath("users")
                .when()
                .get("2")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("address.geo.lat"),"-43.9509");
        Assert.assertEquals(response.jsonPath().getString("address.geo.lng"),"-34.4618");
        response.prettyPrint();


    }

}
