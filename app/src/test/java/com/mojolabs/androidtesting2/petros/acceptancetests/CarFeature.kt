package com.mojolabs.androidtesting2.petros.acceptancetests

import com.mojolabs.androidtesting2.petros.Car
import com.mojolabs.androidtesting2.petros.Engine
import com.mojolabs.androidtesting2.petros.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CarFeature {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

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
    fun carIsTurningOnItsEngineAndIncreasesTheTemperatureGradually() {
        car.turnOn()

        mainCoroutineRule.advanceTimeBy(2000)
        assertEquals(25, car.engine.temperature)

        mainCoroutineRule.advanceTimeBy(2000)
        assertEquals(50, car.engine.temperature)

        mainCoroutineRule.advanceTimeBy(2000)
        assertEquals(95, car.engine.temperature)

        assertTrue(car.engine.isTurnedOn)
    }
}
