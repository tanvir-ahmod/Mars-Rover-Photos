package com.example.marsroverphotos.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val showLoader = MutableLiveData<Boolean>(false)
    val showErrorMessage = MutableLiveData<String>()
}