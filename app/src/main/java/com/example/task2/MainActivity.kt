package com.example.bmicalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.task2.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)


        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if(validateInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                // to get results in two decimal digit
                val bmiAvg = String.format("%.2f", bmi).toFloat()
                display(bmiAvg)
            }
        }

    }

    private fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }

    }
    private fun display(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvResult)
        val status = findViewById<TextView>(R.id.tvStatus)

        resultIndex.text = bmi.toString()

        var resultText = ""
        var color = 0

        when{
            bmi<18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 ->{
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi>29.99 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultIndex.setTextColor(ContextCompat.getColor(this,color))
        status.text = resultText

    }
}