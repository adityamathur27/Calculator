package com.aditya.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import com.aditya.calculator.databinding.ActivityMainBinding
import kotlin.time.Duration.Companion.ZERO

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var inputValue1: Double? = 0.0
    private var inputValue2: Double? = null
    private var currentOperator: Operator? = null
    private var result: Double? = null
    private val equation: StringBuilder = StringBuilder().append(ZERO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }
    private fun setListener(){
        for(button in getNumericButton()){
            button.setOnClickListener {
                onNumberClicked(button.text.toString())
            }
        }
        with(binding){
            zerobutton.setOnClickListener {OnZeroClicke()}
            decimalbutton.setOnClickListener {onDecimalPointClicked()}
            plusbutton.setOnClickListener{onOperatorClicked (Operator.ADDITION) }
            minusbutton.setOnClickListener { onOperatorClicked (Operator.SUBTRACTION) }
            multiplicationButton.setOnClickListener{onOperatorClicked (Operator.MULTIPLICATION) }
            divisionButton.setOnClickListener { onOperatorClicked (Operator.DIVISION) }
            equalbutton.setOnClickListener { onEqualsClicked () }
            allClearButton.setOnClickListener {OnAllClearClicked() }
            percentageButton.setOnClickListener { onPercentageClicked() }
            extendbutton.setOnClickListener { onExtendButtonClicked() }
            Sinbutton.setOnClickListener { onSinClicked() }
            Cosbutton.setOnClickListener { onCosClicked() }
            Tanbutton.setOnClickListener { onTanClicked() }
            logButton.setOnClickListener { onLogClicked() }
            lnButton.setOnClickListener { onLnClicked() }
            squareRootButton.setOnClickListener { onSquareRootClicked() }
            factorial.setOnClickListener { onFactorialClicked() }
            xpowminus1.setOnClickListener { onPowerClicked() }
            piButton.setOnClickListener { onPiClicked() }
            e.setOnClickListener { onEClicked() }

        }
    }
    private fun onExtendButtonClicked(){
        var tableRow : TableRow = findViewById(R.id.firstRow)
        var tableRow1 : TableRow = findViewById(R.id.secondRow)
        var squareRootButton : Button = findViewById(R.id.squareRootButton)
        var factorialButton : Button = findViewById(R.id.factorial)
        var powerButton : Button = findViewById(R.id.xpowminus1)
        var piButton : Button = findViewById(R.id.piButton)
        var eButton : Button = findViewById(R.id.e)
        if (tableRow.visibility == View.VISIBLE) {
            tableRow.visibility = View.GONE
            tableRow1.visibility = View.GONE
            squareRootButton.visibility = View.GONE
            factorialButton.visibility = View.GONE
            powerButton.visibility = View.GONE
            piButton.visibility = View.GONE
            eButton.visibility = View.GONE

        } else {
            tableRow.visibility = View.VISIBLE
            tableRow1.visibility = View.VISIBLE
            squareRootButton.visibility = View.VISIBLE
            factorialButton.visibility = View.VISIBLE
            powerButton.visibility = View.VISIBLE
            piButton.visibility = View.VISIBLE
            eButton.visibility = View.VISIBLE
        }
    }
    private fun onPercentageClicked (){
        if (inputValue2 == null) {
            val percentage = getInputValue1() / 100
            inputValue1  = percentage
            equation.clear().append(percentage)
            updateInputOnDisplay()
        } else {
            val percentage0fValue1 = (getInputValue1() * getInputValue2()) / 100
            val percentage0fValue2 = getInputValue2() / 100
            result  = when (requireNotNull(currentOperator)) {
                Operator.ADDITION -> getInputValue1()+percentage0fValue1
                Operator.SUBTRACTION -> getInputValue1() - percentage0fValue1
                Operator.MULTIPLICATION -> getInputValue1() * percentage0fValue2
                Operator.DIVISION -> getInputValue1() / percentage0fValue2
            }
            equation.clear().append(ZERO)
            updateResultOnDisplay(isPercentage = true)
            inputValue1 = result
            result = null
            inputValue2 = null
            currentOperator = null
        }
    }
    private fun OnAllClearClicked(){
        equation.clear().append(ZERO)
        inputValue1 = 0.0
        inputValue2 = null
        currentOperator = null
        result = null
        clearDisplay()
    }
    private fun OnDeleteClicked(){
        if(equation.length == 1){
            equation.clear().append(ZERO)
        }
        else{
            equation.deleteCharAt(equation.length - 1)
        }
        updateInputOnDisplay()
    }
    private fun onSinClicked(){
        if(inputValue1 == 0.0){
            equation.clear().append("SIN(")
            var num : Double = 0.0
            for(button in getNumericButton()){
                button.setOnClickListener {
                    onNumberClicked(button.text.toString())
                    num = equation.toString().toDouble()

                }
            }
            inputValue1 = Math.sin(num)
            updateInputOnDisplay()
        }
        else{
            val sinValue = Math.sin(getInputValue1())
            equation.clear().append(sinValue)
            inputValue1 = sinValue
            updateInputOnDisplay()
        }
    }
    private fun onCosClicked(){
        val cosValue = Math.cos(getInputValue1())
        equation.clear().append(cosValue)
        inputValue1 = cosValue
        updateInputOnDisplay()
    }
    private fun onTanClicked(){
        val tanValue = Math.tan(getInputValue1())
        equation.clear().append(tanValue)
        inputValue1 = tanValue
        updateInputOnDisplay()
    }
    private fun onLogClicked(){
        val logValue = Math.log10(getInputValue1())
        equation.clear().append(logValue)
        inputValue1 = logValue
        updateInputOnDisplay()
    }
    private fun onLnClicked(){
        val lnValue = Math.log(getInputValue1())
        equation.clear().append(lnValue)
        inputValue1 = lnValue
        updateInputOnDisplay()
    }
    private fun onSquareRootClicked(){
        val squareRootValue = Math.sqrt(getInputValue1())
        equation.clear().append(squareRootValue)
        inputValue1 = squareRootValue
        updateInputOnDisplay()
    }
    private fun onFactorialClicked(){
        var factorialValue = (getInputValue1())
        while(factorialValue > 1){
            factorialValue *= (factorialValue - 1)
        }
        equation.clear().append(factorialValue)
        inputValue1 = factorialValue
        updateInputOnDisplay()
    }
    private fun onPowerClicked(){
        val powerValue = Math.pow(getInputValue1(), -1.0)
        equation.clear().append(powerValue)
        inputValue1 = powerValue
        updateInputOnDisplay()
    }
    private fun onPiClicked(){
        val piValue = Math.PI
        equation.clear().append(piValue)
        inputValue1 = piValue
        updateInputOnDisplay()
    }
    private fun onEClicked(){
        val eValue = Math.E
        equation.clear().append(eValue)
        inputValue1 = eValue
        updateInputOnDisplay()
    }
    private fun onOperatorClicked(operator : Operator){
        onEqualsClicked()
        currentOperator = operator
    }
    private fun onEqualsClicked() {
        if (inputValue2 != null) {
            result = calculate ()
            equation.clear().append(ZERO)
            updateResultOnDisplay()
            inputValue1 = result
            result = null
            inputValue2 = null
            currentOperator = null
        } else {
            equation.clear().append(ZERO)
        }
    }
    private fun calculate(): Double {
        return when (requireNotNull (currentOperator)) {
            Operator. ADDITION -> getInputValue1() + getInputValue2 ()
            Operator.SUBTRACTION -> getInputValue1() - getInputValue2()
            Operator.MULTIPLICATION -> getInputValue1() * getInputValue2 ()
            Operator.DIVISION -> getInputValue1() / getInputValue2()
        }
    }
    private fun onDecimalPointClicked () {
        if (equation.contains (DECIMAL_POINT)) return
        equation.append (DECIMAL_POINT)
        setInput()
        updateInputOnDisplay()
    }
    private fun OnZeroClicke(){
        if(equation.startsWith(ZERO))return
        onNumberClicked(ZERO)
    }
    private fun getNumericButton() = with(binding) {
        arrayOf(
            zerobutton,
            onebutton,
            twobutton,
            threebutton,
            fourbutton,
            fiveButton,
            sixbutton,
            sevenbutton,
            eightButton,
            ninebutton
        )
    }
    private fun onNumberClicked (numberText: String) {
        if (equation.startsWith(ZERO)) {
            equation.deleteCharAt(0)
        }
        equation.append(numberText)
        setInput()
        updateInputOnDisplay()
    }
    private fun setInput() {
        if (currentOperator == null) {
            inputValue1= equation.toString().toDouble()
        } else {
            inputValue2= equation.toString().toDouble()
        }
    }
    private fun clearDisplay() {
        with (binding) {
            textDisplay.text = null
            operation.text= getFormattedDisplayValue(value=getInputValue1())
        }
    }
    private fun updateResultOnDisplay (isPercentage: Boolean = false) {
        binding.operation.text = getFormattedDisplayValue(value = result)
        var input2Text = getFormattedDisplayValue(value=getInputValue2())
        if(isPercentage) input2Text = "$input2Text${getString (R.string.percentage)}"
        binding.textDisplay.text=String.format(
            "%s %s %s",
            getFormattedDisplayValue (value=getInputValue1()),
            getOperatorSymbol(),
            input2Text
        )
    }
    private fun updateInputOnDisplay() {
        if (result == null) {
            binding.operation.text = null
        }
        binding.textDisplay.text = equation
    }
    private fun getInputValue1 (): Double {
        return inputValue1 ?: 0.0
    }
    private fun getInputValue2 (): Double {
        return inputValue2 ?: 0.0
    }
    private fun getOperatorSymbol (): String {
        return when(requireNotNull(currentOperator)) {
            Operator.ADDITION -> getString(R.string.addition)
            Operator.SUBTRACTION -> getString(R.string.subtraction)
            Operator.MULTIPLICATION -> getString(R.string.multiplication)
            Operator.DIVISION -> getString(R.string.division)
        }
    }
    private fun getFormattedDisplayValue (value: Double?): String? {
        val originalValue = value ?: return null
        return if (originalValue % 1 == 0.0) {
            originalValue.toInt ().toString()
        } else {
            originalValue.toString()
        }
    }
    enum class Operator {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
    }
    private companion object {
        const val DECIMAL_POINT = "."
        const val ZERO = "0"
    }
}