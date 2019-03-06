package cz.uhk.umte.prefs

import android.content.Context
import android.content.SharedPreferences

const val NAME = "UmteAppName"

const val APP_START = "APP_START"

object Prefs {
    //? - nullable
    private var sharedPreferences : SharedPreferences? = null

    //sigleton - má odkaz sám na sebe - nezahodí jí garbage colector
    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    fun setAppStart(millis: Long){
        sharedPreferences!!.edit().putLong(APP_START, millis).apply()
    }

    fun getAppStart() : Long{
        return sharedPreferences!!.getLong(APP_START, 0)
    }

}