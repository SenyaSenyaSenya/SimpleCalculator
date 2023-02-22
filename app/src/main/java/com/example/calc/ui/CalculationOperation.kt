package com.example.calc.ui

sealed class CalculationOperation(val symbol: String){
    object Add: CalculationOperation("+")
    object Subtract: CalculationOperation("-")
    object Multiply: CalculationOperation("×")
    object Divide: CalculationOperation("/")
    object Percent: CalculationOperation("%")
}
