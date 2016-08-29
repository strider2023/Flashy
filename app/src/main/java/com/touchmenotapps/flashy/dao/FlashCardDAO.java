package com.touchmenotapps.flashy.dao;

import android.content.Context;

import com.touchmenotapps.flashy.dao.enums.FlashCardType;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by school on 24/8/16.
 */
public class FlashCardDAO extends BaseDAO {

    private FlashCardType flashCardType;
    private String header;
    private String body;
    private String imageURL;

    public FlashCardDAO(Context context) {
        super(context);
    }

    @Override
    protected void parse(JSONParser jsonParser, JSONObject jsonObject) {

    }

    public FlashCardType getFlashCardType() {
        return flashCardType;
    }

    public void setFlashCardType(FlashCardType flashCardType) {
        this.flashCardType = flashCardType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
