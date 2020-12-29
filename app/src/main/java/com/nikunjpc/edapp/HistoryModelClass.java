package com.nikunjpc.edapp;

public class HistoryModelClass {

    String class_type;
    String file_name;
    int id;

    public HistoryModelClass(String class_type, String file_name) {
        this.class_type = class_type;
        this.file_name = file_name;
    }

    public HistoryModelClass(int id, String class_type, String file_name) {
        this.class_type = class_type;
        this.file_name = file_name;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
