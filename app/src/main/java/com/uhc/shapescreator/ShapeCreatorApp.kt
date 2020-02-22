package com.uhc.shapescreator

import android.app.Application
import com.uhc.shapescreator.editor.EditorViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ShapeCreatorApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ShapeCreatorApp)
            modules(
                shapeModule
            )
        }
    }

    private val shapeModule = module {
        /** Main */
        viewModel { MainViewModel() }

        /** Editor */
        viewModel { EditorViewModel() }
    }
}