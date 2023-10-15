package com.example.fonecompany.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fonecompany.R
import com.example.fonecompany.adapter.ReportAdapter
import com.example.fonecompany.databinding.FragmentReportBinding
import com.example.fonecompany.model.ReportList
import com.example.fonecompany.model.ReportResDTO
import java.util.Calendar
import java.util.Date

class ReportFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private val reportResDTO = ReportResDTO(listOf(ReportList(date = Date())))
    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!
    private val reportAdapter: ReportAdapter by lazy {
        ReportAdapter(onItemClick = {
            handleReportClick(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.months,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMonth.adapter = adapter
        binding.spinnerMonth.onItemSelectedListener = this

        val adapterYear = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            buildYears(),
        )
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerYear.adapter = adapterYear
        binding.rv.adapter = reportAdapter
        reportAdapter.submitList(reportResDTO.list)
    }

    private fun buildYears(): List<Int> {
        val list = arrayListOf<Int>()
        val calendar = Calendar.getInstance()
        list.add(calendar.get(Calendar.YEAR))
        for (i in -4..-1) {
            calendar.add(Calendar.YEAR, -1)
            list.add(calendar.get(Calendar.YEAR))
        }
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(
            requireContext(),
            resources.getStringArray(R.array.months).get(position),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun handleReportClick(item: ReportList) {
        findNavController().navigate(R.id.toReportDetailsFragment)
    }
}