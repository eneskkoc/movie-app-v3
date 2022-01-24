package com.eneskkoc.android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eneskkoc.android.ui.fragment.DetailViewModel
import com.eneskkoc.android.ui.fragment.MainFragmentViewModel
import com.eneskkoc.android.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @PerApplication
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel:MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindMainFragmentViewModel(viewModel: DetailViewModel): ViewModel

}