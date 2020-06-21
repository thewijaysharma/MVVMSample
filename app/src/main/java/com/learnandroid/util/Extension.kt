package com.learnandroid.util

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.learnandroid.BuildConfig
import com.learnandroid.base.App

fun AppCompatActivity.showToast(msg : String, duration : Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, msg, duration).show()
}

fun Any.logError(tag : String, description : String){
    if(BuildConfig.DEBUG){
        Log.e(tag, description)
    }
}