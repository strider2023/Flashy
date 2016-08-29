package com.touchmenotapps.flashy.dao;

import android.content.Context;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by arindamnath on 18/01/16.
 */
public abstract class BaseDAO {

    private Long id;
    private Context context;
    private Date today;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dateOfTheMonth;
    private DecimalFormat amountFormatter;

    public BaseDAO(Context context) {
        this.context = context;
        this.today = new Date();
        this.dateOfTheMonth = new SimpleDateFormat("dd");
        this.dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
        this.amountFormatter = new DecimalFormat("##,##,###.##");
    }

    protected abstract void parse(JSONParser jsonParser, JSONObject jsonObject);

    public Context getContext() {
        return context;
    }

    public DecimalFormat getAmountFormatter() {
        return amountFormatter;
    }

    public String getDate(Long millis) throws Exception {
        Date date = new Date(millis);
        /*if(DateUtils.isSameDay(today, date)) {
            return "Today";
        } *//*else if(DateUtils.isAfterDay(today, date)) {
            return "Tomorrow";
        } *//*else {*/
            return dateFormat.format(date);
        //}
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleDateFormat getDateOfTheMonth() {
        return dateOfTheMonth;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }
}
