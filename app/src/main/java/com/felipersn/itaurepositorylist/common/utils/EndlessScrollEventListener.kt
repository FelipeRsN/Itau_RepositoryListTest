package com.felipersn.itaurepositorylist.common.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollEventListener(private val linearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private val VISIBLE_THRESHOLD = 15

    var mOnLoadMoreListener: OnLoadMoreListener? = null
    var isLoading = false

    var totalItemCount = 0
    var previousTotalItemCount = 0
    var lastVisibleItemPosition = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        totalItemCount = linearLayoutManager.itemCount

        lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()

        if (totalItemCount < previousTotalItemCount) {
            previousTotalItemCount = totalItemCount
        }

        if (!isLoading && (lastVisibleItemPosition + VISIBLE_THRESHOLD) > totalItemCount) {
            isLoading = true
            mOnLoadMoreListener?.loadMore()
        }
    }

    fun reset() {
        previousTotalItemCount = 0
        totalItemCount = 0
        lastVisibleItemPosition = 0
    }

    interface OnLoadMoreListener {
        fun loadMore()
    }
}