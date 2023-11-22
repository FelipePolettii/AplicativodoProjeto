package com.example.fonecompany.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fonecompany.R
import com.example.fonecompany.databinding.FragmentLoginBinding
import com.example.fonecompany.fragments.viewModel.LoginViewModel
import com.example.fonecompany.repository.token.TokenDataStore
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.runBlocking

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private val dialogProgress: DialogProgress by lazy { DialogProgress() }
    private val tokenDataStore: TokenDataStore by lazy { TokenDataStore() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.login(edtDocument.text.toString(), edtPassword.text.toString())
            }
        }
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it == true) dialogProgress.show(
                parentFragmentManager,
                ""
            ) else if (dialogProgress.isVisible) dialogProgress.dismiss()
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                Snackbar.make(requireView(), "Ocorreu um Erro", Snackbar.LENGTH_LONG).show()
                it.printStackTrace()
            }
        }
        viewModel.loginRes.observe(viewLifecycleOwner) {
            it?.let {
                runBlocking { tokenDataStore.savetoken(requireContext(), it) }
                findNavController().navigate(R.id.toOnBoardingFragment)
            }
        }
        viewModel.skipLogin.observe(viewLifecycleOwner){
            if (it) findNavController().navigate(R.id.toOnBoardingFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}