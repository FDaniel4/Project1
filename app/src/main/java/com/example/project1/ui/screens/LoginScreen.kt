package com.example.project1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project1.data.controller.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project1.data.controller.LoginState

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel() // Vinculamos el ViewModel
) {
    // Observamos el estado del login
    val loginState by viewModel.loginState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        LoginForm(navController, viewModel, loginState)
    }
}

@Composable
fun LoginForm(navController: NavController, viewModel: LoginViewModel, loginState: LoginState) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Card(
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.DarkGray
        ),
        modifier = Modifier.padding(40.dp, 0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            AsyncImage(
                model = "https://logodownload.org/wp-content/uploads/2019/08/github-logo.png",
                contentDescription = "Github logo",
                contentScale = ContentScale.Fit
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                value = username,
                onValueChange = { username = it },
                label = { Text("User", color = Color.White) }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                visualTransformation = PasswordVisualTransformation()
            )

            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = {
                    viewModel.login(username, password)
                }
            ) {
                Text("LOG IN")
            }

            errorMessage?.let {
                Text(it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = {
                    navController.navigate("home")
                }
            ) {
                Text("Log in without internet")
            }

            when (loginState) {
                is LoginState.Idle -> Text("Por favor ingresa las credenciales correctas")
                is LoginState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is LoginState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate("home")
                    }
                }
                is LoginState.Error -> Text(
                    text = (loginState as LoginState.Error).message,
                    color = Color.White,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}

