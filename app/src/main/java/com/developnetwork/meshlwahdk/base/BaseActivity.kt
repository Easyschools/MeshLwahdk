package com.developnetwork.meshlwahdk.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.developnetwork.meshlwahdk.utils.managers.LocaleManager
import com.developnetwork.meshlwahdk.utils.view.ProgressDialog
import es.dmoral.toasty.Toasty

import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {
    private var loadingDialog: ProgressDialog? = null
    private val localeManager: LocaleManager by inject()

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        loadingDialog = ProgressDialog(this)
        loadingDialog?.setOnCancelListener {
//            findNavController().navigateUp()
            try {
                onBackPressed()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        init()
    }

    open fun showLoading() {
        loadingDialog?.let {
            it.show()
        }
    }

    open fun hideLoading() {
        loadingDialog?.let { it.dismiss() }
    }

    open fun showErrorToast(@StringRes message: Int) {
        Toasty.error(this, message, 0).show()
    }

    open fun showErrorToast(message: String) {
        Toasty.error(this, message, 0).show()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(
            LocaleManager.updateResources(
                base,
                localeManager.language
            )
        );
    }


    override fun onPause() {
        super.onPause()
        if (loadingDialog != null && loadingDialog?.isShowing!!) {
            loadingDialog?.dismiss()
        }
    }

    override fun onStop() {
        super.onStop()
        loadingDialog = null
    }
}