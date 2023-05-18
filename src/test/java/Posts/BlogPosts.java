package Posts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BlogPosts {
    @BeforeClass
    public static void setUP(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

    }
    @Test
    public void It_should_get_post_byId(){
        System.out.println("Hello word");
        Response response = given()
                .basePath("posts")
                .when()
                .get("5")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("title"),"nesciunt quas odio");
        Assert.assertEquals(response.jsonPath().getString("body"),"repudiandae veniam quaerat sunt sed\n" +
                "alias aut fugiat sit autem sed est\n" +
                "voluptatem omnis possimus esse voluptatibus quis\n" +
                "est aut tenetur dolor neque");
       response.prettyPrint();


    }
    @Test
    public void it_should_get_comments_by_postId(){
      //  https://jsonplaceholder.typicode.com/comments?postId=1
        Response response = given()
                .basePath("comments")
                .queryParam("postId","1")
                .when()
                .get()
                .then()
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getString("[0].email"),"Eliseo@gardner.biz");

    }
    @Test
    public void it_should_create_new_post(){
        Response response = given()
                .basePath("posts")
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                                "    \"userId\": 1,\n" +
                                "    \"title\": \"Dummy title\",\n" +
                                "    \"body\": \"sample body\"\n" +
                                "}"
                        )
                .post()
                .then()
                .extract()
                .response();
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getInt("id"),101);
        Assert.assertEquals(response.jsonPath().getString("userId"),"1");
        Assert.assertEquals(response.jsonPath().getString("title"),"Dummy title");
        Assert.assertEquals(response.jsonPath().getString("body"),"sample body");


    }

}
