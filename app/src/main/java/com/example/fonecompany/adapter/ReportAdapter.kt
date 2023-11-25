package com.example.fonecompany.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fonecompany.databinding.ItemReportBinding
import com.example.fonecompany.model.ReportDetailResDTO
import com.example.fonecompany.model.ReportResDTO
import com.example.fonecompany.utils.ItemCallBack
import java.util.Date

class ReportAdapter(val onItemClick: (ReportResDTO) -> Unit) :
    ListAdapter<ReportResDTO, ReportAdapter.BaseViewHolder>(ItemCallBack()) {
    abstract class BaseViewHolder(val binding: ItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: ReportResDTO)
    }

    inner class ReportViewHolder(binding: ItemReportBinding) :
        ReportAdapter.BaseViewHolder(binding) {
        override fun bind(item: ReportResDTO) {
            binding.btn.text = Date(item.creationDate).toLocaleString()
            binding.btn.setOnClickListener { onItemClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ReportAdapter.BaseViewHolder {
        return ReportViewHolder(
            binding = ItemReportBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ReportAdapter.BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}