package com.example.fonecompany.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fonecompany.adapter.EmployerAdapter
import com.example.fonecompany.databinding.FragmentEmployerBinding
import com.example.fonecompany.fragments.viewModel.EmployerViewModel
import com.example.fonecompany.model.EmployerDTO
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.divider.MaterialDividerItemDecoration

class EmployerFragment : Fragment() {

    private var _binding: FragmentEmployerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EmployerViewModel by viewModels()
    private val employerAdapter: EmployerAdapter by lazy { EmployerAdapter(::handler) }
    private val loading: DialogProgress by lazy { DialogProgress() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.apply {

            val divider =
                MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(divider)
            adapter = employerAdapter
        }
        binding.mt.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        initObservers()
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner){
            if (it) loading.showLoading(parentFragmentManager)else  loading.hideLoading()
        }
        viewModel.error.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.userresponse.observe(viewLifecycleOwner){
            it?.let {
                employerAdapter.submitList(it)
            }
        }
    }
    private fun handler(employerDTO: EmployerDTO){
        setFragmentResult("employer", bundleOf("data" to employerDTO))
        findNavController().popBackStack()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}