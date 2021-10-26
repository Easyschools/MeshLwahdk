package com.developnetwork.meshlwahdk.ui.dialogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.ui.main.MainActivity
import com.developnetwork.meshlwahdk.utils.managers.LocaleManager
import kotlinx.android.synthetic.main.dialog_fragment_change_lang.*
import org.koin.android.ext.android.inject

class ChangeLanguageDialog : DialogFragment() {
    private val localeManager:LocaleManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_change_lang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (localeManager.isEnglish)
            english.isChecked = true
        else
            arabic.isChecked = true

        lang_radio.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.arabic -> localeManager.setNewLocale(
                    requireContext(),
                    LocaleManager.LANGUAGE_Arabic
                )

                R.id.english -> localeManager.setNewLocale(
                    requireContext(),
                    LocaleManager.LANGUAGE_ENGLISH
                )
            }
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}