package com.example.fonecompany.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.fonecompany.R
import com.example.fonecompany.databinding.FragmentOnBoardingBinding
import com.example.fonecompany.databinding.FragmentReportBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.util.Calendar

class ReportFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

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
}