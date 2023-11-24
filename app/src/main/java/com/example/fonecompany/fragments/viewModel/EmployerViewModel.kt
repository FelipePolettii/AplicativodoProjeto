package com.example.fonecompany.fragments.viewModel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fonecompany.model.EmployerDTO
import com.example.fonecompany.repository.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class EmployerViewModel : BaseViewModel() {
    private val repository: UserRepository by lazy { UserRepository() }
    private val _usersresponse = MutableStateFlow<List<EmployerDTO>?>(null)
    val userresponse = _usersresponse.asLiveData()

    init {
        fetchalluser()
    }

    private fun fetchalluser() {
        viewModelScope.launch {
            showLoading()
            repository.getalluser().catch {
                showError(it)
                throw it
            }.collect {
                _usersresponse.value = it
            }.also { _usersresponse.value = null }
            hideLoading()
        }
    }
}