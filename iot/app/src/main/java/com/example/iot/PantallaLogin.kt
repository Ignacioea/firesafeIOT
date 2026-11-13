package com.example.iot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaLogin(onLoginExitoso: (String)-> Unit){
    //paleta de colores
    val colorFondo = Color(0xFFF5F6F8)
    val colorNaranja = Color(0xFFE8632B)
    val colorTarjetaOscura = Color(0xFF152530)
    val colorTextoSecundario = Color(0xFF8A949E)

    //variables para almacenar lo que escribe el usuario
    var correo by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("")}
    var mensajeError by rememberSaveable{mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(50.dp))

        //Encabezado
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(colorNaranja, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center

            ){
                Image(
                    painter = painterResource(id= R.drawable.icono_iot),
                    contentDescription = "Logo FireSafe",
                    modifier = Modifier.size(46.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            Column{
                Text(
                    text = "FireSafe",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorTarjetaOscura,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
        Spacer(modifier = Modifier.height(32.dp))


        //campo de correo electrónico
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Correo electrónico",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorTarjetaOscura
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    mensajeError = ""
                },
                placeholder = {Text("nombre@correo.com", color=colorTextoSecundario)},
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = colorNaranja,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        //campo pa la contraseña
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Contraseña",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorTarjetaOscura
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    mensajeError = ""
                },
                placeholder = { Text("Ingresa tu contraseña", color = colorTextoSecundario) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = colorNaranja,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        //mensaje de error si falla el login
        if (mensajeError.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = mensajeError,
                color = MaterialTheme.colorScheme.error,
                fontSize = 13.sp
            )
        }
        Spacer(modifier = Modifier.height(28.dp))

        //boton naranja
        Button(
            onClick = {
                val correoLimpio = correo.trim().lowercase()
                val passLimpia = password.trim()

                //validacion de campos vacios
                if (correoLimpio.isEmpty() || passLimpia.isEmpty()) {
                    mensajeError = "Por favor completa tu correo y contraseña"
                    return@Button
                }

                //Evaluacion de 3 cuentas y asignación de rol

                val rolDetectado = when {
                    correoLimpio == "user@firesafe.com" && passLimpia == "user1234" -> "USUARIO"
                    correoLimpio == "admin@firesafe.com" && passLimpia == "admin1234" -> "ADMINISTRADOR"
                    correoLimpio == "system@firesafe.com" && passLimpia == "system1234" -> "SISTEMA"
                    else -> null
                }

                //rol detectado
                if (rolDetectado != null) {
                    onLoginExitoso(rolDetectado)
                } else{
                    mensajeError = "correo o contraseña incorrectos"
                }
            },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorNaranja),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ){
            Text(
                text = "Iniciar sesión",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
