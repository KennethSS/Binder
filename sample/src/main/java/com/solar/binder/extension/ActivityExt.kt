package com.solar.binder.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import kotlin.properties.ReadWriteProperty

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

inline fun <reified T>View.startActivity(vararg pairs: Pair<String, Any>) {
    context.startActivity(Intent(context, T::class.java).apply {
        for ((key, value) in pairs) {
            when(value) {
                is Boolean -> putExtra(key, value)
                is Byte -> putExtra(key, value)
                is Char -> putExtra(key, value)
                is Double -> putExtra(key, value)
                is Float -> putExtra(key, value)
                is Int -> putExtra(key, value)
                is Long -> putExtra(key, value)
                is Short -> putExtra(key, value)
            }
        }
    })
}