package com.solar.binder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solar.binder.model.Food
import com.solar.binder.model.FoodFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
class FoodPagingViewModel : ViewModel() {

    private val _foodLiveData: MutableLiveData<List<Food>> by lazy { MutableLiveData<List<Food>>() }
    val foodLiveData: LiveData<List<Food>> = _foodLiveData

    fun fetchNextPaging() = viewModelScope.launch {
        delay(2000)
        _foodLiveData.postValue(FoodFactory.getFoodList(5))
    }
}