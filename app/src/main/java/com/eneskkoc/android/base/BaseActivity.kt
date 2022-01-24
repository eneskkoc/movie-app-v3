package com.eneskkoc.android.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, T : ViewDataBinding>: AppCompatActivity(), HasAndroidInjector {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int
    protected lateinit var binding: T

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    lateinit var viewModel: VM
    abstract val clazz: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(clazz)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this

    }
    open fun init() {}
    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }
}