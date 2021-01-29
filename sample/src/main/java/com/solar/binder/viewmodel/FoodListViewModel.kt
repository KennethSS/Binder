package com.solar.binder.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.solar.binder.model.Food
import com.solar.binder.model.FoodFactory
import com.solar.library.binder.viewmodel.ViewModelList
import kotlinx.coroutines.Dispatchers

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
class FoodListViewModel : ViewModel(), ViewModelList<Food> {
    private val _list = MutableLiveData<Int>()
    override val list: LiveData<ArrayList<Food>>

    init {
        list = _list.switchMap {
            liveData(Dispatchers.IO) {
                val foods = FoodFactory.getFoodList(5)
                val array = arrayListOf<Food>()
                array.addAll(foods)
                emit(array)
            }
        }
    }

    @MainThread
    fun fetchFoodList() {
        _list.value = 0
    }
}