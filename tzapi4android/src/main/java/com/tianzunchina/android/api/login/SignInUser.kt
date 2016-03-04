package com.tianzunchina.android.api.login

import com.tianzunchina.android.api.utils.LoginConfig

import java.util.HashMap

/**
 * 登录信息实体类
 * CraetTime 2016-3-3
 * @author SunLiang
 */
class SignInUser {

    var account: String? = null
    var password: String? = null
    var isKeep = false
    var isAuto = false

    constructor(userName: String, userPassword: String) {
        this.account = userName
        this.password = userPassword
    }

    constructor(userName: String, userPassword: String, isKeep: Boolean) {
        this.account = userName
        this.password = userPassword
        this.isKeep = isKeep
    }

    constructor(userName: String, userPassword: String, isKeep: Boolean,
                isAuto: Boolean) {
        this.account = userName
        this.password = userPassword
        this.isKeep = isKeep
        this.isAuto = isAuto
    }

    constructor(userName: String) {
        this.account = userName
    }

    val data: Map<String, String> get() {
        val map = HashMap<String, String>()
        map.put(LoginConfig.getUserName(), account!!)
        map.put(LoginConfig.getUserPassword(), password!!)
        return map
    }

    fun save(){
        LoginConfig.getInstence().saveUser(this)
    }

    override fun toString(): String {
        return "SignInUser [account=$account, password=$password, isAuto=$isAuto]"
    }
}
