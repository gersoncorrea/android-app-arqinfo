package com.app.feature.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.app.feature.home.databinding.FeatureHomeActivityBinding

class FeatureHomeActivity : AppCompatActivity() {

    private val binding: FeatureHomeActivityBinding by lazy {
        FeatureHomeActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.customToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HOME", "Destroyed")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HOME", "Pause")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
