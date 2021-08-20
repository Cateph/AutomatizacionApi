package com.tsoft.bot.backend.test;

import com.tsoft.bot.backend.base.BaseClass;
import com.tsoft.bot.backend.pages.pages.api.UpdateUser;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends BaseClass {


    @Test
    public void UpdateUser(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .then()
                .statusCode(200);
    }
    @Test
    public void ValidateSchema(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("update.json"));
    }
    @Test
    public void ValidateStatus(){
        int code = given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .thenReturn()
                .statusCode();
        Assert.assertEquals(HttpStatus.SC_OK, code);

    }
    @Test
    public void ValidateName(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo(map.get("name")));
    }

    @Test
    public void ValidateJob(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .then()
                .statusCode(200)
                .body("job", equalTo(map.get("job")));
    }
    @Test
    public void ValidateUpdateDate(){
        String response =  given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .then()
                .statusCode(200).extract().asString();

        UpdateUser updateUser = from(response).getObject("", UpdateUser.class);
        String dateupdate = updateUser.getUpdatedAt().substring(0,10);
        String datetoday = datehourtoday.substring(0,10);
        Assert.assertEquals(dateupdate,datetoday);

    }

}
