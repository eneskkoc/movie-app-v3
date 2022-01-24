package com.eneskkoc.android.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, T : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var binding: T
    lateinit var viewModel: VM
    abstract val clazz: Class<VM>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this, viewModelFactory).get(clazz)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResourceId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }
    open fun init() {}
}