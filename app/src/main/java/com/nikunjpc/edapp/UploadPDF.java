package com.nikunjpc.edapp;

public class UploadPDF {
     String classtype;
     String name;
     String url;
     String info;

    public UploadPDF(){

    }

    public UploadPDF(String classtype, String name, String url, String info) {
        this.classtype=classtype;
        this.name = name;
        this.url = url;
        this.info=info;
    }

    public String getClasstype()
    {
        return classtype;
    }

    public void setClasstype(String classtype)
    {
        this.classtype = classtype;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }
}
