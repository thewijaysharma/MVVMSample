package com.learnandroid.rest

import com.learnandroid.model.UserModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("todos")
    fun getTodos() : Single<List<UserModel>>
//    fun getTodos(@Query("param") param : String) : Observable<List<UserModel>>
}