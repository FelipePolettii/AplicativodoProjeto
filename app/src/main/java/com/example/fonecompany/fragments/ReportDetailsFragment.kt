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
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fonecompany.R
import com.example.fonecompany.databinding.FragmentReportDetailBinding
import com.example.fonecompany.fragments.viewModel.ReportDetailsViewModel
import com.example.fonecompany.utils.FileUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ReportDetailsFragment : Fragment() {
    private val args: ReportDetailsFragmentArgs by navArgs()
    private val viewModel: ReportDetailsViewModel by viewModels()
    private val loading: DialogProgress by lazy { DialogProgress() }
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
        viewModel.fetchreportdetails(args.reportId)

    }

    private fun observers() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) loading.showLoading(parentFragmentManager) else loading.hideLoading()
        }
        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.reportresponse.observe(viewLifecycleOwner) {
            it?.let {
                binding.apply {
                    tvName.apply {
                        text = getString(
                            R.string.report_fragment_detail_name_label, it.name, it.office
                        )
                        colorText()
                    }
                    tvINSS.apply {
                        text = getString(R.string.report_fragment_detail_inss_label, it.inss)
                        colorText()
                    }
                    tvIRPF.apply {
                        text = getString(R.string.report_fragment_detail_irpf_label, it.irpf)
                        colorText()
                    }
                    tvWage.apply {
                        text = getString(
                            R.string.report_fragment_detail_salary_label, it.salary.toFloat()
                        )
                        colorText()
                    }
                    tvDeductions.apply {
                        text = getString(
                            R.string.report_fragment_detail_deduction_label, it.deductions.toFloat()
                        )
                        colorText()
                    }
                    tvSalaryLiquid.apply {
                        text = getString(
                            R.string.report_fragment_detail_salary_liquid_label,
                            it.salaryLiquid.toFloat()
                        )
                        colorText()
                    }
                    tvTransportationValue.apply {
                        text = getString(
                            R.string.report_fragment_detail_transportation_value_label,
                            it.transportationValue.toFloat()
                        )
                        colorText()
                    }
                    btnDownload.setOnClickListener {
                        showDialogDownload()
                    }
                }
            }
        }
        viewModel.pdfResponse.observe(viewLifecycleOwner) {
            it?.let { response ->
                val uri = FileUtil.saveFile(
                    requireContext(), response.byteStream(), "Folha de Pagamento.xlsx"
                )
                Intent(Intent.ACTION_VIEW).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    setDataAndType(uri, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    requireContext().startActivity(Intent.createChooser(this, "selecione um app"))
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

    fun showDialogDownload() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(R.string.report_fragment_detail_dialog_download_title)
            setPositiveButton(R.string.yes) { _, _ ->
                viewModel.downloadPDF(viewModel.reportresponse.value?.userId ?: "", args.reportId)
            }
            setNegativeButton(R.string.no) { _, _ ->

            }

        }.show()
    }
}