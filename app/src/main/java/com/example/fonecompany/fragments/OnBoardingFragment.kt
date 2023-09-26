package com.example.fonecompany.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fonecompany.R
import com.example.fonecompany.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : Fragment() {

    private var _binding : FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = getString(R.string.onbording_username, "Felipe Renó Valle Poletti")
        binding.tvRa.text = getString(R.string.onbording_userRA, "N9025J8")
        userClickListener()
    }
    private fun userClickListener(){
        binding.btnPaymentPaper.setOnClickListener {  }
        binding.btnEmployees.setOnClickListener {  }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}