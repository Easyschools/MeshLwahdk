package com.developnetwork.meshlwahdk.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.ui.auth.AuthActivity
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.android.ext.android.inject

class SplashFragment : Fragment() {
//    private val viewModel: SplashViewModel by viewModel()

    private val sharedPreferencesManager: SharedPreferencesManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            motion_layout.transitionToEnd()
        }, 500)

//        viewModel.getCompany().observe(viewLifecycleOwner,{
//            when {
//                sharedPreferencesManager.isLoggedIn -> {
//                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainActivity())
//                    requireActivity().finishAffinity()
//                }
//                !sharedPreferencesManager.choseLanguage -> findNavController().navigate(
//                    SplashFragmentDirections.actionSplashFragmentToChooseLanguageFragment()
//                )
//                sharedPreferencesManager.termsAgreed -> {
//                    val authIntent = Intent(requireActivity(), AuthActivity::class.java)
//                    startActivity(authIntent)
//                    requireActivity().finishAffinity()
//                }
//                else -> findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToIntroFragment())
//            }
//
//        })

        motion_layout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            when {
                sharedPreferencesManager.isLoggedIn -> {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainActivity())
                    requireActivity().finishAffinity()
                }
                !sharedPreferencesManager.choseLanguage -> findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToChooseLanguageFragment()
                )
                sharedPreferencesManager.termsAgreed -> {
                    val authIntent = Intent(requireActivity(), AuthActivity::class.java)
                    startActivity(authIntent)
                    requireActivity().finishAffinity()
                }
                else -> findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToIntroFragment())
            }
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                //TODO("Not yet implemented")
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                //TODO("Not yet implemented")
            }
        })

    }

}