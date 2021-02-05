package com.solar.binder

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.solar.binder.adapter.FoodAdapter
import com.solar.binder.adapter.FoodBasicAdapter
import com.solar.binder.model.FoodFactory
import com.solar.binder.viewmodel.FoodPagingViewModel

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
class FoodBasicActivity : AppCompatActivity() {

    private val viewModel: FoodPagingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_recycler_view)

        val foodAdapter = FoodBasicAdapter()
        viewModel.foodLiveData.observe(this, {
            foodAdapter.addAll(it)
        })

        val recyclerView = findViewById<CustomRecyclerView>(R.id.custom_recycler_view)

        with(recyclerView) {
            adapter = foodAdapter.apply {
                addAll(FoodFactory.getFoodList(5))
            }

            onNextPage = { viewModel.fetchNextPaging() }
        }
    }
}