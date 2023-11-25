package com.example.fonecompany.fragments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asLiveData()
    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asLiveData()
    internal fun showError(error: Throwable) {
    error.printStackTrace()
        _error.value = error
        _error.value = null
    }

    internal fun showLoading() {
        _loading.value = true
    }

    internal fun hideLoading() {
        _loading.value = false
    }
}