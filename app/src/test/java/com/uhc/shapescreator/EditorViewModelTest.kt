package com.uhc.shapescreator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.uhc.shapescreator.editor.EditorViewModel
import com.uhc.shapescreator.editor.ShapeType
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EditorViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EditorViewModel

    private val observerEventsMock: Observer<EditorViewModel.Events> = mock()

    @Before
    fun setupTest() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        viewModel = EditorViewModel()
    }

    @Test
    fun shouldAddSquareShape() {
        // Prepare
        viewModel.events.observeForever(observerEventsMock)

        // Action
        viewModel.onClickSquare()

        // Test
        verify(observerEventsMock).onChanged(EditorViewModel.Events.UPDATE_SHAPE_CANVAS)
        assert(viewModel.modelsList.size == 1)
        assert(viewModel.modelsList.first().shapeType == ShapeType.SQUARE)
    }

    @Test
    fun shouldAddCircleShape() {
        // Prepare
        viewModel.events.observeForever(observerEventsMock)

        // Action
        viewModel.onClickCircle()

        // Test
        verify(observerEventsMock).onChanged(EditorViewModel.Events.UPDATE_SHAPE_CANVAS)
        assert(viewModel.modelsList.size == 1)
        assert(viewModel.modelsList.first().shapeType == ShapeType.CIRCLE)
    }

    @Test
    fun shouldAddTriangleShape() {
        // Prepare
        viewModel.events.observeForever(observerEventsMock)

        // Action
        viewModel.onClickTriangle()

        // Test
        verify(observerEventsMock).onChanged(EditorViewModel.Events.UPDATE_SHAPE_CANVAS)
        assert(viewModel.modelsList.size == 1)
        assert(viewModel.modelsList.first().shapeType == ShapeType.TRIANGLE)
    }

    @Test
    fun shouldAddMultipleShapes() {
        // Prepare
        viewModel.events.observeForever(observerEventsMock)

        // Action
        viewModel.onClickSquare()
        viewModel.onClickCircle()
        viewModel.onClickTriangle()

        // Test
        verify(observerEventsMock, times(3)).onChanged(EditorViewModel.Events.UPDATE_SHAPE_CANVAS)
        assert(viewModel.modelsList.size == 3)
    }

    @Test
    fun shouldAdd3ShapesAndThenRemoveLastShapeFromList() {
        // Prepare
        viewModel.events.observeForever(observerEventsMock)

        // Action
        viewModel.onClickSquare()
        viewModel.onClickCircle()
        viewModel.onClickTriangle()
        viewModel.onClickUndo()

        // Test
        verify(observerEventsMock, times(4)).onChanged(EditorViewModel.Events.UPDATE_SHAPE_CANVAS)
        assert(viewModel.modelsList.size == 2)
    }

    @Test
    fun shouldDoNothingWhenUndoClickOnEmptyList() {
        // Action
        viewModel.onClickUndo()

        // Test
        assert(viewModel.modelsList.isEmpty())
    }

    @Test
    fun shouldGoToStatsWhenOnClickStats() {
        // Prepare
        viewModel.events.observeForever(observerEventsMock)

        // Action
        viewModel.onClickStats()

        // Test
        verify(observerEventsMock).onChanged(EditorViewModel.Events.GO_TO_STATS)
    }
}