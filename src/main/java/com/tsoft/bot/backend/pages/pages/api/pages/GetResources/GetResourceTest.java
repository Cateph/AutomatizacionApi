package com.tsoft.bot.backend.pages.pages.api.pages.GetResources;

import com.tsoft.bot.backend.base.BaseClass;
import com.tsoft.bot.backend.pages.objects.ExcelDataBackObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class GetResourceTest extends BaseClass {

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataBackObjects.EXCEL_DOC, ExcelDataBackObjects.PAGE_GETRESOURCES);
    }

    public void GetResources(String url) throws Throwable {
        int countPage = Integer.parseInt(url) - 1;
        url = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_URL);
        baseURI = url;
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Connection" , "keep-alive");
        given()
                .when()
                .get("/unknown")
                .then()
                .assertThat().headers(header);
    }

    public void ValidateSchema(){
        given()
                .when()
                .get("/unknown")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("getresources.json"));
    }

    public void GetResourcesByID(){
        given()
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .body("data[2].id",equalTo(3))
                .extract().body().asString();
    }

    public void GetResourceByName(){
        String response = given()
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .body("data[1].name",equalTo("fuchsia rose"))
                .extract().body().asString();
       }

    public void ValidateStatus(){
        int status = given()
                .when()
                .get("/unknown")
                .thenReturn()
                .statusCode();
        Assert.assertEquals(HttpStatus.SC_OK, status);
    }
}
