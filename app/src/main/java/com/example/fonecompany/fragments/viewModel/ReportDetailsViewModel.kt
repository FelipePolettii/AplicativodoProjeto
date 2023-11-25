package com.example.fonecompany.fragments.viewModel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fonecompany.model.ReportDetailResDTO
import com.example.fonecompany.repository.reports.ReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class ReportDetailsViewModel : BaseViewModel() {
    private val repository: ReportRepository by lazy { ReportRepository() }
    private val _reportresponse = MutableStateFlow<ReportDetailResDTO?>(null)
    val reportresponse = _reportresponse.asLiveData()
    private val _pdfResponse = MutableStateFlow<ResponseBody?>(null)
    val pdfResponse = _pdfResponse.asLiveData()
    fun downloadPDF(userId: String, reportId: String) {
        viewModelScope.launch {
            showLoading()
            repository.downloadpdf(userId, reportId).catch {
                showError(it)
            }.collect {
                _pdfResponse.value = it
            }
            hideLoading()
        }
    }

    fun fetchreportdetails(reportId: String) {
        viewModelScope.launch {
            showLoading()
            repository.findreportbyid(reportId).catch {
                showError(it)
            }.collect {
                _reportresponse.value = it
            }
            hideLoading()
        }
    }
}