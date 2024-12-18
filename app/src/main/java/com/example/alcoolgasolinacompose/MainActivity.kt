package com.example.alcoolgasolinacompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alcoolgasolinacompose.ui.theme.AlcoolGasolinaComposeTheme

class MainActivity : ComponentActivity() {
    var percentual: Double = 0.7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlcoolGasolinaComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen("Álcool ou Gasolina?")
                }
            }
        }
    }

    override fun onResume(){
        super.onResume()
        Log.d("PDM23","No onResume, $percentual")
    }
    override fun onStart(){
        super.onStart()
        Log.d("PDM23","No onResume")
    }
    override fun onPause(){
        super.onPause()
        Log.d("PDM23","No onResume")
    }
    override fun onStop(){
        super.onStop()
        Log.d("PDM23","No onResume")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("PDM23","No onResume")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(name: String) {
    var checked by remember { mutableStateOf(false) }
    var gasolina by remember { mutableStateOf("") }
    var alcool by remember { mutableStateOf("") }

    fun calculaRentabilidade() {
        val gasolinaValue = gasolina.toDoubleOrNull() ?: 0.0
        val alcoolValue = alcool.toDoubleOrNull() ?: 0.0

        checked = if (gasolinaValue > 0) {
            (alcoolValue / gasolinaValue) <= 0.75
        } else {
            false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(name) },
                modifier = Modifier.background(Color.Red)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.White)
                    .padding(32.dp)
                    .width(300.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Ícone do app",
                    modifier = Modifier.size(100.dp).padding(bottom = 16.dp)
                )
                Text(
                    text = "Gasolina ou Álcool?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                TextField(
                    value = gasolina,
                    onValueChange = {
                        gasolina = it
                        calculaRentabilidade()
                                    },
                    label = { Text("Valor da gasolina") },
                    modifier = Modifier
                        .padding(16.dp)
                )
                TextField(
                    value = alcool,
                    onValueChange = {
                        alcool = it
                        calculaRentabilidade()
                                    },
                    label = { Text("Valor do álcool") },
                    modifier = Modifier
                        .padding(16.dp)
                )
                Text(
                    text = "Rentabilidade álcool",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                {
                    Text(
                        text = "75%",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Switch(
                        checked = checked,
                        onCheckedChange = null,
                        enabled = true,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color.Blue,
                            uncheckedThumbColor = Color.Gray,
                            uncheckedTrackColor = Color.LightGray
                        )
                    )
                }
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text("CALCULAR")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen("Alcool ou Gasolina?")
}

