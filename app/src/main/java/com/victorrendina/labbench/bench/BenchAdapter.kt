package com.victorrendina.labbench.bench

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victorrendina.labbench.databinding.ListItemExperimentBinding

class BenchAdapter(
    private val listener: OnExperimentClickListener
) : RecyclerView.Adapter<ExperimentViewHolder>() {

    private var data: List<ExperimentItem> = emptyList()

    fun updateData(data: List<ExperimentItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperimentViewHolder {
        return ExperimentViewHolder(
            ListItemExperimentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ExperimentViewHolder, position: Int) {
        holder.bind(data[position])
    }

    interface OnExperimentClickListener {
        fun onExperimentClicked(experimentItem: ExperimentItem)
    }
}
