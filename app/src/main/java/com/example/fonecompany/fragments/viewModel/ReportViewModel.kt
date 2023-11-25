package com.example.fonecompany.fragments.viewModel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fonecompany.model.ReportDetailResDTO
import com.example.fonecompany.model.ReportResDTO
import com.example.fonecompany.repository.reports.ReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Calendar

class ReportViewModel : BaseViewModel() {
    private val repository: ReportRepository by lazy { ReportRepository() }
    var monthSelected: Int = Calendar.getInstance().get(Calendar.MONTH)
    var yearSelected: Int = Calendar.getInstance().get(Calendar.YEAR)
    private val _reportresponse = MutableStateFlow<List<ReportResDTO>?>(null)
    val reportresponse = _reportresponse.asLiveData()
    fun loadreport(userId: String?) {
        viewModelScope.launch {
            val date = Calendar.getInstance().apply {
                set(Calendar.YEAR,yearSelected)
                set(Calendar.MONTH,monthSelected)
            }
            showLoading()
            repository.findreport(userId, date.time.time).catch {
                showError(it)
            }.collect {
                _reportresponse.value = it
            }.also { _reportresponse.value = null }
            hideLoading()
        }
    }
}