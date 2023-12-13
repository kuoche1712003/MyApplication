package com.example.myapplication

import android.content.Context
import android.util.Log

class MyManager internal constructor(
    private val context: Context
){

    fun saveEmail(email: String){
        val sp = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        with(sp.edit()){
            putString("email", email)
            apply()
        }
    }

    fun getEmail(): String{
        Log.d("Singleton", "Inside Context hashCode: " + context.hashCode());

        val sp = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        return sp.getString("email", "no found") ?: "no found"
    }

    companion object{
        private var instance: MyManager? = null
        private var context: Context? = null

        @JvmStatic
        fun init(context: Context){
            if (instance != null) {
                throw IllegalStateException("already init")
            }
            this.context = context.applicationContext
        }

        @JvmStatic
        fun getInstance(): MyManager {
            val context = this.context ?: throw IllegalStateException("un ready")
            return instance ?: MyManager(
                context
            ).apply {
                instance = this
            }
        }
    }

}