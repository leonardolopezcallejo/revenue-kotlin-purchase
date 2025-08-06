# RevenueCat Kotlin Proof of Concept
This repository contains a proof of concept (POC) Android application demonstrating integration of the RevenueCat SDK with Google Play Billing using Kotlin and Jetpack Compose. It allows fetching subscription offerings from RevenueCat, displaying a subscription button with the localized price, and handling purchase flow in sandbox mode.

## Features
* Initialize RevenueCat SDK with your public SDK key
* Fetch available offerings and display the monthly subscription package
* Launch Google Play purchase flow for subscriptions
* Handle purchase success and error/cancellation callbacks
* Display user feedback messages in the UI

## Prerequisites
* Android Studio Electric Eel (2022.3) or newer
* Android SDK Platform 36
* Kotlin 1.8+ (configured via Gradle)
* A RevenueCat account with a project and public SDK key
* A Google Play Console account for internal testing

## Setup
1. Clone the repository:

   ```bash
   git clone https://github.com/leonardolopezcallejo/revenue-kotlin-purchase.git
   cd RevenuePOC
   ```

2. Open the project in Android Studio.

3. In `app/src/main/java/com/example/revenuepoc/App.kt`, replace the placeholder SDK key with your RevenueCat public SDK key:

4. Verify dependencies in `app/build.gradle.kts`:

   ```kotlin
   implementation("com.android.billingclient:billing-ktx:8.0.0")
   implementation("com.revenuecat.purchases:purchases:9.1.0")
   ```

5. Sync Gradle: **Sync Project with Gradle Files**.

## Running on Emulator
1. Create or launch an Android Virtual Device (AVD) with Google Play support (Android 13+).
2. Run the app in debug mode from Android Studio or via command line:
3. Observe "Loading offers..." text, then tap the "Subscribe for ..." button when it appears.
4. Complete or cancel the purchase in the sandbox UI.
5. Verify feedback message in the app and events in the RevenueCat dashboard.

## Running on Physical Device
1. Enroll in internal testing in Google Play Console and add your test account.
2. Build and sign an internal test APK or App Bundle.
3. Upload to the internal testing track and install via the Play Store link.
4. Open the app and perform the sandbox purchase flow as above.

## Troubleshooting
* Ensure `JAVA_HOME` is set correctly for command-line builds.
* Verify `mavenCentral()` is declared in `settings.gradle.kts`, not in `app/build.gradle.kts`.
* Enable debug logs in `App.kt` by setting `Purchases.debugLogsEnabled = true`.

## License
This project is provided as-is for demonstration purposes. No license is specified.