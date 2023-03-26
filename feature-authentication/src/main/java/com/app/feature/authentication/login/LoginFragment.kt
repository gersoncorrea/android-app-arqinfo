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
import com.app.feature.authentication.domain.LoginBottom
import com.app.feature.authentication.domain.LoginHeader
import com.app.feature.authentication.forgotpassword.ForgotPasswordFragment
import com.app.feature.authentication.signup.SignUpFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.replace
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var progressView: ViewGroup
    private val featureRouter: FeatureRouter by inject()
    private val viewModel by viewModel<LoginViewModel>()
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

        progressView = View.inflate(
            activity,
            com.app.common.R.layout.progressbar_layout,
            null
        ) as ViewGroup

        binding.button.onButtonClickListener {
            featureRouter.start(requireActivity(), HOME_ACTION)
        }

        binding.forgotPasswordTextView.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace<ForgotPasswordFragment>(R.id.frame_view)
                addToBackStack(null)
            }
        }

        binding.signUpTextView.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace<SignUpFragment>(R.id.frame_view)
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
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                val v = binding.fragmentLogin.rootView
                val viewGroup = (v as ViewGroup)
                viewGroup.addView(progressView)
            } else {
                val v = binding.fragmentLogin.rootView
                val viewGroup = (v as ViewGroup)
                viewGroup.removeView(progressView)
            }
        }

        viewModel.login.observe(viewLifecycleOwner) {
            bindHeader(it.header)
            bindForgotPassword(it.forgotPassword)
            bindBottom(it.bottom)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun bindHeader(header: LoginHeader) {
        binding.userEmailInputLayout.hint = header.labelUserInput.title
        binding.userPasswordInputLayout.hint = header.labelPasswordInput.title
    }

    override fun bindBottom(loginBottom: LoginBottom) {
        binding.button.setButtonText(loginBottom.button)
        binding.signUpTextView.text = loginBottom.signup
    }

    override fun bindForgotPassword(forgotPassword: String) {
        binding.forgotPasswordTextView.text = forgotPassword
    }
}
