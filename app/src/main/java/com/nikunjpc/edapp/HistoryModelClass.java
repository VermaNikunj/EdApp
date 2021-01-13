package com.nikunjpc.edapp;

import android.util.Log;

public class HistoryModelClass {

    String class_type;
    String file_name;
    int id;
    String url;

    public HistoryModelClass(String class_type, String file_name, String url) {
        this.class_type = class_type;
        this.file_name = file_name;
        this.url=url;
        Log.e("Url CHECK 3", url);
        Log.e("Url CHECK 4", this.url);

        Log.e("Url CHECK 5", getUrl());

    }

    public HistoryModelClass(int id, String class_type, String file_name, String url) {
        this.class_type = class_type;
        this.file_name = file_name;
        this.id = id;
        this.url=url;
        }

    public String getClass_type() {
        return class_type;
    }

    public void setClass_type(String class_type) {
        this.class_type = class_type;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
