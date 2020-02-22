package com.uhc.shapescreator

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.uhc.shapescreator.databinding.MainActivityBinding
import com.uhc.shapescreator.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainActivityBinding>() {

    val viewModel: MainViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    fun setupToolbar(toolbar: Toolbar, enableBackButton: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableBackButton)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
