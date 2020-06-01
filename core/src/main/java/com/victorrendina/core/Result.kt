package com.victorrendina.core

sealed class Result <out T>

data class Success<out T>(val value: T) : Result<T>()

data class Fail<out T>(val error: Throwable) : Result<T>()
