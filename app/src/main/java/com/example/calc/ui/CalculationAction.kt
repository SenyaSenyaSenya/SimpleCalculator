package com.example.calc.ui

sealed class CalculationAction {
    data class Number(val number: Long) : CalculationAction()
    object Clear : CalculationAction()
    object Delete : CalculationAction()
    object Decimal : CalculationAction()
    object Calculate : CalculationAction()
    data class Operation(val operation: CalculationOperation) : CalculationAction()
}
