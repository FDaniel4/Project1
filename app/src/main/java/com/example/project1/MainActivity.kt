package com.example.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project1.ui.screens.ComponentScreen
import com.example.project1.ui.screens.HomeScreen
import com.example.project1.ui.screens.LoginScreen
import com.example.project1.ui.screens.MenuScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMultiScreenApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeMultiScreenApp(){
    val navController = rememberNavController()
    Surface (color = Color.White) {
        SetupNavGraph(navController = navController)
    }
}

@Composable
fun SetupNavGraph (navController: NavHostController){
    NavHost(navController = navController, startDestination = "Login"){
        composable("menu"){ MenuScreen(navController) }
        composable("home"){ HomeScreen(navController) }
        composable("components"){ ComponentScreen(navController) }
        composable("Login"){ LoginScreen(navController) }

    }
}