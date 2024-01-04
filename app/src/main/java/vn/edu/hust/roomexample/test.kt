package vn.edu.hust.roomexample

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.random.Random

fun main() = runBlocking {
    val value1 = async {
        println("Job1 started")
        delay(3000L)
        println("Job1 completed")
        return@async Random.nextInt(10, 100)
    }

    val value2 = async {
        println("Job2 started")
        delay(5000L)
        println("Job2 completed")
        return@async Random.nextInt(10, 100)
    }

    println("Launched 2 jobs")
    println("Value 1: ${value1.await()}")
    println("Value 2: ${value2.await()}")
}