package com.solar.library.binder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BindingFragment<T : ViewDataBinding> constructor(
  @LayoutRes private val layoutId: Int
) : Fragment() {

  private var _binding: T? = null

  protected val binding: T
    get() = checkNotNull(_binding) {
      "$this Fragment cannot be accessed before onCreateView() or after onDestroyView()"
    }

  abstract fun onViewCreated(
    binding: T,
    savedInstanceState: Bundle?
  )

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    onViewCreated(binding, savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}