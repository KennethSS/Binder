package com.solar.binder.model

import com.solar.binder.R
import kotlin.random.Random

object FoodFactory {

    fun getFoodList(size: Int): List<Food> {
        val random = Random.nextInt()
        return getRandomFood().shuffled()
    }

    private fun getRandomFood(): List<Food> {
        return listOf(
            Food("Luttuce combo", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_1, id = 0),
            Food("Courmet combo", "VEG BREAKFAST", getPrice(), R.drawable.item_food_2, id = 1),
            Food("Spaghetti", "VEG BREAKFAST", getPrice(), R.drawable.item_food_3, id = 2),
            Food("Fries Combo", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_4, id = 3),
            Food("Spaghetti combo", "VEG BREAKFAST", getPrice(), R.drawable.item_food_5, id = 4)
        )
    }

    private fun getPrice(): String = (3..20).random().toString()
}