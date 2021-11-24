package com.example.practice

import org.junit.Assert.*
import org.junit.Test

class StringTrainingUnitTest {

    private val stringTraining = StringsTraining()

    @Test
    fun getOddCharacterString() {
        assertEquals("BDF", stringTraining.getOddCharacterString("ABCDEF"))
    }
    @Test
    fun getArrayLastSymbol() {
        val excepted = intArrayOf(0,3,6)
        assertArrayEquals(excepted, stringTraining.getArrayLastSymbol("AB_AB_AB_A"))
    }
    @Test
    fun getNumbersCount() {
        assertEquals(1, stringTraining.getNumbersCount("ABC_1_AS"))
        assertEquals(0, stringTraining.getNumbersCount("ABC__AS"))
    }
    @Test
    fun replaceAllNumbers() {
        assertEquals("ABC_ABC_1", stringTraining.replaceAllNumbers("ABC_1"))
        assertEquals("ABC", stringTraining.replaceAllNumbers("ABC"))
    }
    @Test
    fun capitalReverse() {
        assertEquals("abc-ABC", stringTraining.capitalReverse("ABC-abc"))
        assertEquals("ABCDEFG", stringTraining.capitalReverse("abcdefg"))
        assertEquals("abcdefg", stringTraining.capitalReverse("ABCDEFG"))
    }
}
