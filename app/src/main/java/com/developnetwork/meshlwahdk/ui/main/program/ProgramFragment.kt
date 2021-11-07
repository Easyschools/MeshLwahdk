package com.developnetwork.meshlwahdk.ui.main.program

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import kotlinx.android.synthetic.main.fragment_program.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProgramFragment : BaseFragment() {
    private val viewModel: ProgramViewModel by viewModel()
    private val args: ProgramFragmentArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.fragment_program
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleError(viewModel)
        handleProgress(viewModel)

        handleProgramData()
        nextBTN.setOnClickListener {
            if (args.pharmacyID == 0)
                findNavController().navigate(
                    ProgramFragmentDirections.actionProgramFragmentToSelectOrderPharmacyFragment(
                        args.id
                    )
                )
            else
                findNavController().navigate(
                    ProgramFragmentDirections.actionProgramFragmentToOrderProgramFragment(
                        args.id,
                        args.pharmacyID,
                        args.pharmacyName
                    )
                )
        }
    }

    private fun handleProgramData() {
        if (args.id == 0) {
            showError(R.string.this_pharmacy_doesnt_have_programs)
            findNavController().navigateUp()
            return
        }
        viewModel.getProgram(args.id).observe(viewLifecycleOwner, {

            it?.product?.let { product ->
                if (!product.logo.isNullOrBlank())
                    productLogo.setImageURL(product.logo)

                msg.text = product.name
            }
            fields.text = it?.fields

        })
    }
}