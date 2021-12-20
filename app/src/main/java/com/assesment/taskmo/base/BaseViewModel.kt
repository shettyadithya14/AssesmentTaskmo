package com.assesment.taskmo.base

import com.assesment.taskmo.utils.commons.toSingleEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseViewModel() : ViewModel() {

    private val mUiState = MutableLiveData<Any>()
    open val uiState: LiveData<Any> = mUiState.toSingleEvent()

    fun <T : Any> updateViewState(result: T) {
        mUiState.value = result
    }

}