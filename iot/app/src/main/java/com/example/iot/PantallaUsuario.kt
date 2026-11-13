package com.example.iot


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaUsuario(onCerrarSesion: () -> Unit = {}) {

    // Paleta de colores
    val colorFondo = Color(0xFFF5F6F8)
    val colorOscuro = Color(0xFF152530)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        // panel de usuario
        Encabezado("Usuario")

        Spacer(modifier = Modifier.height(14.dp))

        // conexión bluetooth
        TarjetaBluetooth()
        Spacer(modifier = Modifier.height(16.dp))

        // tarjeta de temperatura
        TarjetaTemperatura()
        Spacer(modifier = Modifier.height(22.dp))

        // sección de control rápido
        ControlRapido()

        Spacer(modifier = Modifier.height(22.dp))

        // Estado del sistema
        EstadoSistema()
        Spacer(modifier = Modifier.height(27.dp))

        //botón para cerrar sesión
        CerrarSesion(onClick = onCerrarSesion)
    }
}

@Preview(showBackground = true, heightDp = 920)
@Composable
fun VistaPreviaUsuario() {
    PantallaUsuario()
}