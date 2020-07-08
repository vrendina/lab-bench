package com.victorrendina.labbench

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun channel_Test() {
        val channel = Channel<Int>(Channel.UNLIMITED)
        runBlocking {
            val one = launch {
                channel.consumeAsFlow().collect {
                    println("One received message $it")
                }
            }

            val two = launch {
                channel.consumeAsFlow().collect {
                    println("Two received message $it")
                }
            }

            channel.offer(1)
            delay(500)
            one.cancel()
            channel.offer(2)
            two.cancel()
            channel.offer(3)
            channel.close()
        }
    }
}
