package com.touchmenotapps.flashy.dao;

import android.content.Context;

import com.touchmenotapps.flashy.dao.enums.Subject;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by school on 24/8/16.
 */
public class LessonsDAO extends BaseDAO {

    private String subjectName;
    private String chapterName;
    private String revisionTime;
    private String imageUrl;
    private Subject subject;
    private boolean isComplete;

    public LessonsDAO(Context context) {
        super(context);
    }

    @Override
    protected void parse(JSONParser jsonParser, JSONObject jsonObject) {

    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getRevisionTime() {
        return revisionTime;
    }

    public void setRevisionTime(String revisionTime) {
        this.revisionTime = revisionTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
