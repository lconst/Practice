package com.example.practice

//Задание №1
enum class Type {
    DEMO, FULL
}

//Задание №2
data class User(val id: Int, val name: String, val age: Int, val type: Type) {
    val startTime by lazy { System.currentTimeMillis() }
}

//Задание №7
fun User.checkAge() = println(
    if (this.age > 18) {
        "$this старше 18"
    } else {
        ERROR
    }
)


fun main() {
    //Задание №3
    val user1 = User(1, "User-1", 5, Type.DEMO)
    println(user1.startTime)
    Thread.sleep(1000)
    println(user1.startTime)

    //Задание №4
    val userList = mutableListOf(user1)
    userList.apply {
        add(User(2, "User-2", 10, Type.FULL))
        add(User(3, "User-3", 20, Type.FULL))
        add(User(4, "User-4", 30, Type.DEMO))
    }
    println(userList)

    //Задание №5
    println(userList.filter { user -> user.type == Type.FULL })

    //Задание №6
    userList.map { user -> user.name }
        .also { nameList -> println(nameList.first()) }
        .also { nameList -> println(nameList.last()) }

    //Задание №7
    userList.first().checkAge()

    //Задание №10
    user1.auth(::updateCache)

}

//Задание №8
val access = object : AuthCallback {
    override fun authSuccess() {
        println("Success")
    }

    override fun authFailed() {
        println("Failed")
    }
}

interface AuthCallback {
    fun authSuccess()
    fun authFailed()
}

//Задание №9
//Задание №10
fun updateCache() = println("Update cache")

inline fun User.auth(action: () -> Unit) {
    if (checkAge().equals(ERROR)) {
        access.authFailed()
    } else {
        action()
        access.authSuccess()
    }
}

//Задание №11
sealed class Action {
    object Registration : Action()
    object Logout : Action()
    class Login(val user: User) : Action()
}

//Задание №12
fun doAction(action: Action) {
    when (action) {
        is Action.Login -> action.user.auth(::updateCache)
        is Action.Logout -> println("Logout")
        is Action.Registration -> println("Registration")
    }

}

const val ERROR = "Ошибка"
