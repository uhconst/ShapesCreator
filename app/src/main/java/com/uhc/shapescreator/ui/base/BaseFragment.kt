package com.uhc.shapescreator.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.uhc.shapescreator.MainActivity
import com.uhc.shapescreator.R
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    protected lateinit var binding: B

    protected val compositeDisposable = CompositeDisposable()

    protected val mainActivity: MainActivity get() = (activity as MainActivity)

    protected val navController: NavController? get() = activity?.findNavController(R.id.navHostMain)

    abstract fun getLayoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)

        /** Enable MutableLiveData to be updated on the UI */
        binding.lifecycleOwner = this

        return binding.root
    }

    protected fun setupToobar(toolbar: Toolbar, enableBackButton: Boolean = true) {
        mainActivity.setupToolbar(toolbar, enableBackButton)
    }

    protected fun onBackPressed() {
        mainActivity.onBackPressed()
    }
}