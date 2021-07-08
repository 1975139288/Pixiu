package com.poker.pixiu

import com.poker.common.mvvm.ui.BaseActivity
import com.poker.pixiu.databinding.ActivityMainBinding
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by getViewModel(MainViewModel::class.java){

    }

    @Inject
    lateinit var moshi: Moshi

    override fun init() {
        viewModel.getBanner()
//        viewModel.login("18971399106","1234561")
    }
}


