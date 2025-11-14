package com.sopt.dive.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.sopt.dive.R
import com.sopt.dive.presentation.theme.defaultDiveColors

enum class DiveTab(
    @DrawableRes val iconResId: Int,
    @StringRes val label: Int,
    val defaultColor: Color = defaultDiveColors.gray400,
    val selectedColor: Color = defaultDiveColors.mainBlue
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