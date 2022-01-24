package com.eneskkoc.android.ui.main

import com.eneskkoc.android.R
import com.eneskkoc.android.base.BaseActivity
import com.eneskkoc.android.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val clazz: Class<MainViewModel> = MainViewModel::class.java
    override val layoutResourceId: Int = R.layout.activity_main

}