package com.example.iot

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaSistema(onCerrarSesion: () -> Unit = {}) {

    // colores
    val colorFondo = Color(0xFFF5F6F8)
    val colorOscuro = Color(0xFF152530)
    val colorBordeSuave = Color(0xFFE2E8F0)

    // Estados para las herramientas técnicas del sistema
    var offsetCalibracion by remember { mutableFloatStateOf(0.0f) }
    var anguloCompuerta by remember { mutableIntStateOf(0) }
    var mensajeLcd by remember { mutableStateOf("FireSafe Activo") }

    // Estados para abrir los diálogos al tocar cada opción
    var mostrarCalibracion by remember { mutableStateOf(false) }
    var mostrarMoverCompuerta by remember { mutableStateOf(false) }
    var mostrarCambiarTexto by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        //título
        Encabezado("Sistema")
        Spacer(modifier = Modifier.height(14.dp))

        //conexión bluetooth
        TarjetaBluetooth()
        Spacer(modifier = Modifier.height(16.dp))

        //tarjeta de temperatura
        TarjetaTemperatura()
        Spacer(modifier = Modifier.height(22.dp))

        // control rápido
        ControlRapido()
        Spacer(modifier = Modifier.height(22.dp))

        //estado del sistema
        EstadoSistema()
        Spacer(modifier = Modifier.height(27.dp))

        //Herramientas del sistema
        Text(
            text = "Herramientas del sistema",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = colorOscuro
        )

        Spacer(modifier = Modifier.height(12.dp))

        //calibrar el sensor de temperatura
        Surface(
            onClick = { mostrarCalibracion = true },
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, colorBordeSuave),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "🌡️", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "calibrar el sensor de temperatura",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorOscuro
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.flecha),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        //mover la compuerta
        Surface(
            onClick = { mostrarMoverCompuerta = true },
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, colorBordeSuave),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "⇄", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = colorOscuro)
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "mover la compuerta",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorOscuro
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.flecha),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        //cambiar texto en pantalla
        Surface(
            onClick = { mostrarCambiarTexto = true },
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, colorBordeSuave),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "🖥️", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "cambiar texto en pantalla",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorOscuro
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.flecha),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(27.dp))

        // botón para cerrar sesión
        CerrarSesion (onClick = onCerrarSesion)
        Spacer(modifier = Modifier.height(30.dp))
    }
    //DIÁLOGOS INTERACTIVOS DE HERRAMIENTAS DEL SISTEMA
    if (mostrarCalibracion) {
        AlertDialog(
            onDismissRequest = { mostrarCalibracion = false },
            title = { Text("Calibrar sensor LM35") },
            text = {
                Column {
                    Text("Compensación térmica: ${String.format("%+.1f", offsetCalibracion)} °C")
                    Spacer(modifier = Modifier.height(8.dp))
                    Slider(
                        value = offsetCalibracion,
                        onValueChange = { offsetCalibracion = it },
                        valueRange = -5f..5f
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarCalibracion = false }) { Text("Aplicar") }
            }
        )
    }

    if (mostrarMoverCompuerta) {
        AlertDialog(
            onDismissRequest = { mostrarMoverCompuerta = false },
            title = { Text("Control manual SG90") },
            text = {
                Column {
                    Text("Ángulo actual del servomotor: $anguloCompuerta°")
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(onClick = { anguloCompuerta = 0 }) { Text("0°") }
                        OutlinedButton(onClick = { anguloCompuerta = 45 }) { Text("45°") }
                        OutlinedButton(onClick = { anguloCompuerta = 90 }) { Text("90°") }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarMoverCompuerta = false }) { Text("Cerrar") }
            }
        )
    }

    if (mostrarCambiarTexto) {
        AlertDialog(
            onDismissRequest = { mostrarCambiarTexto = false },
            title = { Text("Mensaje pantalla LCD 16x2") },
            text = {
                Column {
                    Text("Máximo 16 caracteres por fila:")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = mensajeLcd,
                        onValueChange = { if (it.length <= 16) mensajeLcd = it },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarCambiarTexto = false }) { Text("Enviar") }
            }
        )
    }
}

@Preview(showBackground = true, heightDp = 980)
@Composable
fun VistaPreviaSistema() {
    PantallaSistema()
}


