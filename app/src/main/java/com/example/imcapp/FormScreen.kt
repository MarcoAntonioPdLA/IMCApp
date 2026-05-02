package com.example.imcapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.imcapp.ui.theme.Typography



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
                        fontSize = 14.sp,
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
                    valueRange = 50f..200f,
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

        //Elección de peso y edad
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //Elección de peso
            NumberSelector(
                modifier = Modifier.weight(1f),
                tag = "Peso (kg)",
                value = weight,
                onMinus = {
                    if (weight > 1) weight--
                },
                onPlus = {
                    if (weight < 200) weight++
                }
            )
            //Elección de edad
            NumberSelector(
                modifier = Modifier.weight(1f),
                tag = "Edad",
                value = age,
                onMinus = {
                    if (age > 1) age--
                },
                onPlus = {
                    if (age < 100) age++
                }
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Routes.resultScreen + "/$genderSelectedIndex/$height/$weight/$age")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Purple)
        ) {
            Text(
                text = "CALCULAR",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun NumberSelector(modifier: Modifier = Modifier, tag: String, value: Int, onMinus: () -> Unit, onPlus: () -> Unit) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = VeryLightGray),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = tag,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = value.toString(),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FilledIconButtonSmall(symbol = "−", onClick = onMinus)
                FilledIconButtonSmall(symbol = "+", onClick = onPlus)
            }
        }
    }
}

@Composable
fun FilledIconButtonSmall(symbol: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(50))
            .background(LightPurple)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Purple
        )
    }
}