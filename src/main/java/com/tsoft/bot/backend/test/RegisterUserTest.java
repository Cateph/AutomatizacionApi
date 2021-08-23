package com.tsoft.bot.backend.test;
import static io.restassured.RestAssured.*;
import com.tsoft.bot.backend.base.BaseClass;
import com.tsoft.bot.backend.pages.pages.api.RegisterUser.Register;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.path.json.JsonPath.from;

public class RegisterUserTest extends BaseClass {

    @Test
    public void RegisterUser(){
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Connection" , "keep-alive");

       given()
                .contentType(ContentType.JSON)
                .body(mapresgister)
                .post("register")
                .then()
                .assertThat().headers(header);

    }
    @Test
    public void ValidateSchema(){
        given()
                .contentType(ContentType.JSON)
                .body(mapresgister)
                .post("register")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("register.json"));
    }

    @Test
    public void ValidarToken(){
        given()
                .contentType(ContentType.JSON)
                .body(mapresgister)
                .post("register")
                .then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void ValidarID() {

      given()
                .when()
                .contentType(ContentType.JSON)
                .body(mapresgister)
                .post("register")
                .then()
                .statusCode(200)
                .body("id", equalTo(4));

    }


    @Test
    public void ValidarStatus(){
        int status = given()
                .contentType(ContentType.JSON)
                .body(mapresgister)
                .post("register")
                .thenReturn()
                .statusCode();

        Assert.assertEquals(HttpStatus.SC_OK, status);
    }

}
