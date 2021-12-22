package com.developnetwork.meshlwahdk.ui.main.orderprogram

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.dialogs.UploadImageDialog
import es.dmoral.toasty.Toasty
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

handleCheckUseOfProgram()
        handleButtons()
    }

    private fun handleButtons() {
//        scanBTN.setOnClickListener {
//            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
//        }

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
        if (viewModel.isProductRedeemed)
            handleRedeem()
        else
            if (!viewModel.rxPath.isNullOrBlank())
                handleRedeem()
            else
                Toasty.warning(requireContext(), R.string.select_rx, 0).show()
    }

    private fun handleRedeem() {
        viewModel.redeemProgram(args.programID, args.pharmacyID).observe(viewLifecycleOwner, {
            findNavController().navigate(
                OrderProgramFragmentDirections.actionOrderProgramFragmentToOrderSuccessFragment(
                    args.pharmacyName,
                    it.code
                )
            )
        })
    }

    private fun handleCheckUseOfProgram() {
        viewModel.checkProductRedeemed(args.programID).observe(viewLifecycleOwner, {
            if (it)
                uploadRXBTN.visibility = View.GONE
        })

    }

}