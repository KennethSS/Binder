package com.solar.library.binder.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.solar.library.binder.holder.BindingHolder
import com.solar.library.binder.holder.ItemType
import com.solar.library.binder.viewmodel.ViewModelList

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
abstract class AbstractBaseAdapter<T : ItemType, VH : RecyclerView.ViewHolder> :
        RecyclerView.Adapter<VH>() {

    protected open val list: MutableList<T> by lazy { mutableListOf() }

    fun add(item: T) {
        list.add(item)
        notifyItemInserted(itemCount)
    }

    fun addAt(index: Int, item: T) {
        if (index < list.size) {
            list.add(index, item)
            notifyItemInserted(index)
        }
    }

    fun addAll(list: List<T>) {
        val previous = this.list.size
        this.list.addAll(list)
        notifyItemRangeInserted(previous, list.size)
    }

    fun remove(item: T, isAnim: Boolean = false) {
        val index = list.indexOf(item)
        if (index != -1) {
            list.removeAt(index)

            if (isAnim) notifyItemRangeRemoved(index, 1)
            else notifyDataSetChanged()
        }
    }

    fun removeAt(position: Int, isAnim: Boolean = false) {
        if (position < list.size) {
            list.removeAt(position)

            if (isAnim) notifyItemRangeRemoved(position, 1)
            else notifyDataSetChanged()
        }
    }

    fun update(index: Int, item: T) {
        if (index < list.size) {
            list[index] = item
            notifyItemChanged(index)
        }
    }

    fun submitList(list: List<T>) {
        this.list.run {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    fun getItem(index: Int): T = list[index]

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = list[position].layoutRes
}