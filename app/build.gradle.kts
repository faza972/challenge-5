plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.faza.challenge_4"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.faza.challenge_4"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
        dataBinding = true
    }
    flavorDimensions += "env"
    productFlavors {
        create("production") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://https://671c4e06-ccad-4037-a84f-f6c2f13e087d.mock.pstmn.io\""
            )
        }
        create("integration") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://671c4e06-ccad-4037-a84f-f6c2f13e087d.mock.pstmn.io\""
            )
            }
        }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    implementation("io.coil-kt:coil:2.4.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")

    implementation("com.google.firebase:firebase-database-ktx:20.3.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.2.0")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-crashlytics:18.5.1")
    implementation("com.google.firebase:firebase-analytics:21.5.0")
    kapt("androidx.room:room-compiler:2.6.0")

    implementation ("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
    
    implementation ("androidx.activity:activity-ktx:1.8.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
}