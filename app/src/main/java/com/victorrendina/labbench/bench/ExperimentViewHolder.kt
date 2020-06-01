package com.victorrendina.labbench.bench

import androidx.recyclerview.widget.RecyclerView
import com.victorrendina.labbench.databinding.ListItemExperimentBinding

class ExperimentViewHolder(
    private val binding: ListItemExperimentBinding,
    private val listener: BenchAdapter.OnExperimentClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private var experiment: ExperimentItem? = null

    init {
        binding.root.setOnClickListener {
            experiment?.let {
                listener.onExperimentClicked(it)
            }
        }
    }

    fun bind(item: ExperimentItem) {
        binding.apply {
            title.setText(item.titleRes)
            description.setText(item.descriptionRes)
            icon.setImageResource(item.iconRes)
        }
        experiment = item
    }
}
