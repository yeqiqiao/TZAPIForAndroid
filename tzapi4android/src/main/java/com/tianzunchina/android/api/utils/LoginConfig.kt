package com.tianzunchina.android.api.utils

import com.tianzunchina.android.api.login.SignInUser

/**
 * 登录信息记录类(kotlin中可以直接定义object 实现单例效果)
 * CraetTime 2016-3-4
 * @author SunLiang
 */
object  LoginConfig : Config() {
	val USER_NAME = "account"
	val USER_PASSWORD = "password"
	private val IS_SAVE_PASSWORD = "isKeep"

    /**
     * 保存登录状态
     * @param user 包含userName及userPassword的对象
     * *
     * @return 没有返回值
     */
    fun saveUser(user: SignInUser) {
        //		保存用户名及密码信息 并标记是否记住密码及自动登录
        saveInfo(USER_NAME, user.account)
        if (user.isKeep) {
            saveInfo(USER_PASSWORD, user.password)
        }
        saveInfo(IS_SAVE_PASSWORD, user.isKeep)
    }

    /*
	 * 判断是否保存登录状态
	 * 将之前在本地登录的信息进行返回
	 * @return 返回本地保存的User对象
	 */
    fun loadUser(): SignInUser {
        var user: SignInUser? = null
        val userName = loadString(USER_NAME)
        val password = loadString(USER_PASSWORD)
        val isKeep = loadBoolean(IS_SAVE_PASSWORD)
        if (userName != null) {
            user = SignInUser(userName, password, isKeep)
        }
        return user!!
    }

    fun removePWD() {
        removeInfo(USER_PASSWORD)
        removeInfo(IS_SAVE_PASSWORD)
    }
}