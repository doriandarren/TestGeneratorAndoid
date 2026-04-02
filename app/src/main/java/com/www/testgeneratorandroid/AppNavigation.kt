package com.www.testgeneratorandroid

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.www.testgeneratorandroid.modules.auth.screens.HomeScreen
import com.www.testgeneratorandroid.modules.auth.screens.LoginScreen
import kotlinx.coroutines.launch

const val ROUTE_HOME = "home"
const val ROUTE_LOGIN = "login"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menú")
                HorizontalDivider()

                TextButton(
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(ROUTE_HOME) {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text("Home")
                }

                TextButton(
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(ROUTE_LOGIN) {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text("Login")
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Test Generator") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = ROUTE_HOME,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(ROUTE_HOME) {
                    HomeScreen(
                        onLoginClick = {
                            navController.navigate(ROUTE_LOGIN) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable(ROUTE_LOGIN) {
                    LoginScreen()
                }
            }
        }
    }
}