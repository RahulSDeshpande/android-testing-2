package com.mojolabs.androidtesting2.petros.unittests

import com.mojolabs.androidtesting2.petros.Engine
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
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
        runTest {
            engine.turnOn()
        }

        assertTrue(engine.isTurnedOn)
    }

    @Test
    fun raiseTheTemperatureGraduallyWhenItTurnsOn() {
        runTest {
            val flow = engine.turnOn()
            val actual = flow.toList()
            assertEquals(listOf(25, 50, 95), actual)
        }
    }
}
