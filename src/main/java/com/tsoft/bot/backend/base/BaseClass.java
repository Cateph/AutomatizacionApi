package com.tsoft.bot.backend.base;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseClass {

    //HashMap variables para Definir variables"
    protected Map<String, Object> map = new HashMap<String, Object>();
    Date date = new Date();
    SimpleDateFormat dateformat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    protected String datehourtoday = dateformat.format(date.getTime());

}
