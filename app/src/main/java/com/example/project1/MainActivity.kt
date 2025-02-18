package com.example.project1

//import androidx.navigation.compose.NavHostController
import android.Manifest
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.project1.data.database.AppDatabase
import com.example.project1.data.database.DatabaseProvider
import com.example.project1.ui.background.CustomWorker
import com.example.project1.ui.camera.CameraScreen
import com.example.project1.ui.contacts.ContactScreen
import com.example.project1.ui.location.viewModel.SearchViewModel
import com.example.project1.ui.location.views.HomeView
import com.example.project1.ui.location.views.MapsSearchView
import com.example.project1.ui.network.NetworkMonitor
import com.example.project1.ui.screens.ComponentScreen
import com.example.project1.ui.screens.HomeScreen
import com.example.project1.ui.screens.LoginScreen
import com.example.project1.ui.screens.ManageServiceScreen
import com.example.project1.ui.screens.MenuScreen
import java.time.Duration

class MainActivity : ComponentActivity() {
    //Internet
    // Inicializamos los objetos que vamos a usar para el monitoreo de la red
    private lateinit var wifiManager: WifiManager  // Para gestionar el Wi-Fi
    private lateinit var connectivityManager: ConnectivityManager  // Para gestionar las conexiones de red
    private lateinit var networkMonitor: NetworkMonitor  // Clase que monitorea el estado de la red

    //para la base de datos
    lateinit var database: AppDatabase

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create or load DB
        try {
            database = DatabaseProvider.getDatabase(this)
            Log.d("DB","Database created")
        }catch (e: Exception){
            Log.d("DB","ERROR: $e")
        }



        enableEdgeToEdge()

        //WorkManager

