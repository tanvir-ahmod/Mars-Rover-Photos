package com.example.marsroverimages.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.marsroverimages.R

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {
    protected abstract val mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}