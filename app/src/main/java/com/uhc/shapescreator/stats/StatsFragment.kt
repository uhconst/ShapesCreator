package com.uhc.shapescreator.stats

import android.os.Bundle
import android.view.View
import com.uhc.shapescreator.R
import com.uhc.shapescreator.databinding.StatsFragmentBinding
import com.uhc.shapescreator.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class StatsFragment : BaseFragment<StatsFragmentBinding>() {

    val viewModel: StatsViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.stats_fragment

    private val statsAdapter by inject<StatsAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        setupStatsList()
    }

    private fun setupStatsList() {
        binding.rvStats.adapter = statsAdapter
    }
}