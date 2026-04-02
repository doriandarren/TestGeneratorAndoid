package com.www.testgeneratorandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.www.testgeneratorandroid.ui.theme.TestGeneratorAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TestGeneratorAndroidTheme {
                AppNavigation()
            }
        }
    }
}