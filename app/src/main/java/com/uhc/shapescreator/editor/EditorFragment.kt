package com.uhc.shapescreator.editor

import android.os.Bundle
import android.view.View
import com.uhc.shapescreator.R
import com.uhc.shapescreator.databinding.EditorFragmentBinding
import com.uhc.shapescreator.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class EditorFragment : BaseFragment<EditorFragmentBinding>() {

    val viewModel: EditorViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.editor_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
    }
}