package com.example.tybatest.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun getInfiniteScrollForRecyclerView(linearLayoutManager: LinearLayoutManager, itemCount: Int): RecyclerView.OnScrollListener {
    return object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastItemVisible = linearLayoutManager.findLastVisibleItemPosition()
            if (lastItemVisible % (itemCount - 1) == 0) {
                recyclerView.layoutManager?.scrollToPosition(0)
            }
        }
    }
}