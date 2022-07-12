package com.mojolabs.androidtesting2.petros.outsidein

import com.mojolabs.androidtesting2.petros.Engine
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class CarShould {

    private val engine: Engine = mock()

    private val car =
        Car(
            engine = engine,
            fuel = 5.0
        )

    @Test
    fun loseFuelWhenTurnedOn() {
        car.turnOn()

        assertEquals(4.5, car.fuel, 0.0)
    }

    @Test
    fun turnOnEngine() {
        car.turnOn()

        verify(
            mock = engine,
            mode = times(1)
        ).turnOn()
    }
}
