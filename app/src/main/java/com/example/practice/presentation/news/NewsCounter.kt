package com.example.practice.presentation.news

import io.reactivex.rxjava3.subjects.BehaviorSubject

object NewsCounter {
    val counter: BehaviorSubject<Int> = BehaviorSubject.create()
}
