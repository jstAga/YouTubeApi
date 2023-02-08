package com.geektech.youtubeapi.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding : VB
    protected lateinit var viewModel : VM
    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        isConnection()
        initViews()
        initViewModel()
        initListener()
        initObserver()
    }

    open fun isConnection() {}
    open fun initViews() {}
    open fun initViewModel() {}
    open fun initListener() {}
    open fun initObserver() {}
}