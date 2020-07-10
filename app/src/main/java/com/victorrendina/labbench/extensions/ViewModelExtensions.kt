package com.victorrendina.labbench.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.victorrendina.labbench.LabViewModel
import kotlin.reflect.KClass

inline fun <reified VM : LabViewModel<*, *>> Fragment.viewModel(
    viewModelClass: KClass<VM> = VM::class,
    crossinline keyFactory: () -> String = { viewModelClass.java.name },
    crossinline factory: (savedState: Bundle?) -> VM
) = LifecycleLazy(this) {
    val key = keyFactory()
    val savedState = savedStateRegistry.consumeRestoredStateForKey(key)
    val wrapper = FactoryWrapper { factory(savedState) }

    val viewModel = ViewModelProvider(this, wrapper).get(key, viewModelClass.java)
    savedStateRegistry.registerSavedStateProvider(key, viewModel)

    viewModel
}

class FactoryWrapper<VM>(private val factory: () -> VM) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return factory.invoke() as T
    }
}
