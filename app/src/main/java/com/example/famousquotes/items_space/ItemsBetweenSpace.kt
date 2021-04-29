package com.example.famousquotes.items_space

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemsBetweenSpace: RecyclerView.ItemDecoration() {
    private final var verticalSpaceHeight = 0

    fun spacingItemDecoration(verticalSpaceHeight: Int) {
        this.verticalSpaceHeight = verticalSpaceHeight
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = verticalSpaceHeight
    }
}