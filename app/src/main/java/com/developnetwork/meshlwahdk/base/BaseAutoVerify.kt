package com.developnetwork.meshlwahdk.base


import android.app.Activity
import android.content.*
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import java.util.regex.Pattern

class BaseAutoVerify(
    private val requestActivityForResult: (consentIntent: Intent?, requestCode: Int) -> Unit,
    private val setCode: (code: String) -> Unit
) {
    private val SMS_CONSENT_REQUEST = 2  // Set to an unused request code

    fun listenToSms(context: Context, activity: FragmentActivity) {
        val task = SmsRetriever.getClient(context).startSmsUserConsent(null /* or null */)
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        activity.registerReceiver(smsVerificationReceiver, intentFilter)
    }

    private val smsVerificationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
                val extras = intent.extras
                val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

                when (smsRetrieverStatus.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        // Get consent intent
                        val consentIntent =
                            extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                        try {
                            // Start activity to show consent dialog to user, activity must be started in
                            // 5 minutes, otherwise you'll receive another TIMEOUT intent
//                            startActivityForResult(consentIntent, SMS_CONSENT_REQUEST)
                            requestActivityForResult(consentIntent, SMS_CONSENT_REQUEST)
                        } catch (e: ActivityNotFoundException) {
                            // Handle the exception ...
                        }
                    }
                    CommonStatusCodes.TIMEOUT -> {
                        // Time out occurred, handle the error.
                    }
                }
            }
        }
    }

    fun onVerifyResultHandler(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            // ...
            SMS_CONSENT_REQUEST ->
                // Obtain the phone number from the result
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Get SMS message content
                    val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                    // 2891رمز التاكيد
                    // Extract one-time code from the message and complete verification
                    // `message` contains the entire text of the SMS message, so you will need
                    // to parse the string.
                    message?.let {
                        setCode(parseOneTimeCode(message))
                    }
                    // send one time code to the server
                } else {
                    // Consent denied. User can type OTC manually.
                }
        }
    }

    private fun parseOneTimeCode(msg: String): String {
//        val text = "رمز التاكيد "
//        var code = msg.removeSuffix(text)
//        code = msg.removePrefix(text)

        var code: String = ""
        val p = Pattern.compile("\\d+");
        val m = p.matcher(msg);
        while (m.find()) {
            code += m.group()
        }

        return code
    }

}