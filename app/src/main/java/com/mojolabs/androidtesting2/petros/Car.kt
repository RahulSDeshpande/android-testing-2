package com.mojolabs.androidtesting2.petros

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Car(
    var engine: Engine,
    var fuel: Double
) {

    fun turnOn() {
        fuel -= 0.5

        CoroutineScope(Dispatchers.Main).launch {
            engine.turnOn().collect { temperature ->
                Log.e("Car", "turnOn() | Collected engine temp: $temperature")
            }
        }
    }
}
