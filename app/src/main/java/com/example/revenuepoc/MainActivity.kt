package com.example.revenuepoc

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.revenuecat.purchases.*
import com.revenuecat.purchases.models.StoreTransaction
import com.revenuecat.purchases.Package


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura el SDK de RevenueCat con tu clave de API pública.
        Purchases.configure(
            PurchasesConfiguration.Builder(this, "PUBLIC API KEY GOES HERE").build()
        )

        // Opcional: configura el nivel de log a debug.
        Purchases.logLevel = LogLevel.DEBUG

        setContent {
            PurchaseScreen()
        }
    }
}

// Extensión para obtener la Activity desde un Contexto
fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun PurchaseScreen() {
    var currentPackage by remember { mutableStateOf<Package?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var message by remember { mutableStateOf<String?>(null) }

    // Usa LaunchedEffect para obtener las ofertas cuando se inicia el composable.
    LaunchedEffect(Unit) {
        Purchases.sharedInstance.getOfferingsWith(
            onSuccess = { offerings ->
                // Guarda el paquete mensual.
                currentPackage = offerings.current?.monthly
                isLoading = false
            },
            onError = { error ->
                // Maneja el error al cargar las ofertas.
                message = "Error loading offerings: ${error.message}"
                isLoading = false
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            isLoading -> {
                Text(text = "Loading offers...")
            }
            currentPackage != null -> {
                val activity = LocalContext.current.findActivity()
                Button(
                    onClick = {
                        activity?.let {
                            Purchases.sharedInstance.purchaseWith(
                                PurchaseParams.Builder(it, currentPackage!!).build(),
                                onSuccess = { storeTransaction: StoreTransaction?, customerInfo: CustomerInfo ->
                                    // Maneja la compra exitosa.
                                    message = "Purchase successful"
                                },
                                onError = { error: PurchasesError, userCancelled: Boolean ->
                                    // Maneja el error o la cancelación.
                                    message = if (userCancelled) {
                                        "Purchase cancelled"
                                    } else {
                                        "Error during purchase: ${error.message}"
                                    }
                                }
                            )
                        }
                    }
                ) {
                    // Se accede a `formattedPrice` a través de la propiedad `price` del producto.
                    Text(text = "Subscribe for ${currentPackage!!.product.price.formatted}")
                }
            }
            else -> {
                Text(text = message ?: "No available subscription")
            }
        }
    }
}
