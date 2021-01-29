package com.solar.binder.model

import androidx.annotation.DrawableRes
import com.solar.binder.R
import com.solar.library.binder.holder.ItemType

data class Food (
    var title: String,
    val subtitle: String,
    val price: String,
    @DrawableRes
    val img: Int,
    var id: Int = 0,
    override val layoutRes: Int = R.layout.item_food_menu
) : ItemType