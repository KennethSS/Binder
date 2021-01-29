package com.solar.binder.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solar.binder.model.Food
import com.solar.binder.model.FoodFactory
import com.solar.library.binder.listener.PagingListener
import com.solar.library.binder.viewmodel.ViewModelList
import com.solar.library.binder.viewmodel.ViewModelPagingList
import java.lang.Thread.sleep

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
class FoodListMoreViewModel : ViewModel(), ViewModelPagingList<Food>, PagingListener {
    private val _list = MutableLiveData<List<Food>>()
    override val list: LiveData<List<Food>>
        get() = _list
    override var isPaging: Boolean = true

    @MainThread
    fun fetchFoodList() {
        getFoodList()
    }

    private fun getFoodList() {
        Thread {
            sleep(2000)
            val foods = FoodFactory.getFoodList(5)
            val currentList = (list.value ?: arrayListOf())
            _list.postValue(currentList + foods)

            if (foods.last().title == "Luttuce combo") {
                isPaging = false
            }
        }.start()
    }

    override fun fetchNextPage() {
        getFoodList()
    }
}