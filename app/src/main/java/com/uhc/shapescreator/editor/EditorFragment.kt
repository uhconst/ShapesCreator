package com.uhc.shapescreator.editor

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.uhc.shapescreator.R
import com.uhc.shapescreator.databinding.EditorFragmentBinding
import com.uhc.shapescreator.stats.toStatsModel
import com.uhc.shapescreator.ui.base.BaseFragment
import kotlinx.android.synthetic.main.editor_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class EditorFragment : BaseFragment<EditorFragmentBinding>() {

    val viewModel: EditorViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.editor_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

//        viewModel.setWidthAndHeight(shapes_fl.width, shapes_fl.height)

        viewModel.events.observe(this, Observer { event ->
            when (event) {
                EditorViewModel.Events.UPDATE_SHAPE_CANVAS -> shape_view.update(viewModel.modelsList)
                EditorViewModel.Events.GO_TO_STATS ->
                    navController?.navigate(
                        EditorFragmentDirections.actionShowStatsFragment(
                            viewModel.modelsList.toStatsModel()
                        )
                    )
            }
        })
    }

}