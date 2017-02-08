package com.nielev.rxjavademo1.entity;

/**
 * Created by Neo on 2016/8/26.
 */
public class Subject {
    public String title;
    public String original_title;
    public String id;
    public String alt;
    public String subtype;
    public String year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", id='" + id + '\'' +
                ", alt='" + alt + '\'' +
                ", subtype='" + subtype + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
