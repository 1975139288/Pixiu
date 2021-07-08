package com.poker.common.mvvm.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ToastUtils
import com.leaf.library.StatusBarUtil
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.XPopupCallback
import com.poker.common.mvvm.IUIActionEventObserver
import com.poker.common.widget.SimpleLoadingDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import java.lang.reflect.ParameterizedType

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/6 14:52
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), IUIActionEventObserver {

    override val mContext: Context
        get() = this

    override val lifecycleSupportedScope: CoroutineScope
        get() = lifecycleScope

    override val mLifecycleOwner: LifecycleOwner
        get() = this

    protected lateinit var binding: VB

    private lateinit var job: Job
    private val loadingDialog: BasePopupView by lazy {
        XPopup.Builder(mContext)
            //点击外部关闭loading
            .dismissOnTouchOutside(false)
            //返回键关闭loading
            .dismissOnBackPressed(true)
            .setPopupCallback(object : XPopupCallback {
                override fun onCreated(popupView: BasePopupView?) {
                }

                override fun beforeShow(popupView: BasePopupView?) {
                }

                override fun onShow(popupView: BasePopupView?) {
                }

                override fun onDismiss(popupView: BasePopupView?) {
                    if (this@BaseActivity::job.isInitialized) {
                        job.takeIf { job -> job.isCompleted }?.cancel().run {
                            showToast("已取消")
                        }

//                        if (job.isCompleted) {
//                            return
//                        } else {
//                            job.cancel()
//                            showToast("已取消")
//                        }
                    }
                }

                override fun beforeDismiss(popupView: BasePopupView?) {
                }

                override fun onBackPressed(popupView: BasePopupView?): Boolean {
                    return false
                }

                override fun onKeyBoardStateChanged(popupView: BasePopupView?, height: Int) {
                }

                override fun onDrag(
                    popupView: BasePopupView?,
                    value: Int,
                    percent: Float,
                    upOrLeft: Boolean
                ) {
                }
            })
            .asCustom(SimpleLoadingDialog(mContext))
    }


    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz0 = type.actualTypeArguments[0] as Class<VB>
        val method = clazz0.getMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, layoutInflater) as VB
        setContentView(binding.root)
        StatusBarUtil.setColor(this, Color.WHITE)
        StatusBarUtil.setDarkMode(this)

        init()
    }

    abstract fun init()

    override fun showLoading() {
        loadingDialog.takeIf { it.isDismiss }?.show()
    }

    override fun showLoading(job: Job?) {
        if (job != null) {
            this.job = job
        }
        showLoading()
    }

    override fun dismissLoading() {
        loadingDialog.takeIf { it.isShow }?.dismiss()
    }

    override fun showToast(msg: String) {
        ToastUtils.showLong(msg)
    }


}