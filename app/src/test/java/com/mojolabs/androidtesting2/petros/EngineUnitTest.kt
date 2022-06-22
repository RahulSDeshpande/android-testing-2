package com.mojolabs.androidtesting2.petros

import org.junit.Assert.assertEquals
import org.junit.Test

class EngineUnitTest {

    private val engine =
        Engine(
            cc = 2000,
            hp = 189,
            temperature = 15,
            isTurnedOn = false
        )

    @Test
    fun engineTurnsOn() {
        engine.turnOn()

        assertEquals(true, engine.isTurnedOn)
        assertEquals(95, engine.temperature)
    }

    @Test
    fun engineTurnsOff() {
        engine.turnOn()
        engine.turnOff()

        assertEquals(false, engine.isTurnedOn)
        assertEquals(15, engine.temperature)
    }
}
