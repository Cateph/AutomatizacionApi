package com.tsoft.bot.backend.pages.pages.api.pages.UpdateUser;
import com.tsoft.bot.backend.base.BaseClass;
import com.tsoft.bot.backend.pages.objects.ExcelDataBackObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends BaseClass {

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataBackObjects.EXCEL_DOC, ExcelDataBackObjects.PAGE_UPDATE);
    }

    public void UpdateUser(String urlbody) throws Throwable {
        int countPage = Integer.parseInt(urlbody) - 1;
        String url = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_URL);
        String namerequest = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_NAME);
        String jobrequest = getData().get(countPage).get(ExcelDataBackObjects.COLUMN_JOB);
        baseURI = url;
        map.put("name", namerequest);
        map.put("job", jobrequest);

        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Connection" , "keep-alive");

        given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .then()
                .assertThat().headers(header);
    }

    public void ValidateSchema(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("update.json"));
    }
    public void ValidateName(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo(map.get("name")));
    }

    public void ValidateJob(){
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .then()
                .statusCode(200)
                .body("job", equalTo(map.get("job")));
    }

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

    public void ValidateStatus(){
        int code = given()
                .contentType(ContentType.JSON)
                .body(map)
                .put("users/2")
                .thenReturn()
                .statusCode();
        Assert.assertEquals(HttpStatus.SC_OK, code);

    }

}
