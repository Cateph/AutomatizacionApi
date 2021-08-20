package com.tsoft.bot.backend.test;
import static io.restassured.RestAssured.*;
import com.tsoft.bot.backend.pages.pages.api.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;

public class PruebasApi {

    @BeforeClass
    public void setup(){
        baseURI = "https://reqres.in/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void Asserciones1(){
           given()
                .contentType(ContentType.JSON)
                .body("{\n"+
                        "\"email\": \"eve.holt@reqres.in\",\n"+
                        "\"password\": \"cityslicka\"\n"+
                        "}")
                .post("login")
                .then()
                .log().all()
                .statusCode(200)
                .body("token", notNullValue());
        //Verificar que el codigo sea 200 y el token no sea nulo
    }

   @Test
    public void testGetUsuarios(){
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("data[2].first_name",equalTo("Emma"))
                .extract().body().asString();

    }
    @Test
    public void testPostUsuarios(){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name","Candy");
        map.put("job", "User Succes");
        given()
                .body(map)
                .when()
                .log().all()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);
    }
    @Test
    public void ValidacionSchema(){

           given()
                   .when()
                   .get("/users/2")
                   .then()
                   .assertThat()
                   .body(matchesJsonSchemaInClasspath("testschema.json"));
    }

    @Test
    public void testLogin(){

         given()
                 .log().all()
                .contentType(ContentType.JSON)
                .body("{\n"+
                        "\"email\": \"eve.holt@reqres.in\",\n"+
                        "\"password\": \"cityslicka\"\n"+
                     "}")
                .post("/login")
                .then()
                .log().all()
                 .extract()
                 .asString();

    }

    @Test
    public void Asserciones2(){
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .get("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", equalTo(2));

    }

    @Test
    public void FiltrarDatos(){
        String response = given()
                .when()
                .get("users?page=2").then().extract().body().asString();

        int page = from(response).get("page");
        int totalPages = from(response).get("total_pages");
        int idFirstUser = from(response).get("data[0].id");

        System.out.println("page: "+page);
        System.out.println("total pages: "+totalPages);
        System.out.println("id first user: "+idFirstUser);


        List<Map> usuariosIdmayora10 = from(response).get("data.findAll {user -> user.id > 10}");
        String email = usuariosIdmayora10.get(0).get("email").toString();

        List<Map> usuarios = from(response).get("data.findAll {user -> user.id > 10 && user.last_name == 'Howell'}");
        int id = Integer.valueOf(usuarios.get(0).get("id").toString());
    }

    @Test
    public void StringaClaseJava(){
        String response = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("users").then().extract().body().asString();

        User user = from(response).getObject("", User.class);
        System.out.println(user.getId());

    }
}
