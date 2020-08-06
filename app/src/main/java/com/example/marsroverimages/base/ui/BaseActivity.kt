package com.example.marsroverimages.base.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marsroverimages.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}