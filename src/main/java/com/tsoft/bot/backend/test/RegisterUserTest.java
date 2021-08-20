package com.tsoft.bot.backend.test;
import static io.restassured.RestAssured.*;
import com.tsoft.bot.backend.base.BaseClass;
import com.tsoft.bot.backend.pages.pages.api.Register;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.path.json.JsonPath.from;

public class RegisterUserTest extends BaseClass {

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
    public void ValidarTokenCorrecto(){
        given()
                .contentType(ContentType.JSON)
                .body(mapresgister)
                .post("register")
                .then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void FiltrarID(){

        String response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(mapresgister)
                .post("register")
                .then()
                .statusCode(200).extract().asString();

        Register register = from(response).getObject("", Register.class);
        System.out.println("El id del usuario es "+register.getId());

    }

    @Test
    public void FiltrarToken(){
        String response =  given()
                .contentType(ContentType.JSON)
                .body(mapresgister)
                .post("register")
                .then()
                .statusCode(200).extract().asString();

        Register register = from(response).getObject("", Register.class);
        System.out.println("El token del usuario es "+register.getToken());

    }

}
