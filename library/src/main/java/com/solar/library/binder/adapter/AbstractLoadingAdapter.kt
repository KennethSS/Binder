package com.solar.library.binder.adapter

import androidx.lifecycle.ViewModel
import com.solar.library.binder.R
import com.solar.library.binder.RecyclerViewController
import com.solar.library.binder.holder.BindingHolder
import com.solar.library.binder.holder.ItemType
import com.solar.library.binder.viewmodel.ViewModelList

abstract class AbstractLoadingAdapter<T : ItemType>(
    private val viewModelList: ViewModelList<T>,
    private val controller: RecyclerViewController,
    viewModel: ViewModel? = null,
) : AbstractDataBindingAdapter<T>(viewModelList, viewModel) {

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        viewModelList.list.value?.let { list ->
            if (controller.isLoading && position == list.size) {
                return
            } else {
                super.onBindViewHolder(holder, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (controller.isLoading && position == size) R.layout.item_loading
        else viewModelList.list.value?.get(position)?.layoutRes ?: 0
    }

    override fun getItemCount(): Int {
        return if (controller.isLoading) size + 1 else size
    }
}