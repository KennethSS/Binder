package com.solar.binder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.solar.binder.adapter.FoodAdapter
import com.solar.binder.databinding.ActivityMainBinding
import com.solar.binder.extension.startActivity
import com.solar.binder.viewmodel.FoodListViewModel
import com.solar.binder.viewmodel.FoodViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.normal.setOnClickListener {
            it.startActivity<ListActivity>()
        }
        binding.more.setOnClickListener {
            it.startActivity<LoadMoreActivity>()
        }
        binding.basic.setOnClickListener {
            it.startActivity<FoodBasicActivity>()
        }
    }
}