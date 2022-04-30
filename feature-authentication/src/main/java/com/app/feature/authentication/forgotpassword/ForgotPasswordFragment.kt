package com.app.feature.authentication.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.core.FeatureRouter
import com.app.feature.authentication.R
import com.app.feature.authentication.databinding.FragmentForgotPasswordBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var progressView: ViewGroup
    private val viewModel by viewModel<ForgotPasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressView = View.inflate(
            activity,
            R.layout.progressbar_layout, null
        ) as ViewGroup

        setListener()
        observeScreenResponse()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getScreenPayload()
    }

    private fun observeScreenResponse() {

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                val v = binding.fragmentForgot.rootView
                val viewGroup = (v as ViewGroup)
                viewGroup.addView(progressView)
            } else {
                val v = binding.fragmentForgot.rootView
                val viewGroup = (v as ViewGroup)
                viewGroup.removeView(progressView)
            }
        }

        viewModel.screenResponse.observe(viewLifecycleOwner) {
            binding.userEmailInputLayout.hint = it.label.title
            binding.button.text = it.button
        }

        viewModel.error.observe(viewLifecycleOwner) {
            println(it.message)
        }
    }

    private fun setListener() {
        binding.button.setOnClickListener { activity?.supportFragmentManager?.popBackStack() }
    }
}
