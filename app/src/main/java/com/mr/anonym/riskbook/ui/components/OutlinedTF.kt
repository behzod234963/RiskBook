package com.mr.anonym.riskbook.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.riskbook.R

@Composable
fun OutlinedTF(
    modifier: Modifier,
    containerColor: Color,
    textColor: Color,
    borderColor: Color,
    fontFamily: FontFamily,
    isNumberField:Boolean = true,
    showTrailingIcon: Boolean,
    isSingleLine: Boolean,
    value: String,
    onValueChange:(String)-> Unit,
    label: String,
    onTrailingClick:()-> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            unfocusedBorderColor = borderColor,
            focusedBorderColor = borderColor,
            focusedLabelColor = borderColor,
            unfocusedLabelColor = borderColor
        ),
        textStyle = TextStyle(
            color = textColor,
            fontSize = 16.sp,
            fontFamily = fontFamily
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = if( isNumberField ) {
                KeyboardType.Number
            }else{
                KeyboardType.Text
            }
        ),
        shape = RoundedCornerShape(20.dp),
        trailingIcon = {
            if ( showTrailingIcon ){
                IconButton(
                    onClick = { onTrailingClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        tint = borderColor,
                        contentDescription = ""
                    )
                }
            }
        },
        singleLine = isSingleLine,
        label = {
            Text( text = label )
        }
    )
}