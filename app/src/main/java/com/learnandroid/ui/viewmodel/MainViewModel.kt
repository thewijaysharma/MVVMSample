package com.learnandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learnandroid.model.UserModel
import com.learnandroid.rest.ApiService
import com.learnandroid.ui.repository.MainRepository
import com.learnandroid.util.Response
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainViewModel(private val apiService: ApiService, private val param: String) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val repository = MainRepository(apiService)
    private val users = MutableLiveData<Response<List<UserModel>>>()

    fun fetchUsers()  : LiveData<Response<List<UserModel>>>{

        val disposable = repository.getUsers(param).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                users.postValue(Response.loading(null))
            }
            .subscribe ({
                users.postValue(Response.success(it))
            },{
                when(it){
                    is SocketTimeoutException -> users.postValue(Response.error(Response.TIMEOUT, null))
                    is UnknownHostException -> users.postValue(Response.error(Response.NO_INTERNET, null))
                    else -> users.postValue(Response.error(Response.API_ERROR, null))
                }

            })
        compositeDisposable.add(disposable)

        return users
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
    
}