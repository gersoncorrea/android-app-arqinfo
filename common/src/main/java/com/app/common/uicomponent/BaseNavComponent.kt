package com.app.common.uicomponent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.app.common.R

class BaseNavComponent(private val currentActivity: Activity) {
    private val baseCustomToolbar: Toolbar? = currentActivity.findViewById(R.id.custom_toolbar)
    private val appCompatActivity: AppCompatActivity = currentActivity as AppCompatActivity
    fun setNavigationStatus(isBoolean: Boolean) {
        baseCustomToolbar?.apply {
            appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    fun setTitle(title: String) {
        baseCustomToolbar?.title = title
    }
}
