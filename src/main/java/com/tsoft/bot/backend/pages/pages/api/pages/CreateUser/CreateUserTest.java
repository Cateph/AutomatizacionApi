package com.tsoft.bot.backend.pages.pages.api.pages.CreateUser;
import com.tsoft.bot.backend.base.BaseClass;
import com.tsoft.bot.backend.pages.objects.ExcelDataBackObjects;
import com.tsoft.bot.backend.pages.pages.api.pages.CreateUser.CreateUser;
import com.tsoft.bot.frontend.utility.ExcelReader;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseClass {

    Map<String, Object> map = new HashMap<String, Object>();

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataBackObjects.EXCEL_DOC, ExcelDataBackObjects.PAGE_NAME);
    }
    public void CreateUser(String urlbody) throws Throwable {

        int countPage = Integer.parseInt(urlbody) - 1;
        String url = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_URL);
        String namerequest = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_NAME);
        String jobrequest = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_JOB);
        baseURI = url;
        map.put("name", namerequest);
        map.put("job", jobrequest);

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Connection" , "keep-alive");
        header.put("Server" , "cloudflare");
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .assertThat().headers(header);
    }
    public void ValidateSchema(){

        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("create.json"));
    }

     public void ValidateStatus(){

     int status =   given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .thenReturn()
                .statusCode();
        Assert.assertEquals(HttpStatus.SC_CREATED,status);

    }

    public void ValidateName(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .statusCode(201)
                .body("name", equalTo(map.get("name")));
    }

    public void ValidateJob(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .statusCode(201)
                .body("job", equalTo(map.get("job")));
    }

    public void ValidateCreateDate(){
        String response =  given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("users")
                .then()
                .statusCode(201).extract().asString();

     /*   CreateUser create = from(response).getObject("", CreateUser.class);
        String datecreate = create.getCreatedAt().substring(0,10);
        String datetoday = datehourtoday.substring(0,10);
        Assert.assertEquals(datecreate,datetoday);*/
    }
}
