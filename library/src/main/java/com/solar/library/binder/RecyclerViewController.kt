package com.solar.library.binder

import androidx.recyclerview.widget.RecyclerView
import com.solar.library.binder.listener.LoadMoreScrollListener
import com.solar.library.binder.listener.PagingListener

class RecyclerViewController(
    rv: RecyclerView,
    paging: PagingListener,
    var isAttachedThreshold: Boolean = false
) {
    init {
        rv.addOnScrollListener(LoadMoreScrollListener(this, paging))
    }
}
