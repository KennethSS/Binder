package com.solar.library.binder.listener

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solar.library.binder.RecyclerViewController

/**
 *  Created by Kenneth on 12/16/20
 */
class LoadMoreScrollListener(
    private val controller: RecyclerViewController,
    private val pagingListener: PagingListener,
    private val threshold: Int = 0
) : RecyclerView.OnScrollListener() {

    private var previousCount: Int = 0

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

            if (controller.isAttachedThreshold) {
                if (previousCount < totalItemCount) {
                    controller.isAttachedThreshold = false
                }
            } else {
                if (visibleItemCount + lastVisibleItemPosition + threshold > totalItemCount) {
                    controller.isAttachedThreshold = true
                    previousCount = totalItemCount
                    pagingListener.fetchNextPage()
                }
            }
        }
    }
}