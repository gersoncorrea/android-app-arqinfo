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
import com.app.feature.authentication.remote.LoginBottom
import com.app.feature.authentication.remote.LoginHeader
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.replace
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var binding: FragmentLoginBinding
    private val featureRouter: FeatureRouter by inject()
    private val viewModel by viewModel<LoginViewModel> {
        parametersOf(this)
    }

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
                addToBackStack(null)
            }
        }

        observeLoginResponse()
        observeError()
    }

    private fun observeError() {
        viewModel.error.observe(viewLifecycleOwner) {
            println(it.message)
        }
    }

    private fun observeLoginResponse() {
        viewModel.login.observe(viewLifecycleOwner) {
            bindForgotPassword(it.forgotPassword)
            bindBottom(it.bottom)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun bindHeader(header: LoginHeader) {
    }

    override fun bindBottom(loginBottom: LoginBottom) {
        binding.button.text = loginBottom.button
        binding.signUpTextView.text = loginBottom.signup
    }

    override fun bindForgotPassword(forgotPassword: String) {
        binding.forgotPasswordTextView.text = forgotPassword
    }
}
