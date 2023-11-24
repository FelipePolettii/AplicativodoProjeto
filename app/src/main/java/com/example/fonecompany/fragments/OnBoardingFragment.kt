package com.example.fonecompany.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fonecompany.R
import com.example.fonecompany.databinding.FragmentOnBoardingBinding
import com.example.fonecompany.fragments.viewModel.OnBoardingViewModel
import com.example.fonecompany.model.EmployerDTO
import com.example.fonecompany.repository.token.TokenDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class OnBoardingFragment : Fragment() {
    private val tokenDataStore: TokenDataStore by lazy { TokenDataStore() }

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnBoardingViewModel by viewModels()
    private val loading: DialogProgress by lazy { DialogProgress() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = getString(R.string.onbording_username, "Felipe Renó Valle Poletti")
        binding.tvRa.text = getString(R.string.onbording_userRA, "N9025J8")
        binding.btnEmployees.isActivated =
            runBlocking { tokenDataStore.getisadmin(requireContext()).firstOrNull() ?: false }
        userClickListener()
        initObservers()
        setFragmentResultListener("employer"){_, bundle ->
            val employer = bundle.getParcelable<EmployerDTO>("data")
            if (employer != null){
                viewModel.finduser(employer.id)
            }
        }
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) loading.showLoading(parentFragmentManager) else loading.hideLoading()
        }
        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()

            }
        }
        viewModel.meresponse.observe(viewLifecycleOwner){
            it?.let {
                binding.tvName.text = it.name
                binding.tvRa.text = getString(R.string.onbording_userRA, it.cpf)
            }
        }
    }

    private fun userClickListener() {
        binding.btnPaymentPaper.setOnClickListener {
            findNavController().navigate(OnBoardingFragmentDirections.toReportFragment(viewModel.meresponse.value?.id?:""))
        }
        binding.btnEmployees.setOnClickListener {
            findNavController().navigate(R.id.toEmployerFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}