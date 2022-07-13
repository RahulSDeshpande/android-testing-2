package com.mojolabs.androidtesting2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mojolabs.androidtesting2.R
import com.mojolabs.androidtesting2.petros.Car
import com.mojolabs.androidtesting2.petros.Engine

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val engine =
            Engine(
                cc = 2000,
                hp = 189,
                temperature = 15,
                isTurnedOn = false
            )

        val car = Car(engine = engine, fuel = 20.0)

        car.turnOn()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
