package com.tianzunchina.android.api.login;


import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 * 用户实体类
 * CraetTime 2016-3-3
 * @author SunLiang
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int ID; // 用户编号
    private String account; //登陆名 用户名
    private String password;

    private String name; // 真实姓名
    private String IDCard; // 身份证号
    private String phoneNum; // 手机号
    private String address; // 居住地址
    private String email;
    private String headPath; //头像路径
    private String nickName;//昵称

    private Map<String, String> attributeMap;

    // 获取人员情况构造函数 （参数 flag 为了区别2个不同的构造函数）
    public User(JSONObject json, Context context, int flag)
            throws JSONException {
        ID = json.getInt("UserID");
        account = json.getString("Account");
    }

    private boolean isNull(String str) {
        return null == str || "null".equals(str) || "".equals(str) || 0 == str.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (ID != user.ID) return false;
        return account != null ? account.equals(user.account) : user.account == null;

    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }
}