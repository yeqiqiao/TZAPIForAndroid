package com.tianzunchina.android.api.view;

import java.io.Serializable;

/**
 * 列表页面参数
 * CraetTime 2016-3-14
 * @author SunLiang
 */
public class InstalList implements Serializable{
    private String webServiceUrl;//列表接口文件地址
    private String namespace;//命名控件
    private String name;//接口名称
    private String skipNumber;//跳过条数参数名
    private String takeNumber;//每页个数参数名
    private String jsonListName;
    private int timeOut;

    public InstalList(String webServiceUrl, String namespace, String name, String skipNumber,
                      String takeNumber, String jsonListName, int timeOut) {
        this.webServiceUrl = webServiceUrl;
        this.namespace = namespace;
        this.name = name;
        this.skipNumber = skipNumber;
        this.takeNumber = takeNumber;
        this.timeOut = timeOut;
        this.jsonListName = jsonListName;
    }

    public String getWebServiceUrl() {
        return webServiceUrl;
    }

    public void setWebServiceUrl(String webServiceUrl) {
        this.webServiceUrl = webServiceUrl;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkipNumber() {
        return skipNumber;
    }

    public void setSkipNumber(String skipNumber) {
        this.skipNumber = skipNumber;
    }

    public String getTakeNumber() {
        return takeNumber;
    }

    public void setTakeNumber(String takeNumber) {
        this.takeNumber = takeNumber;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getJsonListName() {
        return jsonListName;
    }

    public void setJsonListName(String jsonListName) {
        this.jsonListName = jsonListName;
    }
}
