package com.solar.library.binder.listener

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
class PagingScroll(
        private val onNextPage: () -> Unit,
        private val threshold: Int = 3
) : RecyclerView.OnScrollListener() {

    private var previousCount: Int = 0
    private var isLoadingPage: Boolean = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        recyclerView.layoutManager?.let { lm ->
            val visibleItemCount = lm.childCount
            val totalItemCount = lm.itemCount

            val lastVisibleItemPosition = when (lm) {
                is LinearLayoutManager -> lm.findLastVisibleItemPosition()
                is GridLayoutManager -> lm.findLastVisibleItemPosition()
                else -> return
            }

            if (isLoadingPage) {
                if (previousCount < totalItemCount) {
                    isLoadingPage = false
                }
            } else {
                if (visibleItemCount + lastVisibleItemPosition + threshold > totalItemCount) {
                    previousCount = totalItemCount
                    onNextPage()
                    isLoadingPage = true
                }
            }
        }
    }
}