package com.victorrendina.labbench.experiments.counter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import com.victorrendina.core.Logger
import com.victorrendina.labbench.*
import com.victorrendina.labbench.databinding.FragmentCounterBinding
import com.victorrendina.labbench.extensions.viewBinding
import com.victorrendina.labbench.extensions.viewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CounterFragment : LabFragment() {

    @Inject
    lateinit var viewModelFactory: CounterViewModel.Factory

    @Inject
    lateinit var log: Logger

    private val binding by viewBinding(FragmentCounterBinding::bind)

    private val viewModel: CounterViewModel by viewModel {
        viewModelFactory.create(CounterViewState.create(it, 0))
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as LabApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCounterBinding.inflate(inflater, container, false).root
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

        viewModel.messages.collectLifecycle {
            log.d("Received message $it")
        }

        viewModel.state.collectLifecycle { state ->
            log.d("Received state update: $state")

            binding.content.isGone = state.loading
            binding.count.text = state.count.toString()
            binding.restored.text = if (state.restored) getString(R.string.restored) else ""
        }

//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewModel.state.collect { state ->
//                log.d("Received state update: $state")
//
//                binding.content.isGone = state.loading
//                binding.count.text = state.count.toString()
//                binding.restored.text = if (state.restored) getString(R.string.restored) else ""
//            }
//        }

        viewModel.state.map { it.loading }.distinctUntilChanged().collectLifecycle { loading ->
            log.d("Received loading update")
            if (loading) binding.progressBar.show() else binding.progressBar.hide()
        }

//        viewModel.state
//            .onEach { state ->
//                log.d("Received state update: $state")
//
//                binding.content.isGone = state.loading
//                binding.count.text = state.count.toString()
//                binding.restored.text = if (state.restored) getString(R.string.restored) else ""
//            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


    companion object {
        private val TAG = CounterFragment::class.java.simpleName
    }
}
