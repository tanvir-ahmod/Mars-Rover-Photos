package com.example.marsroverimages.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {
    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB

    private var loader = Loader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = getViewBinding()
        mViewModel.showLoader.observe(this, Observer { isShow ->
            if (isShow) {
                try {
                    loader.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                loader.isCancelable = false
                loader.show(supportFragmentManager, "loader")
            }
            else{
                try {
                    loader.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    abstract fun getViewBinding(): VB
}