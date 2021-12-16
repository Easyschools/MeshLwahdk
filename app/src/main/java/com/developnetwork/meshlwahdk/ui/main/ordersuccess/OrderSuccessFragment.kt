package com.developnetwork.meshlwahdk.ui.main.ordersuccess

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.managers.LocaleManager
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_order_success.*
import org.koin.android.ext.android.inject

class OrderSuccessFragment : BaseFragment() {
    private val args: OrderSuccessFragmentArgs by navArgs()
    private val sharedPreferencesManager: SharedPreferencesManager by inject()
private val localeManager:LocaleManager by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_order_success
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userName.text = "${getString(R.string.hi)} ${sharedPreferencesManager.userName}"

        msg1.text = if(localeManager.isEnglish)
            "${getString(R.string.your_request_has_been_submitted_to)} ${args.pharmacyName} ${getString(R.string.pharmacy)}"
        else
            "${getString(R.string.your_request_has_been_submitted_to)} ${args.pharmacyName}"

        orderCode.text = args.orderCode

        continueBTN.setOnClickListener {

            if (!findNavController().popBackStack(R.id.home, false))
                findNavController().navigate(OrderSuccessFragmentDirections.actionOrderSuccessFragmentToHomeFragment())
        }
    }
}