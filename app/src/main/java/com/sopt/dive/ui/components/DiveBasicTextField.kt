package com.sopt.dive.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.ui.model.TextFieldValidState
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun DiveBasicTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    textFieldValidType: TextFieldValidState = TextFieldValidState.DEFAULT,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = DiveTheme.typography.body.semibold_18,
            color = DiveTheme.colors.gray600
        )
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.contains("\n").not()) {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = textFieldValidType.color,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp),
            textStyle = DiveTheme.typography.caption.regular_12.copy(
                color = DiveTheme.colors.gray600
            ),
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            singleLine = (maxLines == 1),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box (
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = DiveTheme.typography.caption.regular_12,
                                color = DiveTheme.colors.gray400
                            )
                        }
                        innerTextField()
                    }
                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        trailingIcon?.invoke()
                    }
                }
            }
        )
        if (textFieldValidType is TextFieldValidState.INVALID) {
            Text(
                text = stringResource(textFieldValidType.messageResId),
                color = textFieldValidType.color,
                style = DiveTheme.typography.caption.regular_10
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreview() {
    var id by remember { mutableStateOf("") }

    DiveTheme {
        DiveBasicTextField(
            label = "ID",
            value = id,
            onValueChange = { id = it },
            placeholder = "아이디를 입력해주세요",
            imeAction = ImeAction.Next,
        )
    }
}
