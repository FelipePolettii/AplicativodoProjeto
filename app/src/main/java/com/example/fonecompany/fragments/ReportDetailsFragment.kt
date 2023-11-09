package com.example.fonecompany.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fonecompany.R
import com.example.fonecompany.databinding.FragmentReportDetailBinding
import com.example.fonecompany.fragments.viewModel.ReportDetailsViewModel
import com.example.fonecompany.model.ReportDetailDTO
import com.example.fonecompany.utils.FileUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReportDetailsFragment : Fragment() {
    private val viewModel: ReportDetailsViewModel by viewModels()
    private val reportDetailDTO = ReportDetailDTO(
        name = "Felipe Renó Valle Poletti",
        salary = 5000.0,
        inss = "1000",
        irpf = "1000",
        transportationValue = 1000.0,
        deductions = 1000.0,
        salaryLiquid = 4000.0,
        office = "Gerente",
    )
    private var _binding: FragmentReportDetailBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        binding.apply {
            tvName.apply {
                text = getString(
                    R.string.report_fragment_detail_name_label,
                    reportDetailDTO.name,
                    reportDetailDTO.office
                )
                colorText()
            }
            tvINSS.apply {
                text = getString(R.string.report_fragment_detail_inss_label, reportDetailDTO.inss)
                colorText()
            }
            tvIRPF.apply {
                text = getString(R.string.report_fragment_detail_irpf_label, reportDetailDTO.irpf)
                colorText()
            }
            tvWage.apply {
                text =
                    getString(R.string.report_fragment_detail_salary_label, reportDetailDTO.salary)
                colorText()
            }
            tvDeductions.apply {
                text = getString(
                    R.string.report_fragment_detail_deduction_label, reportDetailDTO.deductions
                )
                colorText()
            }
            tvSalaryLiquid.apply {
                text = getString(
                    R.string.report_fragment_detail_salary_liquid_label,
                    reportDetailDTO.salaryLiquid
                )
                colorText()
            }
            tvTransportationValue.apply {
                text = getString(
                    R.string.report_fragment_detail_transportation_value_label,
                    reportDetailDTO.transportationValue
                )
                colorText()
            }
            btnDownload.setOnClickListener{
                showDialogDownload()
            }
        }
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.pdfResponse.collect {
                        it?.let { response ->
                            val uri = FileUtil.saveFile(requireContext(),response.byteStream(),"Folha de Pagamento.pdf")
                            Intent(Intent.ACTION_VIEW).apply {
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                setDataAndType(uri,"application/pdf")
                                requireContext().startActivity(Intent.createChooser(this,"selecione um app"))
                            }
                        }
                    }
                }
                launch {
                    viewModel.error.collect {
                        it?.let { error ->
                            throw error
                        }
                    }
                }
            }
        }
    }

    private fun TextView.colorText(
    ) {
        val transformText = this.text.split(':').first()
        this.text = SpannableStringBuilder(this.text).apply {
            setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.white)),
                0,
                transformText.length + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
    fun showDialogDownload(){
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(R.string.report_fragment_detail_dialog_download_title)
            setPositiveButton(R.string.yes){_,_->
                viewModel.downloadPDF()
            }
            setNegativeButton(R.string.no){_,_->

            }

        }.show()
    }
}