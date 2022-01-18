package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    var billAmount = ""
    var percentage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<EditText>(R.id.edit_text).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                billAmount = s?.toString() ?: ""
                updateView()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        findViewById<com.google.android.material.slider.Slider>(R.id.slider).addOnChangeListener {
                _, value, _ -> percentage = value.toInt()
                updateView()
        }
    }
    fun updateView() {
        text_view.text = if (billAmount.isEmpty()) ""
        else "Tip amount: ${calculateTip(billAmount, percentage)}"
    }
    fun calculateTip(billAmount: String, percentage: Int): BigDecimal {
        val bigBill = billAmount.toBigDecimal()
        val bigPercent = percentage.toBigDecimal()
        val hundred = BigDecimal(100.00)
        return (bigBill * bigPercent / hundred).setScale(2, RoundingMode.HALF_UP)
    }
}