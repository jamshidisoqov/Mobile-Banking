package uz.gita.mobile_banking.utils

import androidx.viewpager2.widget.ViewPager2

// Created by Jamshid Isoqov on 12/29/2022


fun ViewPager2.currentPageCallback(block: (Int) -> Unit) {
    this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            block.invoke(position)
        }
    })
}