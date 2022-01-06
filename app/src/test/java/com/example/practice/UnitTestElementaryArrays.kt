package com.example.practice

import org.junit.Assert.assertArrayEquals
import org.junit.Test

import org.junit.Assert.assertEquals

class UnitTestElementaryArrays {

    // Elementary
    @Test
    fun averageValue() {
        assertEquals(5.0, ElementaryTraining().averageValue(2, 8), 0.0)
    }

    @Test
    fun changeValue() {
        assertEquals(-7, ElementaryTraining().changeValue(3))
    }

    @Test
    fun complicatedAmount() {
        val excepted = (1 * 2 + 2 - 3 + 3 * 3) * 1.0
        assertEquals(excepted, ElementaryTraining().complicatedAmount(1, 2, 3), 0.0)
    }

    @Test
    fun swapNumbers() {
        assertEquals(4231, ElementaryTraining().swapNumbers(1234))
        assertEquals(123456, ElementaryTraining().swapNumbers(123456))
        assertEquals(321, ElementaryTraining().swapNumbers(123))
        assertEquals(1, ElementaryTraining().swapNumbers(10))
        assertEquals(5, ElementaryTraining().swapNumbers(5))
    }

    @Test
    fun zeroEvenNumber() {
        assertEquals(10305, ElementaryTraining().zeroEvenNumber(12345))
        assertEquals(6, ElementaryTraining().zeroEvenNumber(6))
    }

    // Arrays
    @Test
    fun sort() {
        val param = intArrayOf(5, 4, 3, 2, 1)
        val excepted = intArrayOf(1, 2, 3, 4, 5)
        assertArrayEquals(excepted, ArraysTraining().sort(param))
    }

    @Test
    fun maxValue() {
        assertEquals(100, ArraysTraining().maxValue(4, 2, 5, 100))
        assertEquals(0, ArraysTraining().maxValue())
    }

    @Test
    fun reverse() {
        val param = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val excepted = intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1)
        assertArrayEquals(excepted, ArraysTraining().reverse(param))
    }

    @Test
    fun fibonacciNumbers() {
        val excepted = intArrayOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
        assertArrayEquals(excepted, ArraysTraining().fibonacciNumbers(10))
    }

    @Test
    fun maxCountSymbol() {
        val params = intArrayOf(1, 2, 3, 4, 5, 6)
        assertEquals(1, ArraysTraining().maxCountSymbol(params))
    }
}
