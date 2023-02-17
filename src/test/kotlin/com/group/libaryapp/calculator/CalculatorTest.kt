package com.group.libaryapp.calculator

import com.group.libraryapp.calculator.Calculator

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiflyTest()
    calculatorTest.divideTest()
    calculatorTest.divideExceptionTest()
}

class CalculatorTest {

    fun addTest() {
        val calculator = Calculator(5)
        calculator.add(3)

        //val expectedCalculator = Calculator(8)
        if (calculator.number != 8) {
            throw IllegalStateException()
        }
    }

    fun minusTest() {
        val calculator = Calculator(5)
        calculator.minus(3)

        if (calculator.number != 2) {
            throw IllegalStateException()
        }
    }

    fun multiflyTest() {
        val calculator = Calculator(5)
        calculator.multifly(3)

        if (calculator.number != 15) {
            throw IllegalStateException()
        }
    }

    fun divideTest() {
        val calculator = Calculator(5)
        calculator.divide(2)

        if (calculator.number != 2) {
            throw IllegalStateException()
        }
    }

    fun divideExceptionTest() {
        val calculator = Calculator(5)

        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            if (e.message != "0으로 나눌 수 없습니다.") {
                throw IllegalStateException("메시지가 다릅니다")
            }
            return
        } catch (e: Exception) {
            throw IllegalStateException()
        }

        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다.")
    }
}