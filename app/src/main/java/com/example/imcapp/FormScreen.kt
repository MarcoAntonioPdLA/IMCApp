package com.example.imcapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.imcapp.ui.theme.Typography

val VeryLightGray = Color(0xFFF0EEF6)
val Purple = Color(0xFF4C12FF)

@OptIn(ExperimentalMaterial3Api::class)
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
        Column {
            Text(
                text = "Género:",
                style = Typography.bodyMedium
            )
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
        }

        //Elección de altura
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = VeryLightGray),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Altura",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            modifier = Modifier.alignByBaseline(),
                            text = height.toString(),
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = Purple
                        )
                        Text(
                            modifier = Modifier.alignByBaseline(),
                            text = " cm",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }
                Slider(
                    value = height.toFloat(),
                    onValueChange = { height = it.toInt() },
                    valueRange = 30f..220f,
                    thumb = {
                        Box(
                            modifier = Modifier.size(24.dp).clip(CircleShape).background(Purple)
                        )
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = Purple,
                        activeTrackColor = Color.LightGray,
                        inactiveTrackColor = Color.LightGray
                    )
                )
            }
        }

        //Elección de peso

        //Elección de edad


    }
}