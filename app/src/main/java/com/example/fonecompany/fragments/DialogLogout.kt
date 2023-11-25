package com.example.fonecompany.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fonecompany.R
import com.example.fonecompany.databinding.DialogLogoutBinding
import com.example.fonecompany.repository.token.TokenDataStore
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.runBlocking

class DialogLogout : Fragment() {
    private val datastorerepository: TokenDataStore by lazy { TokenDataStore() }
    private var _binding: DialogLogoutBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = DialogLogoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cancel.setOnClickListener {
                findNavController().popBackStack()
            }
            logout.setOnClickListener {
                runBlocking { datastorerepository.logout(requireContext()) }
                findNavController().navigate(DialogLogoutDirections.toLoginNavGraph())
            }
        }
    }
}