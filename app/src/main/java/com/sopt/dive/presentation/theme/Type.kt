package com.sopt.dive.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

sealed interface TypographyTokens {

    @Immutable
    data class Header(
        val medium_bold: TextStyle
    )

    @Immutable
    data class Body(
        val large_semibold: TextStyle,
        val large_regular: TextStyle,
        val medium_semibold: TextStyle,
        val medium_regular: TextStyle
    )

    @Immutable
    data class Caption(
        val large_semibold: TextStyle,
        val large_regular: TextStyle,
        val medium_semibold: TextStyle,
        val medium_regular: TextStyle,
        val small_semibold: TextStyle,
        val small_regular: TextStyle
    )
}

@Immutable
data class DiveTypography(
    val heather: TypographyTokens.Header,
    val body: TypographyTokens.Body,
    val caption: TypographyTokens.Caption
)

val defaultDiveTypography = DiveTypography(
    heather = TypographyTokens.Header(
        medium_bold = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
        )
    ),
    body = TypographyTokens.Body(
        large_semibold = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 1.4.em,
            letterSpacing = 0.1.sp
        ),
        large_regular = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 1.4.em,
            letterSpacing = 0.1.sp
        ),
        medium_semibold = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 1.2.em,
            letterSpacing = 0.3.sp
        ),
        medium_regular = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 1.3.em,
            letterSpacing = 0.3.sp
        )
    ),
    caption = TypographyTokens.Caption(
        large_semibold = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 1.4.em,
            letterSpacing = 0.3.sp
        ),
        large_regular = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 1.4.em,
            letterSpacing = 0.3.sp
        ),
        medium_semibold = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 1.4.em,
            letterSpacing = 0.5.sp
        ),
        medium_regular = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 1.4.em,
            letterSpacing = 0.5.sp
        ),
        small_semibold = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 1.5.em,
            letterSpacing = 0.8.sp
        ),
        small_regular = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 1.5.em,
            letterSpacing = 0.8.sp
        )
    )
)