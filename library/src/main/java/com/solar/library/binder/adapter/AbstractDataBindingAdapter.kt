package com.solar.library.binder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.solar.library.binder.holder.BindingHolder
import com.solar.library.binder.holder.ItemType
import com.solar.library.binder.viewmodel.ViewModelList

abstract class AbstractDataBindingAdapter<T : ItemType>(
    private val viewModelList: ViewModelList<T>,
    private val viewModel: ViewModel? = null
) : RecyclerView.Adapter<BindingHolder<T>>() {

    protected val size: Int
        get() = viewModelList.list.value?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val biding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return BindingHolder(biding)
    }

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        viewModelList.list.value?.let { list ->
            if (list.isNotEmpty()) {
                holder.bind(list[position], position, viewModel)
            }
        }
    }


    override fun getItemCount(): Int = size

    override fun getItemViewType(position: Int): Int = viewModelList.list.value?.get(position)?.layoutRes ?: 0
}