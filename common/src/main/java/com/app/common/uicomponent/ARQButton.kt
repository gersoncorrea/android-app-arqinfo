package com.app.common.uicomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.app.common.databinding.ArqDefaultButtonBinding

class ARQButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private val binding = ArqDefaultButtonBinding.inflate(LayoutInflater.from(context), this, true)

    fun setButtonText(title: String) = with(binding) {
        arqMatButton.text = title
    }

    fun onButtonClickListener(listener: () -> Unit) {
        binding.arqMatButton.setOnClickListener { listener.invoke() }
    }
}
