package com.innoventesmovistv.myapplication.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val mError = MutableLiveData<ErrorWrapper>()
    val error: LiveData<ErrorWrapper>
        get() = mError
}