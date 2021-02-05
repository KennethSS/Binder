package com.solar.binder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.solar.binder.adapter.FoodAdapter
import com.solar.binder.viewmodel.FoodListViewModel
import com.solar.binder.viewmodel.FoodViewModel

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
class ListActivity : AppCompatActivity() {
    private val foodViewModel: FoodViewModel by viewModels()
    private val foodListViewModel: FoodListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        findViewById<RecyclerView>(R.id.food_list_view).let {
            it.adapter = FoodAdapter(this@ListActivity, foodListViewModel, foodViewModel)
        }

        foodViewModel.toastEvent.observe(this, {
            Toast.makeText(this, "Food is $it", Toast.LENGTH_SHORT).show()
        })

        foodListViewModel.fetchFoodList()
    }
}