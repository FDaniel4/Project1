package com.example.projecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import androidx.navigation.compose.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project1.ui.screens.ComponentScreen
import com.example.project1.ui.screens.HomeScreen
import com.example.project1.ui.screens.MenuScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ComposeMultiScreenApp()

//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
//
//            ) {
////                Text(text = "Hola Android!")
////                ModifierExample()
////                ModifierExample2()
////                ModifierExample3()
//                CustomText()
//                Picture()
//                Content1()
//            }

//para hacer push

            //layouts
//            Column {
//                Text(text = "First Row")
//                Text(text = "Second Row")
//                Text(text = "Third Row")
//                Row {
//                    Text(text = "Text 1")
//                    Text(text = "Text 2")
//                    Text(text = "Text 3")
//                    Text(text = "Text 4")
//                    Text(text = "Text 5")
//                    Text(text = "Text 6")
//                }
//                Box{
//                    Text(text = "BoxText 1")
//                    Text(text = "BoxText 2")
//                }
//                Greeting(name = "World")
//            }
//test

//            Project1Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
        }
    }
}
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Project1Theme {
        Greeting("Daniel")
    }
}

//@Preview(showBackground = true)
@Composable
fun ModifierExample() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .clickable(onClick = { clickAction() })
    ) {
        Text(text = "Hola Android!")
    }
}

//@Preview(showBackground = true)
@Composable
fun ModifierExample2() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clickable(onClick = { clickAction() })
    ) {
        Text(text = "Hola Android!")
    }
}

//@Preview(showBackground = true)
@Composable
fun ModifierExample3() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clickable(onClick = { clickAction() })
            .padding(16.dp)
            .background(Color.Cyan)
            .border(width = 2.dp, Color.Green)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
        Text(text = "Item 4")
        Text(text = "Item 5")
        Text(text = "Item 6")
    }
}

//@Preview(showBackground = true)
@Composable
fun CustomText() {
    Column {
        Text(
            stringResource(R.string.hello_world_text),
            color = colorResource(R.color.purple_200),
            fontSize = 28.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradientColors = listOf(Cyan, Blue)
        Text(
            stringResource(R.string.hello_world_text),
            style = TextStyle(brush = Brush.linearGradient(colors = gradientColors))
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun Picture() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo Andorid",
            contentScale = ContentScale.Crop
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun Content1() {
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            text = "This is a title",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "ANDROID LOGO",
            contentScale = ContentScale.Crop
        )
        Text(
            stringResource(id = R.string.text_card),
            textAlign = TextAlign.Justify,
            lineHeight = 18.sp,
            modifier = Modifier
                .padding(10.dp)
        )


    }
}

//@Preview(showBackground = true)
@Composable
fun Content2() {
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row {
            Column (verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()) {
                Image(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(0.dp, 10.dp),

                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "ANDROID LOGO",
                    contentScale = ContentScale.Fit
                )
            }
            Column {
                Text(
                    text = "This is a title",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Text(
                    stringResource(id = R.string.text_card),
                    textAlign = TextAlign.Justify,
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    maxLines = 10,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxExample1(){
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxWidth()
            .padding(5.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "ANDROID",
            contentScale = ContentScale.FillBounds
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                Icons.Filled.AccountBox,
                contentDescription = "Acount Box"
            )
            Text(
                text = "Text",

                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxExample2(){
    Box(
        modifier = Modifier
            .background(Color.Magenta)
            .padding(5.dp)
            .size(275.dp)
    ){
        Text(
            text = "TopStart",
            Modifier.align(Alignment.TopStart)
        )
        Text(
            text = "TopCenter",
            Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = "TopEnd",
            Modifier.align(Alignment.TopEnd)
        )
        Text(
            text = "CenterStart",
            Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = "Center",
            Modifier.align(Alignment.Center)
        )
        Text(
            text = "CenterEnd",
            Modifier.align(Alignment.CenterEnd)
        )
        Text(
            text = "BottomStart",
            Modifier.align(Alignment.BottomStart)
        )
        Text(
            text = "BottomCenter",
            Modifier.align(Alignment.BottomCenter)
        )
        Text(
            text = "BottomEnd",
            Modifier.align(Alignment.BottomEnd)
        )
    }
}
//git test2

fun clickAction(element: String = "Elemento") {
    println("$element Clicked")
}
*/

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
    NavHost(navController = navController, startDestination = "menu"){
        composable("menu"){ MenuScreen(navController) }
        composable("home"){ HomeScreen(navController) }
        composable("components"){ ComponentScreen(navController) }

    }
}