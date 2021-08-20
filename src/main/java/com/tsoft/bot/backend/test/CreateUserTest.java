package com.tsoft.bot.backend.test;
import com.tsoft.bot.backend.base.BaseClass;
import com.tsoft.bot.backend.pages.pages.api.CreateUser;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseClass {

    @Test
    public void CreateUser(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .statusCode(201);
    }

    @Test
    public void ValidateSchema(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("create.json"));
    }

    @Test
    public void ValidateStatus(){
     int status =   given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .thenReturn()
                .statusCode();
        Assert.assertEquals(HttpStatus.SC_CREATED,status);
    }

    @Test
    public void ValidateName(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .statusCode(201)
                .body("name", equalTo(map.get("name")));
    }

    @Test
    public void ValidateJob(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .statusCode(201)
                .body("job", equalTo(map.get("job")));
    }

     @Test
    public void ValidateCreateDate(){
        String response =  given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .statusCode(201).extract().asString();

        CreateUser create = from(response).getObject("", CreateUser.class);
        String datecreate = create.getCreatedAt().substring(0,10);
        String datetoday = datehourtoday.substring(0,10);
        Assert.assertEquals(datecreate,datetoday);
    }
}
