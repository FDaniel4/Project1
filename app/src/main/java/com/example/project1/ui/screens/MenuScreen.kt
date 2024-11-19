package com.example.project1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MenuScreen(navController: NavController){
    Column {
        Text(text = "This is the Menu Screen")
        Button(onClick = { navController.navigate("home")}) {
            Icon(Icons.Filled.Home,"")
        }
        Button(onClick = { navController.navigate("components") }) {
        }
        Button(onClick = { navController.navigate("login")}) {
            Text("Login")
        }
        Button(onClick = { navController.navigate("Camera")}) {
            Text("Camera")
        }
        Button(onClick = { navController.navigate("internet")}) {
            Text("Internet")
        }
        Button(onClick = { navController.navigate("contacts")}) {
            Text("Contacts")
        }
        Button(onClick = { navController.navigate("biometrics")}) {
            Text("Biometrics")
        }
        Button(onClick = { navController.navigate("homeMaps")}) {
            Text("Maps")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen(navController = rememberNavController())
}