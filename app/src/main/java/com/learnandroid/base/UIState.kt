package com.learnandroid.base

sealed class UIState{
    object Loading : UIState()
    object HasData : UIState()
    object NoData : UIState()
    class Error(val msg : String) : UIState()
}

sealed class ViewEffect(){
    data class ShowToast(val msg : String) : ViewEffect()
}

enum class Status{
    LOADING, SUCCCESS, ERROR
}