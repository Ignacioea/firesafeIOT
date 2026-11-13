package com.example.iot

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun PantallaAdmin(onCerrarSesion: () -> Unit = {}) {

    // colores
    val colorFondo = Color(0xFFF5F6F8)
    val colorOscuro = Color(0xFF152530)
    val colorBordeSuave = Color(0xFFE2E8F0)

    // Estados para las ventanas emergentes de Administración
    var umbralAlarma by remember { mutableIntStateOf(45) }
    var mostrarConfig by remember { mutableStateOf(false) }
    var mostrarHistorial by remember { mutableStateOf(false) }
    var mostrarCuentas by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        // Título
        Encabezado("Administrador")
        Spacer(modifier = Modifier.height(14.dp))

        //conexión bluetooth
        TarjetaBluetooth()
        Spacer(modifier = Modifier.height(16.dp))

        // tarjeta de temperatura
        TarjetaTemperatura()
        Spacer(modifier = Modifier.height(22.dp))

        // control rápido
        ControlRapido()
        Spacer(modifier = Modifier.height(22.dp))

        //estado del sistema
        EstadoSistema()
        Spacer(modifier = Modifier.height(27.dp))

        // 5. SECCIÓN de administración
        Text(
            text = "Administración",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = colorOscuro
        )

        Spacer(modifier = Modifier.height(12.dp))

        //configuración
        Surface(
            onClick = { mostrarConfig = true },
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, colorBordeSuave),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.configuraciones),
                    contentDescription = "Gestión de cuentas",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "configuración",
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

        //historial
        Surface(
            onClick = { mostrarHistorial = true },
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, colorBordeSuave),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reloj),
                    contentDescription = "Gestión de cuentas",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Historial",
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

        //gestión de cuentas
        Surface(
            onClick = { mostrarCuentas = true },
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, colorBordeSuave),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.people),
                    contentDescription = "Gestión de cuentas",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Gestión de cuentas",
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

        // cerrar sesión
        CerrarSesion (onClick = onCerrarSesion)
        Spacer(modifier = Modifier.height(30.dp))
    }

    //Dialogos al tocar cada opción
    if (mostrarConfig) {
        AlertDialog(
            onDismissRequest = { mostrarConfig = false },
            title = { Text("Umbral de temperatura") },
            text = {
                Column {
                    Text("Aviso por alta temperatura a partir de: $umbralAlarma °C")
                    Spacer(modifier = Modifier.height(8.dp))
                    Slider(
                        value = umbralAlarma.toFloat(),
                        onValueChange = { umbralAlarma = it.toInt() },
                        valueRange = 25f..80f
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarConfig = false }) { Text("Guardar") }
            }
        )
    }

    if (mostrarHistorial) {
        AlertDialog(
            onDismissRequest = { mostrarHistorial = false },
            title = { Text("Registro de eventos") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("• 10:15 - Acceso Administrador")
                    Text("• 09:50 - Lectura normal: 28,4 °C")
                    Text("• 09:30 - Sistema iniciado sin alertas")
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarHistorial = false }) { Text("Cerrar") }
            }
        )
    }

    if (mostrarCuentas) {
        AlertDialog(
            onDismissRequest = { mostrarCuentas = false },
            title = { Text("Usuarios y roles") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("• usuario@firesafe.com — Usuario")
                    Text("• admin@firesafe.com — Administrador")
                    Text("• sistema@firesafe.com — Sistema")
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarCuentas = false }) { Text("Cerrar") }
            }
        )
    }
}

@Preview(showBackground = true, heightDp = 980)
@Composable
fun VistaPreviaAdmin() {
    PantallaAdmin()
}