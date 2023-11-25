package com.example.fonecompany.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fonecompany.R
import com.example.fonecompany.adapter.ReportAdapter
import com.example.fonecompany.databinding.FragmentReportBinding
import com.example.fonecompany.fragments.viewModel.ReportViewModel
import com.example.fonecompany.model.ReportDetailResDTO
import com.example.fonecompany.model.ReportResDTO
import com.example.fonecompany.repository.token.TokenDataStore
import java.util.Calendar

class ReportFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!
    private val args: ReportFragmentArgs by navArgs()
    private val viewModel: ReportViewModel by viewModels()
    private val loading: DialogProgress by lazy { DialogProgress() }
    private val reportAdapter: ReportAdapter by lazy {
        ReportAdapter(onItemClick = {
            handleReportClick(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.months, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMonth.adapter = adapter


        val adapterYear = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            buildYears(),
        )
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        handlerMonthSelected()
        binding.spinnerYear.adapter = adapterYear
        binding.rv.adapter = reportAdapter

        initobservers()
        binding.spinnerMonth.onItemSelectedListener = this
    }

    private fun initobservers() {
        viewModel.loading.observe(viewLifecycleOwner){
            if (it)loading.showLoading(parentFragmentManager)else  loading.hideLoading()
        }
            viewModel.error.observe(viewLifecycleOwner){
                it?.let {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        viewModel.reportresponse.observe(viewLifecycleOwner){
            it?.let{
                reportAdapter.submitList(it)
            }
        }
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
        viewModel.monthSelected  = position
        viewModel.loadreport(args.userId)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        parent?.setSelection(viewModel.monthSelected)
    }

    private fun handleReportClick(item: ReportResDTO) {
        findNavController().navigate(ReportFragmentDirections.toReportDetailsFragment(item.id))
    }

    private fun handlerMonthSelected(){
        binding.spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.yearSelected = buildYears()[position]
                viewModel.loadreport(args.userId)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                parent?.setSelection(viewModel.yearSelected)
            }

        }
    }
}