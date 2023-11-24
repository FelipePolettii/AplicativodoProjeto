package com.example.fonecompany.fragments

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.fonecompany.R

class DialogProgress: DialogFragment(R.layout.fragment_dialog) {
}
fun DialogProgress.showLoading(parentManager: FragmentManager){
    show(parentManager,"")
}
fun DialogProgress.hideLoading(){
    if (isVisible) dismiss()
}