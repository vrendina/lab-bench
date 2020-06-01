package com.victorrendina.data.mappers

interface Mapper<F, T> {
    fun map(from: F): T
}
