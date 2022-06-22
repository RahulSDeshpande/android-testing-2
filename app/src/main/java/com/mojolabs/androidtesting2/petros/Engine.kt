package com.mojolabs.androidtesting2.petros

class Engine(
    val cc: Int,
    val hp: Int,
    var temperature: Int,
    var isTurnedOn: Boolean
) {

    fun turnOn() {
        isTurnedOn = true
        temperature = 95
    }

    fun turnOff() {
        isTurnedOn = false
        temperature = 15
    }
}
