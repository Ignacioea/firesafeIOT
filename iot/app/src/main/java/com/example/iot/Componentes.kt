package com.example.iot

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*

//colores
val colorBordeSuave = Color(0xFFE2E8F0)
val colorOscuro = Color(0xFF152530)
val colorTextoGris = Color(0xFF8A949E)
val colorVerdeTexto = Color(0xFFC8E6C9)

val colorNaranja = Color(0xFFE8632B)




@Composable
fun Encabezado(titulo: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Panel de "+ titulo,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF152530)
        )
    }
}
@Composable
fun TarjetaBluetooth(){
    val colorBordeSuave = Color(0xFFE2E8F0)
    Surface(
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
                painter = painterResource(id = R.drawable.bluetooth),
                contentDescription = "Bluetooth",
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Bluetooth",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorOscuro
                )
                Text(
                    text = "Arduino desconectado",
                    fontSize = 12.sp,
                    color = colorTextoGris
                )
            }
            Image(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun TarjetaTemperatura(){
    Card(
        colors = CardDefaults.cardColors(containerColor = colorOscuro),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(22.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "TEMPERATURA ACTUAL",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFB0BEC5),
                    letterSpacing = 1.sp
                )
                Text(
                    text = "🌡️",
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Temperatura gigante
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "28,4",
                    fontSize = 54.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 54.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "°C",
                    fontSize = 24.sp,
                    color = Color(0xFFB0BEC5),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Etiqueta "Rango seguro"
            Surface(
                color = Color(0xFF233B42),
                shape = RoundedCornerShape(50)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Rango seguro",
                        color = colorVerdeTexto,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun ControlRapido() {

    // Estados para controlar si están activas
    var alarmaEncendida by remember { mutableStateOf(false) }
    var compuertasAbiertas by remember { mutableStateOf(false) }

    Text(
        text = "Control rápido",
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = colorOscuro
    )

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Botón Alarma
        Surface(
            onClick = { alarmaEncendida = !alarmaEncendida },
            color = if (alarmaEncendida) colorNaranja else Color.White,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, if (alarmaEncendida) colorNaranja else colorBordeSuave),
            modifier = Modifier.weight(1f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.alerta),
                    contentDescription = "Alarma",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Alarma",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (alarmaEncendida) Color.White else colorOscuro
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (alarmaEncendida) "Encendida, presione para apagar" else "Apagada",
                    fontSize = 12.sp,
                    color = if (alarmaEncendida) Color(0xFFFFE0D3) else colorTextoGris
                )
            }
        }

        // Botón Compuertas (también cambia a naranja al tocarlo)
        Surface(
            onClick = { compuertasAbiertas = !compuertasAbiertas },
            color = if (compuertasAbiertas) colorNaranja else Color.White,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, if (compuertasAbiertas) colorNaranja else colorBordeSuave),
            modifier = Modifier.weight(1f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ventilacion),
                    contentDescription = "Compuertas",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "compuertas",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (compuertasAbiertas) Color.White else colorOscuro
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (compuertasAbiertas) "Abiertas, presione para cerrar" else "Cerradas",
                    fontSize = 12.sp,
                    color = if (compuertasAbiertas) Color(0xFFFFE0D3) else colorTextoGris
                )
            }
        }
    }
}
@Composable
fun EstadoSistema(){
    Text(
        text = "Estado del sistema",
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = colorOscuro
    )

    Spacer(modifier = Modifier.height(12.dp))

    Surface(
        color = Color.White,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, colorBordeSuave),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.escudo),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Todo bajo control",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorOscuro
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "El sensor está monitoreando la temperatura correctamente.",
                fontSize = 13.sp,
                color = colorTextoGris
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Aviso por alta temperatura a partir de 45 °C.",
                fontSize = 11.sp,
                color = colorTextoGris
            )
        }
    }
}

//CERRAR SESIÓN
@Composable
fun CerrarSesion(onClick: () -> Unit){
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, Color(0xFFD32F2F)),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color(0xFFD32F2F)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = "Cerrar sesión",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}