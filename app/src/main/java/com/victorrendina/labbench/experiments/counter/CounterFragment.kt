package com.victorrendina.labbench.experiments.counter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.victorrendina.core.Logger
import com.victorrendina.labbench.LabApplication
import com.victorrendina.labbench.LabFragment
import com.victorrendina.labbench.R
import com.victorrendina.labbench.databinding.FragmentCounterBinding
import com.victorrendina.labbench.extensions.viewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CounterFragment : LabFragment() {

    @Inject
    lateinit var viewModelFactory: CounterViewModel.Factory

    @Inject
    lateinit var log: Logger

    private val viewModel: CounterViewModel by viewModel {
        viewModelFactory.create(CounterViewState.create(it, 0))
    }

    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!

    private var stateJob: Job? = null

    override fun onAttach(context: Context) {
        (context.applicationContext as LabApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        binding.incrementButton.setOnClickListener {
            viewModel.increment()
        }

        binding.decrementButton.setOnClickListener {
            viewModel.decrement()
        }

        binding.saveButton.setOnClickListener {
            viewModel.save()
        }
    }

    override fun onStart() {
        super.onStart()

        stateJob = viewModel.state
            .onEach { state ->
                log.d("Received state update: $state")

                if (state.loading) binding.progressBar.show() else binding.progressBar.hide()

                binding.content.isGone = state.loading
                binding.count.text = state.count.toString()
                binding.restored.text = if (state.restored) getString(R.string.restored) else ""
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onStop() {
        super.onStop()
        stateJob?.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = CounterFragment::class.java.simpleName
    }
}
