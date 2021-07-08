package com.poker.common.mvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/6 14:52
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class BaseFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity is BaseActivity<*>){
            (activity as BaseActivity<*>).showLoading()
        }

    }
}