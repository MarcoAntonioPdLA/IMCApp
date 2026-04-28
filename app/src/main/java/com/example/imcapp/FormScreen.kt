package com.example.imcapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.imcapp.ui.theme.Typography

@Composable
fun FormScreen(navController: NavController) {
    var genderSelectedIndex: Int by remember { mutableIntStateOf(0) }
    val genderOptions = listOf("Hombre", "Mujer")
    var height: Int by remember { mutableIntStateOf(150) }
    var weight: Int by remember { mutableIntStateOf(50) }
    var age: Int by remember { mutableIntStateOf(15) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            Text(
                text = "Índice de Masa Corporal",
                style = Typography.headlineLarge
            )
            Text(
                text = "Ingrese y seleccione la siguiente información:",
                style = Typography.bodyLarge
            )
        }

        // Elección de género
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            genderOptions.forEachIndexed { index, label ->
                SegmentedButton(
                    selected = index == genderSelectedIndex,
                    onClick = {
                        genderSelectedIndex = index
                    },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = genderOptions.size
                    )
                ) {
                    Text(label)
                }
            }
        }

        //Elección de altura
        Column {
            Text(
                text = "Altura: $height cm",
                style = Typography.bodyLarge
            )
            Slider(
                value = height.toFloat(),
                onValueChange = { height = it.toInt() },
                valueRange = 30f..250f,
                modifier = Modifier.fillMaxWidth()
            )
        }

        //Elección de peso


        //Elección de edad

    }
}
