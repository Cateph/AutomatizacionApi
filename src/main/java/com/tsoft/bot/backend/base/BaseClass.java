package com.tsoft.bot.backend.base;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;

public class BaseClass {

    protected Map<String, Object> mapresgister = new HashMap<String, Object>();
    //HashMap variables para Create y Update"
    protected Map<String, Object> map = new HashMap<String, Object>();
    Date date = new Date();
    SimpleDateFormat dateformat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    protected String datehourtoday = dateformat.format(date.getTime());

    public void setup () {
        //this.invocationCount1 = Integer.parseInt(invocationCount);
        //String email = getData().get(this.invocationCount1).get(ExcelDataBackObjects.COLUMN_EMAIL);
        baseURI = "https://reqres.in/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        mapresgister.put("email","eve.holt@reqres.in");
        mapresgister.put("password", "pistol");
        //map.put("name", "morpheus");
       // map.put("job", "zion resident");

    }

}
