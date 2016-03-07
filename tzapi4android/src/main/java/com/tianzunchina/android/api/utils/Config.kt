package com.tianzunchina.android.api.utils

import android.app.Activity
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.tianzunchina.android.api.control.TZApplication

/**
 * 配置信息管理的父类
 * 所有用到的配置信息管理都继承此类 以便于对信息进行保存及读取
 * CraetTime 2016-3-3
 * @author SunLiang
 */
open class Config {
    /**
	 * @return 获取一个唯一的私有的SharedPreferences
	 */
    private fun initSP() {
		if (share == null) {
			share = TZApplication.instance!!.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE)
		}
	}


    /**
	 * 提供配置信息修改的编辑器
	 * @return 获取一个唯一的私有的Editor
	 */
    private val ed: Editor
        get() {
            if (editor == null) {
                if (share == null) {
					initSP()
                }
                editor = share!!.edit()
                editor!!.commit()
            }
            return editor as Editor
        }

    /**
	 * 提交对编辑器内容的修改
	 * 在对属性editor进行修改后请调用此方法进行保存
	 * @return 无返回值
	 */
    private fun edCommit() {
        editor!!.commit()
    }

    /**
	 * 读取配置中key对应的String内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回null
	 */
    protected fun loadString(key: String): String {
		initSP()
        return share!!.getString(key, null)
    }

    /**
	 * 读取配置中key对应的String内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
    protected fun loadString(key: String, defVal: String): String {
		initSP()
        return share!!.getString(key, defVal)
    }

    /**
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回false
	 */
    protected fun loadBoolean(key: String): Boolean {
		initSP()
        return share!!.getBoolean(key, false)
    }

    /**
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
    protected fun loadBoolean(key: String, defVal: Boolean?): Boolean {
		initSP()
        return share!!.getBoolean(key, defVal!!)
    }

    /**
	 * 读取配置中key对应的int内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回0
	 */
    protected fun loadInt(key: String): Int {
		initSP()
        return share!!.getInt(key, 0)
    }

    /**
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
    protected fun loadInt(key: String, defVal: Int): Int {
		initSP()
        return share!!.getInt(key, defVal)
    }

    /**
	 * 读取配置中key对应的long内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回0
	 */
    protected fun loadLong(key: String): Long {
		initSP()
        return share!!.getInt(key, 0).toLong()
    }

    /**
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
    protected fun loadLong(key: String, defVal: Long): Long {
		initSP()
        return share!!.getLong(key, defVal)
    }

    /**
	 * 读取配置中key对应的long内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回0
	 */
    protected fun loadFloat(key: String): Float? {
		initSP()
        return share!!.getFloat(key, 0f)
    }

    /**
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
    protected fun loadFloat(key: String, defVal: Float?): Float? {
		initSP()
        return share!!.getFloat(key, defVal!!)
    }

    /**
	 * 将key及对应的String类型的val保存至配置中
	 * @param key 保存的键值
	 * @param val key对应的String类型的值
	 * @return 无返回值
	 */
    protected fun saveInfo(key: String, `val`: String) {
        ed
        editor!!.putString(key, `val`)
        edCommit()
    }

    /**
	 * 将key及对应的String类型的val保存至配置中 并提交修改
	 * @param key 保存的键值
	 * @param val key对应的boolean类型的值
	 * @return 无返回值
	 */
    protected fun saveInfo(key: String, `val`: Boolean) {
        ed
        editor!!.putBoolean(key, `val`)
        edCommit()
    }

    /**
	 * 将key及对应的String类型的val保存至配置中 并提交修改
	 * @param key 保存的键值
	 * @param val key对应的int类型的值
	 * @return 无返回值
	 */
    protected fun saveInfo(key: String, `val`: Int) {
        ed
        editor!!.putInt(key, `val`)
        edCommit()
    }

    /**
	 * 将key及对应的String类型的val保存至配置中 并提交修改
	 * @param key 保存的键值
	 * @param val key对应的long类型的值
	 * @return 无返回值
	 */
    protected fun saveInfo(key: String, `val`: Long) {
        ed
        editor!!.putLong(key, `val`)
        edCommit()
    }

    /**
	 * 将key及对应的String类型的val保存至配置中 并提交修改
	 * @param key 保存的键值
	 * @param val key对应的Float类型的值
	 * @return 无返回值
	 */
    protected fun saveInfo(key: String, `val`: Float?) {
        ed
        editor!!.putFloat(key, `val`!!)
        edCommit()
    }

    val spCon: String
        get() {
			initSP()
            return share!!.toString()
        }

    /**
     * 删除key对应的配置信息
     * @param key 键值
     * @return 无返回值
     */
    protected fun removeInfo(key: String) {
        ed
        editor!!.remove(key)
        edCommit()
    }

    companion object {
        private var share: SharedPreferences? = null
        private var editor: Editor? = null
        private val PREFERENCE_NAME = "saveInfo"
    }
}