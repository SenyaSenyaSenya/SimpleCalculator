package com.example.calc.ui

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val number0: String = "",
    val res: String = "",
    val operation: CalculationOperation? = null
)