package com.developnetwork.meshlwahdk.ui.main.barcodescanner

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseBottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bar_code_scanner.*

class BarCodeScannerFragment : BaseBottomSheetDialogFragment() {
    private lateinit var codeScanner: CodeScanner

    override fun getLayout(): Int {
        return R.layout.fragment_bar_code_scanner
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScanner()
    }

    private fun initScanner() {
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
//                handleQRDataLiveData(it.text)
                codeScanner.releaseResources()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            activity.runOnUiThread {
                Toast.makeText(
                    requireContext(), "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


}