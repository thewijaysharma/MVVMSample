package com.learnandroid.ui.repository

import com.learnandroid.model.UserModel
import com.learnandroid.rest.ApiService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class MainRepository(private val apiService: ApiService) {

    fun getUsers(param : String) : Single<List<UserModel>> {
//        return apiService.getTodos(param)
        return apiService.getTodos()
    }

}