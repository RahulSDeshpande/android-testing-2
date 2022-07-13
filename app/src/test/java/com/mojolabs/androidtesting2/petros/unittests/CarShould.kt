package com.mojolabs.androidtesting2.petros.unittests

import com.mojolabs.androidtesting2.petros.Car
import com.mojolabs.androidtesting2.petros.Engine
import com.mojolabs.androidtesting2.petros.util.MainCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CarShould {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val engine: Engine = mock()

    private val car: Car

    init {
        runTest {
            whenever(
                methodCall = engine.turnOn()
            ).thenReturn(
                flow {
                    delay(2000)
                    emit(25)
                    delay(2000)
                    emit(50)
                    delay(2000)
                    emit(95)
                }
            )
        }

        car = Car(engine, 5.0)
    }

    @Test
    fun loseFuelWhenTurnedOn() {
        car.turnOn()

        assertEquals(4.5, car.fuel, 0.0)
    }

    @Test
    fun turnOnEngine() {
        car.turnOn()

        runTest {
            verify(
                mock = engine,
                mode = times(1)
            ).turnOn()
        }
    }
}
