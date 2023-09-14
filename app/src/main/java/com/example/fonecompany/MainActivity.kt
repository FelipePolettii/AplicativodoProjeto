package com.example.fonecompany

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.fonecompany.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}