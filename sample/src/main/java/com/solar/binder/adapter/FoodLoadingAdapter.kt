package com.solar.binder.adapter

import androidx.lifecycle.LifecycleOwner
import com.solar.binder.model.Food
import com.solar.binder.viewmodel.FoodViewModel
import com.solar.library.binder.RecyclerViewController
import com.solar.library.binder.adapter.AbstractLoadingAdapter
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
class FoodLoadingAdapter(
    lifecycleOwner: LifecycleOwner,
    viewModelList: ViewModelList<Food>,
    controller: RecyclerViewController,
    viewModel: FoodViewModel
) : AbstractLoadingAdapter<Food>(viewModelList, controller, viewModel) {
    init {
        viewModelList.list.observe(lifecycleOwner, {
            notifyDataSetChanged()
        })
    }
}