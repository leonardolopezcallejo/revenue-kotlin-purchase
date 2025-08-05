package com.example.revenuepoc

import android.app.Application
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Enable debug logs
        Purchases.debugLogsEnabled = true
        // Build the configuration with your public Google API key
        val purchasesConfig = PurchasesConfiguration.Builder(
            this,
            "your_public_sdk_key_here"  // replace with your actual key
        )
            .build()
        // Initialize RevenueCat SDK with the configuration
        Purchases.configure(purchasesConfig)
    }
}