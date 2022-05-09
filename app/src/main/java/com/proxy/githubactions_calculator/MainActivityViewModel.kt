package com.proxy.githubactions_calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var calculatorModel = MutableLiveData<CalculatorModel>()
    var result: LiveData<Int> = Transformations.switchMap(calculatorModel) { calculatorModel ->
        calculate(calculatorModel)
    }

    private fun calculate(calculatorModel: CalculatorModel): LiveData<Int> {

        val result = MutableLiveData<Int>()

        when (calculatorModel.operator) {
            1 -> {
                result.value = calculatorModel.number1 + calculatorModel.number2
            }
            2 -> {
                result.value = calculatorModel.number1 - calculatorModel.number2
            }
            3 -> {
                result.value = calculatorModel.number1 * calculatorModel.number2
            }
            4 -> {
                result.value = calculatorModel.number1 / calculatorModel.number2
            }
        }

        return result

    }


}