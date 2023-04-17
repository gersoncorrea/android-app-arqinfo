package com.app.feature.authentication.signup

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.app.core.FeatureRouter
import com.app.core.actions.FeatureActions.AUTH_ACTION
import com.app.feature.authentication.FeatureAuthenticationActivity
import com.app.feature.authentication.R
import com.app.feature.authentication.databinding.FragmentSignupBinding
import com.app.feature.authentication.domain.LabelsDefault
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private val binding: FragmentSignupBinding by lazy {
        FragmentSignupBinding.inflate(layoutInflater)
    }
    private lateinit var progressView: ViewGroup
    private val featureRouter: FeatureRouter by inject()
    private var lastId = -1
    private val viewModel by viewModel<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (activity as FeatureAuthenticationActivity).supportActionBar?.title = "Cadastrar"
        (activity as FeatureAuthenticationActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as FeatureAuthenticationActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressView = View.inflate(
            activity,
            R.layout.progressbar_layout,
            null,
        ) as ViewGroup

        bindListener()
        observeRequestResponse()
        observeError()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getSignUpScreen()
    }

    private fun observeError() = with(viewModel) {
        error.observe(viewLifecycleOwner) {
            println(it.message)
        }
    }

    private fun observeRequestResponse() {
        val v = binding.layoutFragmentSignUp.rootView
        val viewGroup = (v as ViewGroup)
        with(viewModel) {
            loading.observe(viewLifecycleOwner) {
                if (it) {
                    viewGroup.addView(progressView)
                } else {
                    viewGroup.removeView(progressView)
                }
            }
            signup.observe(viewLifecycleOwner) { payloadModel ->
                payloadModel.labels.forEach {
                    addLabelView(it)
                }
                binding.button.text = payloadModel.button
            }
        }
    }

    private fun addLabelView(it: LabelsDefault) = with(binding) {
        val rowView = layoutInflater.inflate(R.layout.item_field, null)
        val rowField = rowView.findViewById<TextInputEditText>(R.id.editTextField)
        val inputLayout = rowView.findViewById<TextInputLayout>(R.id.fieldInputLayout)
        rowView.id = View.generateViewId()
        inputLayout.hint = it.title
        if (it.type == FIELD_TYPES.TEXT_TYPE) {
            rowField.inputType = InputType.TYPE_CLASS_TEXT
        }
        if (it.type == FIELD_TYPES.PASSWORD_TYPE) {
            rowField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        val lp = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
        )
        layoutFragmentSignUp.addView(rowView, lp)

        val cSet = ConstraintSet()
        cSet.clone(layoutFragmentSignUp)
        if (lastId == -1) {
            cSet.connect(
                rowView.id,
                ConstraintSet.TOP,
                imageView.id,
                ConstraintSet.BOTTOM,
                10,
            )
        } else {
            cSet.connect(
                rowView.id,
                ConstraintSet.TOP,
                lastId,
                ConstraintSet.BOTTOM,
                10,
            )
        }
        lastId = rowView.id
        cSet.applyTo(layoutFragmentSignUp)
    }

    private fun bindListener() = with(binding) {
        button.setOnClickListener {
            featureRouter.start(requireActivity(), AUTH_ACTION)
        }
    }

    object FIELD_TYPES {
        const val TEXT_TYPE = "TEXT"
        const val PASSWORD_TYPE = "PASSWORD"
    }
}
