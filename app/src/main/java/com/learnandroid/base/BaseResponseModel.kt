package com.learnandroid.base

data class BaseResponseModel<T>(val status: String, val msg: String, val code: Int?, val data: T?,
                                var respType : Int)

class BaseResponseEmptyDataModel
