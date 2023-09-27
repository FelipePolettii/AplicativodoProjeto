package com.example.fonecompany.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fonecompany.adapter.EmployerAdapter
import com.example.fonecompany.databinding.FragmentEmployerBinding
import com.example.fonecompany.model.EmployerDTO
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.divider.MaterialDividerItemDecoration

class EmployerFragment : Fragment() {

    private var _binding: FragmentEmployerBinding? = null
    private val binding get() = _binding!!
    private val employerAdapter: EmployerAdapter by lazy { EmployerAdapter() }
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
            employerAdapter.submitList(list())
        }
        binding.mt.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }


    private fun list(): List<EmployerDTO> {
        val list = arrayListOf<EmployerDTO>()
        list.add(
            EmployerDTO(
                name = "Felipe Reno Valle Poletti",
                ra = "N9025J8",
                email = "emailparateste@gmail.com",
                office = "Gerente"
            )
        )
        list.add(
            EmployerDTO(
                name = "eduardo",
                ra = "N9025J8",
                email = "emailparateste123@gmail.com",
                office = "Funcionario"
            )
        )
        return list

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}