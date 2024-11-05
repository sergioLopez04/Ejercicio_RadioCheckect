package com.example.radiobutton_checkboxetc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.radiobutton_checkboxetc.ui.theme.RadioButton_checkboxetcTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadioButton_checkboxetcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var mensaje by remember { mutableStateOf("") }
    var mostrarProgreso by remember { mutableStateOf(false) }
    var checkActivado by remember { mutableStateOf(false) }
    var switchActivado by remember { mutableStateOf(false) }
    var opcionSeleccionada by remember { mutableStateOf("") }
    var imagenActual by remember { mutableStateOf(0) }


    val imagenes = listOf(
        painterResource(id = R.drawable.images),
        painterResource(id = R.drawable.imagen2),
        painterResource(id = R.drawable.imagen3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .background(Color.Magenta)
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                icono()

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkActivado,
                        onCheckedChange = { checkActivado = it }
                    )
                    Text("Activar")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (checkActivado) {
                    Text(text = mensaje)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    mostrarProgreso = true
                }) {
                    Text("Presionar")
                }

                if (mostrarProgreso) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator()

                    LaunchedEffect(Unit) {
                        delay(5000)
                        mostrarProgreso = false
                    }
                }

            }


        }

        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Column {
                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Switch(
                        checked = switchActivado,
                        onCheckedChange = { switchActivado = it }
                    )
                    Text("Mostrar opciones")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (switchActivado) {
                    Column {
                        RadioButtonGroup(
                            opciones = listOf("Opción 1", "Opción 2", "Opción 3"),
                            opcionSeleccionada = opcionSeleccionada,
                            onOpcionSeleccionada = {
                                opcionSeleccionada = it
                                mensaje = it
                            }
                        )
                    }
                }
            }

        }

        Row(
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = imagenes[imagenActual],
                    contentDescription = "Imagen",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    imagenActual = (imagenActual + 1) % imagenes.size
                }) {
                    Text("Cambiar imagen")
                }

            }


        }


    }
}

@Composable
fun RadioButtonGroup(
    opciones: List<String>,
    opcionSeleccionada: String,
    onOpcionSeleccionada: (String) -> Unit
) {
    Column {
        opciones.forEach { opcion ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (opcion == opcionSeleccionada),
                    onClick = { onOpcionSeleccionada(opcion) }
                )
                Text(opcion)
            }
        }
    }
}

@Composable
fun icono() {

    Icon(
        imageVector = Icons.Default.Face,
        contentDescription = "Icono",
        tint = Color.Black,
        modifier = Modifier.size(30.dp)

    )


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RadioButton_checkboxetcTheme {
        Greeting("Android")
    }
}