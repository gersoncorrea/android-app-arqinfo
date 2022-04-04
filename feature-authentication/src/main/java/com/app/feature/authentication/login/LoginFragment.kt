package com.app.feature.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.app.core.FeatureRouter
import com.app.core.actions.FeatureActions.HOME_ACTION
import com.app.feature.authentication.R
import com.app.feature.authentication.databinding.FragmentLoginBinding
import com.app.feature.authentication.forgotpassword.ForgotPasswordFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.replace

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val featureRouter: FeatureRouter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            featureRouter.start(requireActivity(), HOME_ACTION)
        }

        binding.forgotPasswordTextView.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                replace<ForgotPasswordFragment>(R.id.frame_view)
            }
        }
    }

}