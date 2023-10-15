package com.example.fonecompany.fragments

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
import com.example.fonecompany.R
import com.example.fonecompany.databinding.FragmentReportDetailBinding
import com.example.fonecompany.model.ReportDetailDTO

class ReportDetailsFragment : Fragment() {
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvName.text = getString(
                R.string.report_fragment_detail_name_label,
                reportDetailDTO.name,
                reportDetailDTO.office
            )
            tvINSS.text =
                getString(R.string.report_fragment_detail_inss_label, reportDetailDTO.inss)
            tvIRPF.text =
                getString(R.string.report_fragment_detail_irpf_label, reportDetailDTO.irpf)
            tvWage.text =
                getString(R.string.report_fragment_detail_salary_label, reportDetailDTO.salary)
            tvDeductions.text = getString(
                R.string.report_fragment_detail_deduction_label,
                reportDetailDTO.deductions
            )
            tvSalaryLiquid.text = getString(
                R.string.report_fragment_detail_salary_label,
                reportDetailDTO.salaryLiquid
            )
            tvTransportationValue.text = getString(
                R.string.report_fragment_detail_transportation_value_label,
                reportDetailDTO.transportationValue
            )
        }
    }
    fun TextView.colorText(
    ): SpannableStringBuilder {
        val transformText = "${text.toString()} ${text.split(':').first()}"
        return SpannableStringBuilder(transformText).apply {
            setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.white)),
                transformText.indexOf(coloredText),
                (transformText.indexOf(coloredText) + coloredText.length),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

}