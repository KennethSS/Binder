package com.solar.library.binder.holder

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.solar.library.binder.BR

class BindingHolder<T>(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(item: T,
             position: Int,
             viewModel: ViewModel? = null,
             scrollToPosition: Int? = 0) {
        with(binding) {
            setVariable(BR.item, item)
            setVariable(BR.position, position)
            setVariable(BR.vm, viewModel)
            setVariable(BR.scrollToPosition, scrollToPosition)
            executePendingBindings()
        }
    }
}