package com.example.fonecompany.fragments.viewModel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fonecompany.AppApplication
import com.example.fonecompany.model.LoginRecDTO
import com.example.fonecompany.model.TokenDTO
import com.example.fonecompany.repository.token.TokenDataStore
import com.example.fonecompany.repository.token.TokenRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private val repositoy by lazy { TokenRepositoryImpl() }
    private val dataStore: TokenDataStore by lazy { TokenDataStore() }
    private val _loginRes = MutableStateFlow<TokenDTO?>(null)
    val loginRes = _loginRes.asLiveData()
    private val _skipLogin = MutableStateFlow<Boolean>(false)
    val skipLogin = _skipLogin.asLiveData()
    init {
        verifyisuserlogged()
    }
    fun login(email: String, cpf: String) {
        viewModelScope.launch {
            showLoading()
            repositoy.login(loginRecDTO = LoginRecDTO(email, cpf)).catch { err ->
                showError(err)
            }.collect {
                _loginRes.value = it
            }.also {
                _loginRes.value = null
            }
            hideLoading()
        }
    }

    private fun verifyisuserlogged() {
        viewModelScope.launch {
            dataStore.gettoken(AppApplication.instance).collect {
                if (!it.isNullOrBlank()) {
                    _skipLogin.value = true
                }
            }
        }
    }
}