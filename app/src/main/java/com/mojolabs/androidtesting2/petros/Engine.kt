package com.mojolabs.androidtesting2.petros

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Engine(
    val cc: Int,
    val hp: Int,
    var temperature: Int,
    var isTurnedOn: Boolean
) {

    suspend fun turnOn(): Flow<Int> {
        isTurnedOn = true

        return flow {
            delay(2000)
            temperature = 25
            emit(temperature)

            delay(2000)
            temperature += 25
            emit(temperature)

            delay(2000)
            temperature += 45
            emit(temperature)

            Log.e("Engine", "turnOn() | Engine has turned on.")
        }
    }

    fun turnOff() {
        isTurnedOn = false
        temperature = 15
    }
}
