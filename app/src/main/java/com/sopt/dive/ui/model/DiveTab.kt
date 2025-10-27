package com.sopt.dive.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.sopt.dive.R

enum class DiveTab(
    @DrawableRes val iconResId: Int,
    @StringRes val label: Int,
    val defaultColor: Color = Color.Gray,
    val selectedColor: Color = Color.Blue
) {
    HOME(
        iconResId = R.drawable.ic_home_24,
        label = R.string.home_title
    ),
    SEARCH(
        iconResId = R.drawable.ic_search_24,
        label = R.string.search_title
    ),
    MYPAGE(
        iconResId = R.drawable.ic_person_24,
        label = R.string.mypage_title
    )
}