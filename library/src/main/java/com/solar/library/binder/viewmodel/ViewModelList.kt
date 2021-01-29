package com.solar.library.binder.viewmodel

import androidx.lifecycle.LiveData
import com.solar.library.binder.holder.ItemType

interface ViewModelList<T: ItemType> {
    val list: LiveData<List<T>>
}