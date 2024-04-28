package com.example.tipcal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var tipPercentEditText: EditText
    private lateinit var roundUpSwitch: Switch
    private lateinit var calculateButton: Button
    private lateinit var tipTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.amount_edit_text)
        tipPercentEditText = findViewById(R.id.tip_percent_edit_text)
        roundUpSwitch = findViewById(R.id.round_up_switch)
        calculateButton = findViewById(R.id.calculate_button)
        tipTextView = findViewById(R.id.tip_text_view)

        calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val amount = amountEditText.text.toString().toDouble()
        val tipPercent = tipPercentEditText.text.toString().toDouble()
        val roundUp = roundUpSwitch.isChecked

        val tip = calculateTip(amount, tipPercent, roundUp)
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        tipTextView.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean): Double {
        var tip = tipPercent / 100 * amount
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        return tip
    }
}