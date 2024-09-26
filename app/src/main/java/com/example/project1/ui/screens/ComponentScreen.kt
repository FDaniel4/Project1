package com.example.project1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch


@Composable
fun ComponentScreen(navController: NavController) {
    var component by remember { mutableStateOf("") } //Actualiza el valor de la variable
    val drawerSate = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerSate, //current state of drawer
        //drawer content
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Drawer title", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Content 1") },
                    selected = false,
                    onClick = {
                        component = "Content 1"
                        scope.launch {
                            drawerSate.apply {
                                close()
                            }
                        }
                    }
                )
                //Content2
                NavigationDrawerItem(
                    label = { Text(text = "Content 2") },
                    selected = false,
                    onClick = {
                        component = "Content 2"
                        scope.launch {
                            drawerSate.apply {
                                close()
                            }
                        }
                    }
                )
            }
        }
    ) {

    }
    Column {
        when (component) {
            "Content 1" -> content1()
            "Content 2" -> content2()
        }
    }
}

@Composable
fun content1(){
    Text(text = "Hola 1")
}

@Composable
fun content2(){
    Text(text = "Hola 2")
}