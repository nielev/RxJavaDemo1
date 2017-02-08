package com.nielev.rxjavademo1.entity;

import java.util.List;

/**
 * Created by Neo on 2016/8/24.
 */
public class MovieEntity {
    public int count;
    public int start;
    public int total;
    public List<Subject> subjects;
    public class Subject{
        public String title;
        public String original_title;
        public String id;
        public String alt;
        public String subtype;
        public String year;

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

    @Override
    public String toString() {
        return "MovieEntity{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", subjects=" + subjects.toString() +
                '}';
    }
}
