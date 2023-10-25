package com.example.fonecompany.fragments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fonecompany.repository.PDFRepository
import com.example.fonecompany.repository.PDFRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class ReportDetailsViewModel : ViewModel() {
    private val repository: PDFRepository by lazy { PDFRepositoryImpl() }
    private val _pdfResponse = MutableStateFlow<ResponseBody?>(null)
    val pdfResponse = _pdfResponse.asStateFlow()
    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()
    fun downloadPDF() {
        val request = flow<ResponseBody> {
            emit(repository.downloadPDF())
        }
        viewModelScope.launch(Dispatchers.IO) {
            request.catch {
                _error.value = it
            }.collect {
                _pdfResponse.value = it
            }
        }
    }
}