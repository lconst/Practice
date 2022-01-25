package com.example.practice.data.db.mappers

interface Mapper<T, K> {
    fun mapFrom(source: T): K
}
