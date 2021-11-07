package com.developnetwork.meshlwahdk.ui.main.orderprogram

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.dialogs.UploadImageDialog
import com.developnetwork.meshlwahdk.ui.main.barcodescanner.BarCodeScannerFragment
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_order_program.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrderProgramFragment : BaseFragment() {
    private val viewModel: OrderProgramsViewModel by viewModel()
    private val args: OrderProgramFragmentArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.fragment_order_program
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleProgress(viewModel)
        handleError(viewModel)

        handleButtons()
    }

    private fun handleButtons() {
        scanBTN.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        uploadRXBTN.setOnClickListener {
            UploadImageDialog("upload Rx") {
                viewModel.rxPath = it
            }.show(childFragmentManager, "rx")
        }

        uploadReceiptBTN.setOnClickListener {
            UploadImageDialog("Upload receipt") {
                viewModel.receiptPath = it
            }.show(childFragmentManager, "recepit")
        }

        continueBTN.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        if (!viewModel.receiptPath.isNullOrBlank() && !viewModel.rxPath.isNullOrBlank())
            handleRedeem()
    }

    private fun handleRedeem() {
        viewModel.redeemProgram(args.programID, args.pharmacyID).observe(viewLifecycleOwner, {
            findNavController().navigate(
                OrderProgramFragmentDirections.actionOrderProgramFragmentToOrderSuccessFragment(
                    args.pharmacyName
                )
            )
        })
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                BarCodeScannerFragment().show(childFragmentManager, "")
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

}