package com.mojolabs.androidtesting2.petros.outsidein

import org.junit.Assert.assertEquals
import org.junit.Test

class CarShould {

    private val car = Car(5.0)

    @Test
    fun loseFuelWhenTurnedOn() {
        car.turnOn()

        assertEquals(4.5, car.fuel)
    }
}
