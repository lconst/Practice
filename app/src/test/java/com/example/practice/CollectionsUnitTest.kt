package com.example.practice

import org.junit.Assert.*
import org.junit.Test

class CollectionsUnitTest {
    private val collectionsStringBlock = CollectionsBlock<String>()
    private val collectionsIntBlock = CollectionsBlock<Int>()

    @Test
    fun collectionTask0() {
        val exceptedInt = listOf(9, 8, 7, 6, 5, 4, 3, 2, 1)
        val exceptedString = listOf("c", "c", "b", "b", "a", "a")
        assertEquals(
            exceptedInt,
            collectionsIntBlock.collectionTask0(listOf(5, 4, 3, 2, 1), listOf(9, 8, 7, 6))
        )
        assertEquals(
            exceptedString,
            collectionsStringBlock.collectionTask0(listOf("c", "b", "a"), listOf("c", "b", "a"))
        )
    }

    @Test
    fun collectionTask1() {
        val exceptedInt = listOf(1, 2, 1, 3, 1, 2, 1)
        val exceptedString = listOf("one", "two", "one", "tree", "one", "two", "one")
        assertEquals(exceptedInt, collectionsIntBlock.collectionTask1(listOf(1, 2, 3)))
        assertEquals(
            exceptedString,
            collectionsStringBlock.collectionTask1(listOf("one", "two", "tree"))
        )
    }

    @Test
    fun collectionTask2() {
        assertEquals(
            true,
            collectionsStringBlock.collectionTask2(
                listOf("one", "two", "tree"),
                listOf("one", "two")
            )
        );
        assertEquals(
            false,
            collectionsStringBlock.collectionTask2(
                listOf("one", "two", "tree"),
                listOf("five", "two")
            )
        );
    }

    @Test
    fun collectionTask3() {
        val param = listOf(1, 2, 3, 4, 5, 6)
        val exceptedLeftShift = listOf(3, 4, 5, 6, 1, 2)
        val exceptedRightShift = listOf(5, 6, 1, 2, 3, 4)
        assertEquals(exceptedLeftShift, collectionsIntBlock.collectionTask3(param, -2))
        assertEquals(exceptedLeftShift, collectionsIntBlock.collectionTask3(param, -8))
        assertEquals(exceptedRightShift, collectionsIntBlock.collectionTask3(param, 2))
        assertEquals(exceptedRightShift, collectionsIntBlock.collectionTask3(param, 8))
    }

    @Test
    fun collectionTask4() {
        val param = listOf("one", " ", "two", " ", "one-one", " ", "two")
        val excepted = listOf("ONE", " ", "two", " ", "ONE-ONE", " ", "two")
        assertEquals(excepted, collectionsStringBlock.collectionTask4(param, "one", "ONE"))
    }

    @Test
    fun collectionTask5() {
        //simple test for run task and show result in output
        assert(collectionsStringBlock.collectionTask5())
    }
}
