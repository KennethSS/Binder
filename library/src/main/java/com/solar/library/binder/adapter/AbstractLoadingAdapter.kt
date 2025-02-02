package com.solar.library.binder.adapter

import androidx.lifecycle.ViewModel
import com.solar.library.binder.R
import com.solar.library.binder.holder.BindingHolder
import com.solar.library.binder.holder.ItemType
import com.solar.library.binder.viewmodel.ViewModelPagingList

abstract class AbstractLoadingAdapter<T : ItemType>(

    private val viewModelList: ViewModelPagingList<T>,
    viewModel: ViewModel? = null,
) : AbstractDataBindingAdapter<T>(viewModelList, viewModel) {

    var previousCount = if (viewModelList.isPaging) size + 1 else size

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        viewModelList.list.value?.let { list ->
            if (viewModelList.isPaging && position == list.size) {
                return
            } else {
                super.onBindViewHolder(holder, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (viewModelList.isPaging && position == size) R.layout.item_loading
        else viewModelList.list.value?.get(position)?.layoutRes ?: 0
    }

    override fun getItemCount(): Int {
        val count = if (viewModelList.isPaging) size + 1 else size
        previousCount = count
        return count
    }
}