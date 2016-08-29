package com.touchmenotapps.flashy.dao;

import android.content.Context;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by school on 26/8/16.
 */
public class SubjectsDAO extends BaseDAO {

    private int image;
    private String subjectName;

    public SubjectsDAO(Context context) {
        super(context);
    }

    @Override
    protected void parse(JSONParser jsonParser, JSONObject jsonObject) {

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
