package com.mojolabs.androidtesting2.petros.outsidein

import com.mojolabs.androidtesting2.petros.Engine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CarFeature {

    private val engine =
        Engine(
            cc = 2000,
            hp = 189,
            temperature = 15,
            isTurnedOn = false
        )

    private val car = Car(engine = engine, fuel = 6.0)

    @Test
    fun carIsLosingFuelWhenItsTurnedOn() {
        car.turnOn()

        assertEquals(5.5, car.fuel, 0.0)
    }

    @Test
    fun carIsTurningOnItsEngineAndIncreasesTheTemperature() {
        car.turnOn()

        assertEquals(95, car.engine.temperature)
        assertTrue(car.engine.isTurnedOn)
    }
}
