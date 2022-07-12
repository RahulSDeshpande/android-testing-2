package com.mojolabs.androidtesting2.petros.outsidein

import org.junit.Assert.assertEquals
import org.junit.Test

class CarFeature {

    val car = Car(6.0)

    @Test
    fun carIsLosingFuelWhenItsTurnedOn() {
        car.turnOn()

        assertEquals(5.5, car.fuel, 0.0)
    }
}
