package com.tsoft.bot.backend.pages.pages.api.pages.RegisterUser;
import static io.restassured.RestAssured.*;
import com.tsoft.bot.backend.base.BaseClass;
import com.tsoft.bot.backend.pages.objects.ExcelDataBackObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;


public class RegisterUserTest extends BaseClass {


    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataBackObjects.EXCEL_DOC, ExcelDataBackObjects.PAGE_REGISTER);
    }
    public void RegisterUser(String urlbody) throws Throwable {
        int countPage = Integer.parseInt(urlbody) - 1;
        String url = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_URL);
        String correorequest = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_EMAIL);
        String contrasenarequest = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_PASSWORD);
        baseURI = url;
        map.put("email", correorequest);
        map.put("password", contrasenarequest);

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Connection" , "keep-alive");

       given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("register")
                .then()
                .assertThat().headers(header);

    }

    public void ValidateSchema(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("register")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("register.json"));
    }

    public void ValidateToken(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("register")
                .then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }


    public void ValidateID() {

      given()
                .when()
                .contentType(ContentType.JSON)
                .body(map)
                .post("register")
                .then()
                .statusCode(200)
                .body("id", equalTo(4));

    }

       public void ValidateStatus(){
        int status = given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("register")
                .thenReturn()
                .statusCode();

        Assert.assertEquals(HttpStatus.SC_OK, status);
    }

}
