package com.example.calc.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculationAction) {
        when (action) {
            is CalculationAction.Number -> {
                enterNumber(action.number)
            }
            is CalculationAction.Decimal -> enterDecimal()
            is CalculationAction.Clear -> {
                state = CalculatorState()
            }
            is CalculationAction.Operation -> {
                enterOperation(action.operation)
            }
            is CalculationAction.Calculate -> performCalculationAll()
            is CalculationAction.Delete -> performDeletion()
        }
    }

//    private fun performCalcPlaceholder() {
//        val number1 = state.number1.toDoubleOrNull()
//        val number2 = state.number2.toDoubleOrNull()
//        if (number1 != null && number2 != null) {
//            val resulting = when (state.operation) {
//                is CalculationOperation.Add -> {
//                    if ((number1 + number2) % 1 == 0.0) (number1 + number2).toLong()
//                    else (number1 + number2).toFloat()
//                }
//                is CalculationOperation.Subtract -> {
//                    if ((number1 - number2) % 1 == 0.0) (number1 - number2).toLong()
//                    else (number1 - number2).toFloat()
//                }
//                is CalculationOperation.Multiply -> {
//                    if ((number1 * number2) % 1 == 0.0) (number1 * number2).toLong()
//                    else (number1 * number2).toFloat()
//                }
//                is CalculationOperation.Divide -> {
//                    if ((number1 / number2) % 1 == 0.0) (number1 / number2).toLong()
//                    else (number1 / number2).toFloat()
//                }
//                is CalculationOperation.Percent -> {
//                    if ((number1 / 100 * number2) % 1 == 0.0) (number1 / 100 * number2).toLong()
//                    else (number1 / 100 * number2).toFloat()
//                }
//                null -> return
//            }
//            state = state.copy(
//                number1 = "",
//                number2 = "",
//                number3 = resulting.toString().take(15),
//                operation = null
//            )
//        }
//        if (number1 != null && number2 == null) {
//            state = state.copy(
//                number1 = "",
//                number2 = "",
//                number3 = state.number1,
//                operation = null
//            )
//        }
//        if (number1 == null && number2 == null) {
//            state = state.copy(
//                number1 = "",
//                number2 = "",
//                number3 = "",
//                operation = null
//            )
//        }
//    }


private fun performDeletion() {
    when {
        state.number2.isNotBlank() -> state = state.copy(
            number2 = state.number2.dropLast(1)
        )
        state.operation != null -> state = state.copy(
            operation = null
        )
        state.number1.isNotBlank() -> state = state.copy(
            number1 = state.number1.dropLast(1)
        )
    }
}

private fun performCalculationAll() {
    val number1 = state.number1.toDoubleOrNull()
    val number2 = state.number2.toDoubleOrNull()
    if (number1 != null && number2 != null) {
        val resulting = when (state.operation) {
            is CalculationOperation.Add -> {
                if ((number1 + number2) % 1 == 0.0) (number1 + number2).toLong()
                else (number1 + number2).toFloat()
            }
            is CalculationOperation.Subtract -> {
                if ((number1 - number2) % 1 == 0.0) (number1 - number2).toLong()
                else (number1 - number2).toFloat()
            }
            is CalculationOperation.Multiply -> {
                if ((number1 * number2) % 1 == 0.0) (number1 * number2).toLong()
                else (number1 * number2).toFloat()
            }
            is CalculationOperation.Divide -> {
                if ((number1 / number2) % 1 == 0.0) (number1 / number2).toLong()
                else (number1 / number2).toFloat()
            }
            is CalculationOperation.Percent -> {
                if ((number1 / 100 * number2) % 1 == 0.0) (number1 / 100 * number2).toLong()
                else (number1 / 100 * number2).toFloat()
            }
            null -> return
        }
        state = state.copy(
            number0 = resulting.toString().take(15),
        )
    }
}

private fun enterOperation(operation: CalculationOperation) {
    if (state.number1.isNotBlank()) {
        state = state.copy(operation = operation)
    }
}

private fun enterDecimal() {
    if (state.operation == null && !state.number1.contains(".")
        && state.number1.isNotBlank()
    ) {
        state = state.copy(number1 = state.number1 + ".")
    }
    if (!state.number2.contains(".") && state.number2.isNotBlank()
    ) {
        state = state.copy(number2 = state.number2 + ".")
    }
}

private fun enterNumber(number: Long) {
    if (state.operation == null) {
        if (state.number1.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number1 = state.number1 + number
        )
        return
    }
    if (state.number2.length >= MAX_NUM_LENGTH) {
        return
    }
    state = state.copy(
        number2 = state.number2 + number
    )
}

companion object {
    private const val MAX_NUM_LENGTH = 15
}
}