package com.example.practice

class KotlinPart1 {
    //Задание №1
    interface Publication {
        val price: Int
        val wordCount: Int

        fun getType(): String
    }

    class Book(override val price: Int, override val wordCount: Int) : Publication {
        override fun getType(): String {
            return when {
                wordCount < 1000 -> {
                    "Flash Fiction"
                }
                wordCount in 1000..7500 -> {
                    "Short Story"
                }
                else -> {
                    "Novel"
                }
            }
        }

        override fun equals(other: Any?): Boolean {
            if (other == this) return true
            if ((other !is Book)) {
                return false
            }
            return this.price == other.price
                && this.wordCount == other.wordCount
        }
    }

    class Magazine(override val price: Int, override val wordCount: Int) : Publication {
        override fun getType(): String {
            return "Magazine"
        }
    }

}

//Задание №2
fun main(args: Array<String>) {
    val book1 = KotlinPart1.Book(20, 10_000)
    val book2 = KotlinPart1.Book(20, 10_000)
    val magazine = KotlinPart1.Magazine(5, 300)

    println("Книга1: ${book1.getType()} ${book1.wordCount} строк ${book1.price}\u20AC")
    println("Книга2: ${book2.getType()} ${book2.wordCount} строк ${book2.price}\u20AC")
    println("Журнал: ${magazine.getType()} ${magazine.wordCount} строк ${magazine.price}\u20AC")

    println(book1 === book2)
    println(book1 == book2)

    //Задание №3
    val book3 = KotlinPart1.Book(20, 10_000)
    val book4: KotlinPart1.Publication? = null

    buy(book3)
    book4?.let { buy(it) }

    //Задание №4
    sum(5, 7)
}

//Задание №3
fun buy(publication: KotlinPart1.Publication) {
    println("The purchase is complete. The purchase amount was ${publication.price}")
}

//Задание №4
val sum = { a: Int, b: Int -> println("$a + $b = ${a + b}") }
