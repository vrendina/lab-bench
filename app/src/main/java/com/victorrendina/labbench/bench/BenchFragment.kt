package com.victorrendina.labbench.bench

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.victorrendina.labbench.BuildConfig
import com.victorrendina.labbench.LabFragment
import com.victorrendina.labbench.R
import com.victorrendina.labbench.databinding.FragmentBenchBinding

class BenchFragment : LabFragment(), BenchAdapter.OnExperimentClickListener {

    private var _binding: FragmentBenchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBenchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = BenchAdapter(this).apply {
            updateData(buildExperiments())
        }
        binding.version.text = BuildConfig.VERSION_NAME
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onExperimentClicked(experimentItem: ExperimentItem) {
        val nav = findNavController()
        when (experimentItem.id) {
            Experiment.COUNTER -> nav.navigate(R.id.action_benchFragment_to_counterFragment)
            else -> {
            }
        }
    }

    private fun buildExperiments(): List<ExperimentItem> {
        return listOf(
            ExperimentItem(
                Experiment.COUNTER,
                R.drawable.ic_android_24dp,
                R.string.counter_title,
                R.string.counter_description
            )
        )
    }
}
