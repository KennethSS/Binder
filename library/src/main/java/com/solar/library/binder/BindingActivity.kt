package com.solar.library.binder

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {

  protected val binding: T by lazy {
    DataBindingUtil.setContentView(this, layoutId)
  }
}