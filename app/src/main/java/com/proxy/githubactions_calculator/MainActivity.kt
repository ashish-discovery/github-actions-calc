package com.proxy.githubactions_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private var number1: EditText? = null
    private var number2: EditText? = null

    private var plus: Button? = null
    private var minus: Button? = null
    private var multiply: Button? = null
    private var divide: Button? = null

    private var textViewResult: TextView? = null

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        number1 = findViewById(R.id.number1)
        number2 = findViewById(R.id.number2)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        multiply = findViewById(R.id.multiply)
        divide = findViewById(R.id.divide)
        textViewResult = findViewById(R.id.textViewResult)
        handleEvents()
        resultObserver()

    }

    private fun resultObserver() {
        viewModel.result.observe(this, {
            textViewResult?.text = "Answer: $it"
        })
    }

    private fun handleEvents() {
        plus?.setOnClickListener {
            calculate(1)
        }

        minus?.setOnClickListener {
            calculate(2)
        }

        multiply?.setOnClickListener {
            calculate(3)
        }

        divide?.setOnClickListener {
            calculate(4)
        }
    }

    private fun calculate(operator: Int) {
        viewModel.calculatorModel.value = CalculatorModel(
            number1 = number1?.text.toString().toInt(),
            number2 = number2?.text.toString().toInt(),
            operator = operator
        )
    }

}
