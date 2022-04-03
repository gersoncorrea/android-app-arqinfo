package com.app.feature.authentication.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.core.FeatureRouter
import com.app.core.actions.FeatureActions
import com.app.core.actions.FeatureActions.HOME_ACTION
import com.app.feature.authentication.R
import com.app.feature.authentication.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {

    private lateinit var mLoginFragmentLoginBinding: FragmentLoginBinding
    private val featureRouter: FeatureRouter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mLoginFragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return mLoginFragmentLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoginFragmentLoginBinding.button.setOnClickListener {
//            startActivity(Intent(HOME_ACTION).setPackage(context?.packageName))
            featureRouter.start(requireActivity(), HOME_ACTION)
        }
    }
}