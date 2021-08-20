package com.tsoft.bot.backend.test;

import com.tsoft.bot.backend.base.BaseClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

public class GetResourceTest extends BaseClass {

    @Test
    public void GetResources(){
        given()
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .extract().body().asString();
    }

    @Test
    public void ValidateSchema(){
        given()
                .when()
                .get("/unknown")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("getresources.json"));
    }

    @Test
    public void GetResourcesByID(){
        given()
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .body("data[2].id",equalTo(3))
                .extract().body().asString();
    }

    @Test
    public void FilterResources(){
        String response = given()
                .when()
                .get("unknown").then().extract().body().asString();

        List<Map> idmayorque = from(response).get("data.findAll {resource -> resource.id > 4}");
        System.out.println("Existen elementos con id > 4");
        System.out.println(idmayorque);

    }

    @Test
    public void GetResourceByName(){
        String response = given()
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .body("data[1].name",equalTo("fuchsia rose"))
                .extract().body().asString();
       }
}
