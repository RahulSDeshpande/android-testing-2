package com.mojolabs.androidtesting2.petros.outsidein

import com.mojolabs.androidtesting2.petros.Engine
import org.junit.Assert.assertTrue
import org.junit.Test

class EngineShould {

    private val engine =
        Engine(
            cc = 2000,
            hp = 189,
            temperature = 15,
            isTurnedOn = false
        )

    @Test
    fun turnOn() {
        engine.turnOn()

        assertTrue(engine.isTurnedOn)
    }
}
