package com.mojolabs.androidtesting2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mojolabs.androidtesting2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
