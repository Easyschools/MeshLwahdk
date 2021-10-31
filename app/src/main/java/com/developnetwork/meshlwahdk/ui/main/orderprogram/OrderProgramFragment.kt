package com.developnetwork.meshlwahdk.ui.main.orderprogram

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.dialogs.UploadImageDialog
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
        scanBTN.setOnClickListener { }

        uploadRXBTN.setOnClickListener {
            UploadImageDialog("upload Rx") {
                viewModel.rxPath = it
            }.show(childFragmentManager, "rx")
        }

        uploadReceiptBTN.setOnClickListener {
            UploadImageDialog("Upload receipt") {
                viewModel.receiptPath = it
            }.show(childFragmentManager, "recepit ")
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
}