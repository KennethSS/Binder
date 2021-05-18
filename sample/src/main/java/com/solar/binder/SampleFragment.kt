package com.solar.binder

import android.os.Bundle
import com.solar.binder.databinding.FragmentSampleBinding
import com.solar.library.binder.BindingFragment

class SampleFragment : BindingFragment<FragmentSampleBinding>(R.layout.fragment_sample) {
  override fun onViewCreated(
    binding: FragmentSampleBinding,
    savedInstanceState: Bundle?
  ) {

  }
}