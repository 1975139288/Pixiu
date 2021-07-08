package com.poker.common.widget

import android.content.Context
import com.lxj.xpopup.core.CenterPopupView
import com.poker.common.R

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/6 16:16
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class SimpleLoadingDialog(context: Context) : CenterPopupView(context) {
    override fun getImplLayoutId(): Int {
        return R.layout.dialog_loading
    }

}