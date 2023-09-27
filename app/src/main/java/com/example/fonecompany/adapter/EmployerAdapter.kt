package com.example.fonecompany.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fonecompany.databinding.ItemEmployerBinding
import com.example.fonecompany.model.EmployerDTO
import com.example.fonecompany.utils.ItemCallBack

class EmployerAdapter : ListAdapter<EmployerDTO, EmployerAdapter.BaseViewHolder>(ItemCallBack()) {
    abstract class BaseViewHolder(val binding: ItemEmployerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: EmployerDTO)
    }

    inner class EmployerViewHolder(binding: ItemEmployerBinding) : BaseViewHolder(binding) {
        override fun bind(item: EmployerDTO) {
            binding.apply {
                tvName.text = item.name
                tvRa.text = item.ra
                tvEmail.text = item.email
                tvOffice.text = item.office
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return EmployerViewHolder(
            binding = ItemEmployerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
