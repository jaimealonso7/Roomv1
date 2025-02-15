package com.example.roomv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.roomv1.navigation.AppNavigation
import com.example.roomv1.room.FacturasDatabase
import com.example.roomv1.ui.theme.Roomv1Theme
import com.example.roomv1.viewmodels.FacturasViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: FacturasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Roomv1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //val database = Room.databaseBuilder(this, FacturasDatabase::class.java, "Facturas").build()
                    //val dao = database.facturasDao()

                    //val viewModel = FacturasViewModel(dao)

                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Roomv1Theme {
        Greeting("Android")
    }
}