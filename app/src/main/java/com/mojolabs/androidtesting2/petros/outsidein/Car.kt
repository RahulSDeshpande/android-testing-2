package com.mojolabs.androidtesting2.petros.outsidein

import com.mojolabs.androidtesting2.petros.Engine

class Car(
    var engine: Engine,
    var fuel: Double
) {

    fun turnOn() {
        fuel -= 0.5
        engine.turnOn()
    }
}
