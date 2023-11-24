package com.example.fonecompany.fragments.viewModel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fonecompany.model.UserDTO
import com.example.fonecompany.repository.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OnBoardingViewModel : BaseViewModel() {
    private val repository: UserRepository by lazy { UserRepository() }
    private val _meresponse = MutableStateFlow<UserDTO?>(null)
    val meresponse = _meresponse.asLiveData()

    init {
        me()
    }

    private fun me() {
        viewModelScope.launch {
            showLoading()
            repository.me().catch {
                showError(it)
            }.collect {
                _meresponse.value = it
            }
            hideLoading()
        }
    }
    fun finduser(userId: String){
        viewModelScope.launch {
            showLoading()
            repository.finduser(userId).catch {
                showError(it)
            }.collect{
                _meresponse.value = it
            }
            hideLoading()
        }
    }
}