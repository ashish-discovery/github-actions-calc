package com.proxy.githubactions_calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    private lateinit var sut: MainActivityViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var observer: Observer<Int>

    @Before
    fun setUp() {
        sut = MainActivityViewModel()
    }

    @Test
    fun addition() {
        testCoroutineRule.runBlockingTest {

            sut.result.observeForever(observer)
            sut.calculatorModel.value = CalculatorModel(1, 1, 1)
            assertEquals(sut.result.value, 2)
            sut.result.removeObserver(observer)
        }
    }

    @Test
    fun subtraction() {
        testCoroutineRule.runBlockingTest {

            sut.result.observeForever(observer)
            sut.calculatorModel.value = CalculatorModel(1, 1, 2)
            assertEquals(sut.result.value, 0)
            sut.result.removeObserver(observer)
        }
    }

}
