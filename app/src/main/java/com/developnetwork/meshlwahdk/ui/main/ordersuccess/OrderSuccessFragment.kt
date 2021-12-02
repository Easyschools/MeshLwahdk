package com.developnetwork.meshlwahdk.ui.main.ordersuccess

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_order_success.*
import org.koin.android.ext.android.inject


class OrderSuccessFragment : BaseFragment() {
    private val args: OrderSuccessFragmentArgs by navArgs()
    private val sharedPreferencesManager: SharedPreferencesManager by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_order_success
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userName.text = "${getString(R.string.hi)} ${sharedPreferencesManager.userName}"

        msg1.text = "${getString(R.string.your_order_is_submitted_to)} ${args.pharmacyName}"

        orderCode.text = args.orderCode

        continueBTN.setOnClickListener {

            if (!findNavController().popBackStack(R.id.home, false))
                findNavController().navigate(OrderSuccessFragmentDirections.actionOrderSuccessFragmentToHomeFragment())
        }
    }
}