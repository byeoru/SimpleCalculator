package com.byeoru.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.byeoru.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val firstNumberText = StringBuilder("")
    private val secondNumberText = StringBuilder("")
    private var operatorText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addListenerToNumberPad()
        addListenerToOperator()
    }

    private fun addListenerToNumberPad() {
        binding.button1.setOnClickListener { stackNumber("1") }
        binding.button2.setOnClickListener { stackNumber("2") }
        binding.button3.setOnClickListener { stackNumber("3") }
        binding.button4.setOnClickListener { stackNumber("4") }
        binding.button5.setOnClickListener { stackNumber("5") }
        binding.button6.setOnClickListener { stackNumber("6") }
        binding.button7.setOnClickListener { stackNumber("7") }
        binding.button8.setOnClickListener { stackNumber("8") }
        binding.button9.setOnClickListener { stackNumber("9") }
        binding.button0.setOnClickListener { stackNumber("0") }
    }

    private fun addListenerToOperator() {
        binding.buttonClear.setOnClickListener { clear() }
        binding.buttonPlus.setOnClickListener { stackOperator("+") }
        binding.buttonMinus.setOnClickListener { stackOperator("-") }
        binding.buttonEqual.setOnClickListener { operate() }
    }

    private fun stackNumber(numberString: String) {
        if(secondNumberText.isEmpty() && operatorText.isEmpty()) {
            firstNumberText.append(numberString)
        } else if(operatorText.isEmpty().not()) {
            secondNumberText.append(numberString)
        }
        show()
    }

    private fun stackOperator(operator: String) {
        if(operatorText.isEmpty()) {
            operatorText = operator
        }
        show()
    }

    private fun operate() {
        val result = when(operatorText) {
            "+" -> "${firstNumberText.toString().toInt() + secondNumberText.toString().toInt()}"
            "-" -> "${firstNumberText.toString().toInt() - secondNumberText.toString().toInt()}"
            else -> "error"
        }
        binding.resultTextView.text = result
    }

    private fun clear() {
        firstNumberText.clear()
        secondNumberText.clear()
        operatorText = ""
        show()
        binding.resultTextView.text = "0"
    }

    private fun show() {
        binding.equationTextView.text = "$firstNumberText $operatorText $secondNumberText"
    }
}