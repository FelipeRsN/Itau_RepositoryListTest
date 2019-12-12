package com.felipersn.itaurepositorylist.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

/**
 * Provide base variables and utils to be used in every viewModels.
 */
open class BaseViewModel  : ViewModel() {

    //Coroutine uiScope configuration
    private val viewModelJob = Job()
    protected val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    //Cancel coroutine scope to avoid memory leak
    override fun onCleared() {
        super.onCleared()
        uiScope.cancel()
    }
}