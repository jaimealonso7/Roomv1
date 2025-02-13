package com.example.roomv1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomv1.viewmodels.FacturasViewModel
import com.example.roomv1.views.FacturasAddView
import com.example.roomv1.views.FacturasListView
import com.example.roomv1.views.FacturasUpdateView

@Composable
fun AppNavigation(viewModel: FacturasViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "FacturasListView") {
        composable("FacturasListView") {
            //Text("Pantalla de lista")
            FacturasListView(navController, viewModel)
        }

        // **Corrección: FacturasAddView inicializa sus valores en la pantalla**
        composable("FacturasAddView") {


            FacturasAddView(
                navController = navController,
                viewModel = viewModel,
                numeroFactura = it.arguments?.getString("numeroFactura") ?: "",
                fechaEmision = it.arguments?.getString("fechaEmision") ?: "",
                empresa = it.arguments?.getString("empresa") ?: "",
                nif = it.arguments?.getString("nif") ?: "",
                direccion = it.arguments?.getString("direccion") ?: "",
                baseImponible = it.arguments?.getDouble("baseImponible") ?: 0.0,
                iva = it.arguments?.getDouble("iva") ?: 0.0,
                total = it.arguments?.getDouble("total") ?: 0.0
            )
        }

        composable(
            "FacturasUpdateView/{id}/{numeroFactura}/{fechaEmision}/{empresa}/{nif}/{direccion}/{baseImponible}/{iva}/{total}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }, // Agregado ID
                navArgument("numeroFactura") { type = NavType.StringType },
                navArgument("fechaEmision") { type = NavType.StringType },
                navArgument("empresa") { type = NavType.StringType },
                navArgument("nif") { type = NavType.StringType },
                navArgument("direccion") { type = NavType.StringType },
                navArgument("baseImponible") { type = NavType.FloatType },
                navArgument("iva") { type = NavType.FloatType },
                navArgument("total") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            FacturasUpdateView(
                navController = navController,
                viewModel = viewModel,
                id = backStackEntry.arguments?.getInt("id") ?: 0, // Agregado ID
                numeroFactura = backStackEntry.arguments?.getString("numeroFactura") ?: "",
                fechaEmision = backStackEntry.arguments?.getString("fechaEmision") ?: "",
                empresa = backStackEntry.arguments?.getString("empresa") ?: "",
                nif = backStackEntry.arguments?.getString("nif") ?: "",
                direccion = backStackEntry.arguments?.getString("direccion") ?: "",
                baseImponible = backStackEntry.arguments?.getFloat("baseImponible")?.toDouble() ?: 0.0,
                iva = backStackEntry.arguments?.getFloat("iva")?.toDouble() ?: 0.0,
                total = backStackEntry.arguments?.getFloat("total")?.toDouble() ?: 0.0
            )
        }
    }
}