        val workRequest = OneTimeWorkRequestBuilder<CustomWorker>()
            .setInitialDelay(Duration.ofSeconds(10))
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(15)
            )
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)

        //Internet
        // Obtenemos los servicios necesarios para controlar Wi-Fi y la conectividad de red
        wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        // Creamos una instancia de NetworkMonitor, pasando los servicios y la actividad actual
        networkMonitor = NetworkMonitor(wifiManager, connectivityManager, this)
        setContent {
            val viewModel: SearchViewModel by viewModels()
            ComposeMultiScreenApp(searchVM = viewModel,this,networkMonitor)


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
    //Internet
// Función para solicitar permisos si no han sido concedidos
    fun requestPermissionsIfNeeded() {
        val permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,  // Permiso para la ubicación precisa
            Manifest.permission.ACCESS_COARSE_LOCATION  // Permiso para la ubicación aproximada
        ).filter {
            // Verificamos si alguno de los permisos no ha sido concedido
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        // Si falta algún permiso, solicitamos los permisos necesarios
        if (permissions.isNotEmpty()) {
            requestPermissionsLauncher.launch(permissions.toTypedArray())
        }
    }
    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        { permissions ->
            // Verificamos si los permisos de ubicación fueron concedidos
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                // Si los permisos son concedidos, mostramos un mensaje
                Toast.makeText(this, "Permisos necesarios concedidos", Toast.LENGTH_SHORT).show()
            } else {
                // Si no se conceden, mostramos un mensaje de error
                Toast.makeText(this, "Permisos necesarios no concedidos", Toast.LENGTH_SHORT).show()
            }
        }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Project1Theme {
//        Greeting("Daniel")
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ModifierExample() {
//    Column(
//        modifier = Modifier
//            .padding(24.dp)
//            .clickable(onClick = { clickAction() })
//    ) {
//        Text(text = "Hola Android!")
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ModifierExample2() {
//    Column(
//        modifier = Modifier
//            .padding(24.dp)
//            .fillMaxWidth()
//            .clickable(onClick = { clickAction() })
//    ) {
//        Text(text = "Hola Android!")
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ModifierExample3() {
//    Column(
//        modifier = Modifier
//            .fillMaxHeight()
//            .clickable(onClick = { clickAction() })
//            .padding(16.dp)
//            .background(Color.Cyan)
//            .border(width = 2.dp, Color.Green)
//            .width(200.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceEvenly
//    ) {
//        Text(text = "Item 1")
//        Text(text = "Item 2")
//        Text(text = "Item 3")
//        Text(text = "Item 4")
//        Text(text = "Item 5")
//        Text(text = "Item 6")
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun CustomText() {
//    Column {
//        Text(
//            stringResource(R.string.hello_world_text),
//            color = colorResource(R.color.purple_200),
//            fontSize = 28.sp,
//            fontWeight = FontWeight.ExtraBold
//        )
//        val gradientColors = listOf(Cyan, Blue)
//        Text(
//            stringResource(R.string.hello_world_text),
//            style = TextStyle(brush = Brush.linearGradient(colors = gradientColors))
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun Picture() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.Black)
//    ) {
//        Image(
//            modifier = Modifier
//                .fillMaxWidth(),
//            painter = painterResource(R.drawable.logo),
//            contentDescription = "Logo Andorid",
//            contentScale = ContentScale.Crop
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun Content1() {
//    Card(
//        modifier = Modifier
//            .background(Color.LightGray)
//            .fillMaxWidth()
//            .padding(5.dp)
//    ) {
//        Text(
//            text = "This is a title",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .padding(10.dp)
//        )
//        Image(
//            modifier = Modifier.fillMaxWidth(),
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "ANDROID LOGO",
//            contentScale = ContentScale.Crop
//        )
//        Text(
//            stringResource(id = R.string.text_card),
//            textAlign = TextAlign.Justify,
//            lineHeight = 18.sp,
//            modifier = Modifier
//                .padding(10.dp)
//        )
//
//
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun Content2() {
//    Card(
//        modifier = Modifier
//            .background(Color.LightGray)
//            .fillMaxWidth()
//            .padding(5.dp)
//    ) {
//        Row {
//            Column (verticalArrangement = Arrangement.Center,
//                modifier = Modifier.fillMaxHeight()) {
//                Image(
//                    modifier = Modifier
//                        .width(200.dp)
//                        .padding(0.dp, 10.dp),
//
//                    painter = painterResource(id = R.drawable.logo),
//                    contentDescription = "ANDROID LOGO",
//                    contentScale = ContentScale.Fit
//                )
//            }
//            Column {
//                Text(
//                    text = "This is a title",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .padding(10.dp)
//                )
//                Text(
//                    stringResource(id = R.string.text_card),
//                    textAlign = TextAlign.Justify,
//                    fontSize = 11.sp,
//                    lineHeight = 11.sp,
//                    maxLines = 10,
//                    modifier = Modifier
//                        .padding(10.dp)
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun BoxExample1(){
//    Box(
//        modifier = Modifier
//            .background(Color.DarkGray)
//            .fillMaxWidth()
//            .padding(5.dp)
//    ){
//        Image(
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "ANDROID",
//            contentScale = ContentScale.FillBounds
//        )
//        Row (
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(0.dp, 10.dp),
//            horizontalArrangement = Arrangement.Center
//        ){
//            Icon(
//                Icons.Filled.AccountBox,
//                contentDescription = "Acount Box"
//            )
//            Text(
//                text = "Text",
//
//                )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun BoxExample2(){
//    Box(
//        modifier = Modifier
//            .background(Color.Magenta)
//            .padding(5.dp)
//            .size(275.dp)
//    ){
//        Text(
//            text = "TopStart",
//            Modifier.align(Alignment.TopStart)
//        )
//        Text(
//            text = "TopCenter",
//            Modifier.align(Alignment.TopCenter)
//        )
//        Text(
//            text = "TopEnd",
//            Modifier.align(Alignment.TopEnd)
//        )
//        Text(
//            text = "CenterStart",
//            Modifier.align(Alignment.CenterStart)
//        )
//        Text(
//            text = "Center",
//            Modifier.align(Alignment.Center)
//        )
//        Text(
//            text = "CenterEnd",
//            Modifier.align(Alignment.CenterEnd)
//        )
//        Text(
//            text = "BottomStart",
//            Modifier.align(Alignment.BottomStart)
//        )
//        Text(
//            text = "BottomCenter",
//            Modifier.align(Alignment.BottomCenter)
//        )
//        Text(
//            text = "BottomEnd",
//            Modifier.align(Alignment.BottomEnd)
//        )
//    }
//}
////git test2
//
//fun clickAction(element: String = "Elemento") {
//    println("$element Clicked")
//}



@Composable
fun ComposeMultiScreenApp(searchVM: SearchViewModel, activity: MainActivity, networkMonitor: NetworkMonitor){
    val navController = rememberNavController()
    Surface (color = Color.White) {
        SetupNavGraph(navController =navController,searchVM,activity,networkMonitor)
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController, searchVM: SearchViewModel, activity: MainActivity, networkMonitor: NetworkMonitor){
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "menu"){
        composable("menu"){ MenuScreen(navController) }
        composable("home"){ HomeScreen(navController) }
        composable("components"){ ComponentScreen(navController) }
        composable("login"){ LoginScreen(navController)}
        composable("manage-service/{serviceId}"){backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")
            ManageServiceScreen(navController, serviceId = serviceId)

        }
        composable("Camera"){ CameraScreen(context = context,navController)}
        composable("internet"){networkMonitor.NetworkMonitorScreen(navController)}

        composable("contacts"){ ContactScreen(navController = navController) }

        //Biometricos
        //composable("biometrics"){ BiometricsScreen(navController = navController, activity = activity)}

        // Ruta para `MapsSearchView` que recibe latitud, longitud y dirección como argumentos
        composable("homeMaps"){ HomeView(navController = navController, searchVM = searchVM)}
        composable("MapsSearchView/{lat}/{long}/{address}", arguments = listOf(
            navArgument("lat") { type = NavType.FloatType },
            navArgument("long") { type = NavType.FloatType },
            navArgument("address") { type = NavType.StringType }
        )) {
            // Obtención de los argumentos con valores predeterminados en caso de que falten
            val lat = it.arguments?.getFloat("lat") ?: 0.0
            val long = it.arguments?.getFloat("long") ?: 0.0
            val address = it.arguments?.getString("address") ?: ""
            MapsSearchView(lat.toDouble(), long.toDouble(), address )
        }
    }
}