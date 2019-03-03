package com.noemi.android.meniu.adapter

import android.support.v4.view.ViewPager
import android.view.View

class MenuPageTransformer(private val maxTranslateOffsetX: Int) : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val viewPager = page.parent as ViewPager

        val leftScreen = page.left - viewPager.scrollX
        val centerXInViewPager = leftScreen + page.measuredWidth / 2
        val offsetX = centerXInViewPager - viewPager.measuredWidth / 2

        val offsetRate = offsetX * 0.38f / viewPager.measuredWidth
        val scaleFactor = 1 - Math.abs(offsetRate)

        if (scaleFactor > 0) {
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor
            page.alpha = scaleFactor
            page.translationX = (-maxTranslateOffsetX * offsetRate)
        }
    }

}