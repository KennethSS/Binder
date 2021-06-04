package com.solar.library.binder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.solar.library.binder.R
import com.solar.library.binder.holder.BindingHolder
import com.solar.library.binder.holder.ItemType

/**
 * Copyright 2020 Kenneth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/
abstract class AbstractBasicBindingAdapter<T : ItemType>(
        private val vm: ViewModel? = null,
        private var isShowProgress: Boolean = true
) : AbstractBaseAdapter<T, BindingHolder<T>>() {

    fun isShowProgress(isShowProgress: Boolean) {
        this.isShowProgress = isShowProgress
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val biding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return BindingHolder(biding)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isShowProgress && position == list.size)  R.layout.item_loading
        else list[position].layoutRes
    }

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        if (list.isNotEmpty() && position <= list.size - 1) {
            holder.bind(list[position], position, vm)
        }
    }

    override fun getItemCount(): Int = if (isShowProgress) list.size + 1 else list.size
}