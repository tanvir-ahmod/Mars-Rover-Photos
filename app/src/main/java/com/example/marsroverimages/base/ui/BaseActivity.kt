package com.example.marsroverimages.base.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {
    protected abstract val mViewModel: VM

}