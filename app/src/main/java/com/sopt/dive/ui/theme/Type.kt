package com.sopt.dive.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

sealed interface TypographyTokens {

    @Immutable
    data class Header(
        val semibold_32: TextStyle
    )

    @Immutable
    data class Title(
        val bold_24: TextStyle,
        val bold_22: TextStyle
    )

    @Immutable
    data class Subtitle(
        val bold_20: TextStyle,
        val semibold_20: TextStyle
    )

    @Immutable
    data class Body(
        val bold_18: TextStyle,
        val semibold_18: TextStyle,
        val regular_18: TextStyle,
        val bold_16: TextStyle,
        val semibold_16: TextStyle,
        val regular_16: TextStyle
    )

    @Immutable
    data class Caption(
        val bold_14: TextStyle,
        val semibold_14: TextStyle,
        val regular_14: TextStyle,
        val semibold_12: TextStyle,
        val regular_12: TextStyle,
        val semibold_10: TextStyle,
        val regular_10: TextStyle
    )
}

@Immutable
data class DiveTypography(
    val heather: TypographyTokens.Header,
    val title: TypographyTokens.Title,
    val subtitle: TypographyTokens.Subtitle,
    val body: TypographyTokens.Body,
    val caption: TypographyTokens.Caption
)

val defaultDiveTypography = DiveTypography(
    heather = TypographyTokens.Header(
        semibold_32 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
        )
    ),
    title = TypographyTokens.Title(
        bold_24 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 28.8.sp, // 120%
            letterSpacing = (-1.6).sp
        ),
        bold_22 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 26.4.sp, // 120%
            letterSpacing = (-1.4).sp
        )
    ),
    subtitle = TypographyTokens.Subtitle(
        bold_20 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 26.sp, // 130%
            letterSpacing = (-1.0).sp
        ),
        semibold_20 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1.0).sp
        )
    ),
    body = TypographyTokens.Body(
        bold_18 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 25.2.sp, // 140%
            letterSpacing = (-0.5).sp
        ),
        semibold_18 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 25.2.sp,
            letterSpacing = (-0.5).sp
        ),
        regular_18 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 25.2.sp,
            letterSpacing = (-0.5).sp
        ),
        bold_16 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 22.4.sp, // 140%
            letterSpacing = 0.sp
        ),
        semibold_16 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 22.4.sp,
            letterSpacing = 0.sp
        ),
        regular_16 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.4.sp,
            letterSpacing = 0.sp
        )
    ),
    caption = TypographyTokens.Caption(
        bold_14 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 19.6.sp, // 140%
            letterSpacing = 0.sp
        ),
        semibold_14 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 19.6.sp,
            letterSpacing = 0.sp
        ),
        regular_14 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 19.6.sp,
            letterSpacing = 0.sp
        ),
        semibold_12 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 18.sp, // 150%
            letterSpacing = 0.8.sp
        ),
        regular_12 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.8.sp
        ),
        semibold_10 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            lineHeight = 15.sp, // 150%
            letterSpacing = 1.sp
        ),
        regular_10 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            letterSpacing = 1.sp
        )
    )
)