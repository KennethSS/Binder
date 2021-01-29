package com.solar.binder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.solar.binder.adapter.FoodAdapter
import com.solar.binder.viewmodel.FoodListViewModel
import com.solar.binder.viewmodel.FoodViewModel

class MainActivity : AppCompatActivity() {
    private val foodViewModel: FoodViewModel by viewModels()
    private val foodListViewModel: FoodListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.food_list_view).let {
            it.adapter = FoodAdapter(this@MainActivity, foodListViewModel, foodViewModel)
        }

        foodViewModel.toastEvent.observe(this, Observer {
            Toast.makeText(this, "Food is $it", Toast.LENGTH_SHORT).show()
        })

        foodListViewModel.fetchFoodList()
    }
}