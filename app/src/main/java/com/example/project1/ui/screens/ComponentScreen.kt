package com.example.project1.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.project1.R
import com.example.project1.data.model.MenuModel
import com.example.project1.data.model.PostModel
import com.example.project1.ui.components.PostCard
import com.example.project1.ui.components.PostCardCompact
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun ComponentScreen(navController: NavController) {
    val menuOptions = arrayOf(
        MenuModel(1, "Buttons", "Buttons", Icons.Filled.Menu),
        MenuModel(2, "Floating Buttons", "FloatingButtons", Icons.Filled.DateRange),
        MenuModel(3, "Chips", "Chips", Icons.Filled.Menu),
        MenuModel(4, "Progress", "Progress", Icons.Filled.DateRange),
        MenuModel(5, "Sliders", "Sliders", Icons.Filled.Menu),
        MenuModel(6, "Switches", "Switches", Icons.Filled.DateRange),
        MenuModel(7, "Badges", "Badges", Icons.Filled.Menu),
        MenuModel(8, "Date Picker", "date-pickers", Icons.Filled.DateRange),
        MenuModel(9, "Time Pickers", "time-pickers", Icons.Filled.Menu),
        MenuModel(10, "Snack Bars", "snack-bar", Icons.Filled.DateRange),
        MenuModel(11, "Alert Dialogs", "alert-dialogs", Icons.Filled.Menu),
        MenuModel(12, "Bars", "bars", Icons.Filled.DateRange),
        MenuModel(13, "Adaptive", "adaptive", Icons.Filled.DateRange),

        )
    var component by rememberSaveable { mutableStateOf("") } //Actualiza el valor de la variable
    val drawerSate = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerSate, //current state of drawer
        //drawer content
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Menu", modifier = Modifier.padding(16.dp))
                HorizontalDivider()

                LazyColumn {
                    items(menuOptions) {item ->
                        NavigationDrawerItem(
                            icon = {(Icon(item.icon, contentDescription = ""))},
                            label = {Text(text = item.title)},
                            selected = false,
                            onClick = {
                                component = item.option
                                scope.launch {
                                    drawerSate.apply {
                                        close()
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {

    }
    Column {
        when (component) {
            "Content 1" -> content1()
            "Content 2" -> content2()
            "Buttons" -> Buttons()
            "FloatingButtons" -> FloatingButtons()
            "Chips" -> Chips()
            "Progress" -> Progress()
            "Sliders" -> Sliders()
            "Switches" -> Switches()
            "Badges" -> Badges()
            "date-pickers" -> DatePickers()
            "time-pickers" -> {
                TimePickers(onConfirm = { hour, minute ->
                    println("Tiempo seleccionado: $hour:$minute")
                },
                    onDismiss = {
                        println("TimePicker descartado")
                    })
            }
            "snack-bar" -> SnackBars()
            "alert-dialogs" -> AlertDialogs()
            "bars" -> Bars()
            "adaptive" -> Adaptive()
            //rutas para las demás pantallas
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

@Preview(showBackground = true)
@Composable
fun Buttons(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.
        fillMaxSize()
    ){
        Button(onClick = {}){
            Text(text = "Filled")
        }
        FilledTonalButton(onClick = {}){
            Text(text = "Tonal")
        }
        OutlinedButton(onClick = {}){
            Text(text = "Outlined")
        }
        ElevatedButton(onClick = {}){
            Text(text = "Elevated")
        }
        TextButton(onClick = {}) {
            Text(text = "Text")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun FloatingButtons() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add,"")
        }
        SmallFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add,"")
        }
        LargeFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add,"")
        }
        ExtendedFloatingActionButton(
            onClick = {},
            icon = {Icon(Icons.Filled.Add, "")},
            text = {Text("Extended FAB")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Chips() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        AssistChip(
            onClick = {},
            label = { Text(text = "Assist Chips") },
            leadingIcon = {
                Icon(Icons.Filled.AccountBox,"",
                    Modifier.size(AssistChipDefaults.IconSize))
            }
        )
        var selected by remember { mutableStateOf(false)}
        FilterChip(
            selected = selected,
            onClick = {  },
            label = { Text(text = "Filter chip") },
            leadingIcon =
            if (selected) {
                {
                    Icon(
                        Icons.Filled.AccountBox, "",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            } else {
                null
            }
        )
        InputChipsExample(text = "Dismiss", {})

    }
}


@Composable
fun InputChipsExample(
    text: String,
    onDismiss: () -> Unit
){
    var enabled by remember { mutableStateOf(true)}
    if (!enabled) return

    InputChip(
        label = { Text(text) },
        selected = enabled,
        onClick = { onDismiss()
            enabled = !enabled
        },
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "",
                Modifier.size(InputChipDefaults.AvatarSize)

            )
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Close,
                contentDescription = "",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        }

    )
}

@Preview(showBackground = true)
@Composable
fun Progress() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Sliders() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        var sliderPosition by remember { mutableStateOf(50f)}
        Column {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                steps = 10,
                valueRange = 0f..100f
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = "Slider position: $sliderPosition"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Switches() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        var checked by remember { mutableStateOf(true)}
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
        var checked2 by remember { mutableStateOf(true)}
        Switch(
            checked = checked2,
            onCheckedChange = {
                checked2 = it
            },
            thumbContent = if (checked2) {
                {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                }
            }else{
                null
            }
        )
        var checked3 by remember { mutableStateOf(true)}
        Checkbox(
            checked = checked3,
            onCheckedChange = {
                checked3 = it
            }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun Badges(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        var itemCount by remember { mutableStateOf(0) }
        BadgedBox(
            badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text(text = "$itemCount")
                    }
                }
            }
        ){
            Icon(imageVector = Icons.Filled.ShoppingCart,
                contentDescription = ""
            )

        }
        Button(
            onClick = { itemCount++ })
        {
            Text(text = "Add item")

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun TimePickers(
    onConfirm: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column {
        TimeInput(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Quitar")
        }
        Button(onClick = { onConfirm(timePickerState.hour, timePickerState.minute) }) {
            Text("Confirmar")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun DatePickers() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("DOB") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }

}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Preview(showBackground = true)
@Composable
fun SnackBars(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ){
        val snackSate = remember { SnackbarHostState() }
        val snackScope = rememberCoroutineScope()

        SnackbarHost(hostState = snackSate, Modifier)

        fun launchSnackBar(){
            snackScope.launch {
                snackSate.showSnackbar("The message was sent")
            }
        }

        Button(onClick = { launchSnackBar() }) {
            Text(text = "Show SnackBar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogs() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        var showAlertDialog by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }

        if (showAlertDialog) {
            AlertDialog(
                icon = { Icon(Icons.Filled.Warning, contentDescription = "") },
                title = { Text(text = "Confirm deletion") },
                text = { Text(text = "Are you sure you want to delete this item?") },
                onDismissRequest = { },
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Confirm"
                            showAlertDialog = false
                        }
                    ){Text(text = "Confirm")}
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Dismiss"
                            showAlertDialog = false
                        }
                    ){Text(text = "Dismiss")}
                }
            )

        }
        Text(selectedOption)
        Button(onClick = { showAlertDialog = true }) {
            Text(text = "Show AlertDialog")
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Bars(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)
    ){
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(10.dp, 50.dp, 10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Icon(Icons.Filled.Menu, contentDescription = "", tint = Color.White)
            Text(
                text = "App title",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)

            )
            Icon(Icons.Filled.Settings, contentDescription = "", tint = Color.White)
        }
        val post = arrayOf(
            PostModel(1, "TextCard","This is the card text", painterResource(R.drawable.logo)),
            PostModel(2, "TextCard","This is the card text", painterResource(R.drawable.logo)),
            PostModel(3, "TextCard","This is the card text", painterResource(R.drawable.logo)),
            PostModel(4, "TextCard","This is the card text", painterResource(R.drawable.logo)),
            PostModel(5, "TextCard","This is the card text", painterResource(R.drawable.logo)),
            PostModel(6, "TextCard","This is the card text", painterResource(R.drawable.logo)),
            PostModel(7, "TextCard","This is the card text", painterResource(R.drawable.logo)),
            PostModel(8, "TextCard","This is the card text", painterResource(R.drawable.logo)),
            PostModel(9, "TextCard","This is the card text", painterResource(R.drawable.logo)),
            PostModel(10, "TextCard","This is the card text", painterResource(R.drawable.logo))

        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxSize()
                .padding(10.dp, 90.dp, 10.dp, 50.dp),
        ) {
            //PostCard(1,  "TextCard","This is the card text", painterResource(R.drawable.logo))
           // Posts(post)
            PostGrid(post)
        }

    }
}

@Composable
fun Posts(arrayPosts : Array<PostModel>, adaptive: String){
    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        items(arrayPosts) { post ->
            when(adaptive){
                "PhoneP" -> PostCardCompact(post.id, post.title, post.body, post.image)
                "PhoneL" -> PostCard(post.id, post.title, post.body, post.image)
                "Tablet" ->
                PostCard(post.id, post.title, post.body, post.image)
            }

        }

    }
}

@Composable
fun PostGrid(arrayPosts : Array<PostModel>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(arrayPosts) { post ->
            PostCard(post.id, post.title, post.body, post.image)
        }

    }
}
@Preview(showBackground = true, device = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=240")
@Composable
fun Adaptive(){
    var WindowsSize = currentWindowAdaptiveInfo().windowSizeClass
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    //Compact width < 60dp phone portrait
    //Medium width >= 600dp < 840dp tablets portrait
    //Expanded width >= 840dp < 1200dp tablets landscape

    //Compact height < 480dp phone landscape
    //Medium height >= 480dp < 900dp tablets landscape or phone portrait
    //Expanded height >= 900dp < 1200dp tablets landscape

    val post = arrayOf(
        PostModel(1, "TextCard","This is the card text", painterResource(R.drawable.logo)),
        PostModel(2, "TextCard","This is the card text", painterResource(R.drawable.logo)),
        PostModel(3, "TextCard","This is the card text", painterResource(R.drawable.logo)),
        PostModel(4, "TextCard","This is the card text", painterResource(R.drawable.logo)),
        PostModel(5, "TextCard","This is the card text", painterResource(R.drawable.logo)),
        PostModel(6, "TextCard","This is the card text", painterResource(R.drawable.logo)),
        PostModel(7, "TextCard","This is the card text", painterResource(R.drawable.logo)),
        PostModel(8, "TextCard","This is the card text", painterResource(R.drawable.logo)),
        PostModel(9, "TextCard","This is the card text", painterResource(R.drawable.logo)),
        PostModel(10, "TextCard","This is the card text", painterResource(R.drawable.logo))

    )
    if(width == WindowWidthSizeClass.COMPACT){
        Posts(post, "PhoneP" )
    }else if(height == WindowHeightSizeClass.COMPACT){
        Posts(post, "PhoneL")
    }else{
        Posts(post, "Tablet")
    }
    Text(text = WindowsSize.toString())
}
