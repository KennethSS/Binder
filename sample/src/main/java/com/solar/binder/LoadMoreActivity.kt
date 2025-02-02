package com.solar.binder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.solar.binder.adapter.FoodLoadingAdapter
import com.solar.binder.viewmodel.FoodListMoreViewModel
import com.solar.binder.viewmodel.FoodViewModel
import com.solar.library.binder.RecyclerViewController

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
class LoadMoreActivity : AppCompatActivity() {
    private val foodItemViewModel: FoodViewModel by viewModels()
    private val foodListMoreViewModel: FoodListMoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        findViewById<RecyclerView>(R.id.food_list_view).also {
            RecyclerViewController(it, foodListMoreViewModel)
        }.let { recyclerView ->
            val adapter = FoodLoadingAdapter(foodListMoreViewModel, foodItemViewModel)
            foodListMoreViewModel.list.observe(this, adapter.observer)
            recyclerView.adapter = adapter
        }

        foodListMoreViewModel.fetchFoodList()

        foodItemViewModel.toastEvent.observe(this, {
            Toast.makeText(this, "Food is $it", Toast.LENGTH_SHORT).show()
        })

        //foodListMoreViewModel.fetchFoodList()
    }
}