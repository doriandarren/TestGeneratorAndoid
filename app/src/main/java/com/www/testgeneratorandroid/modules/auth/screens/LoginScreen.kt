package com.www.testgeneratorandroid.modules.auth.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.www.testgeneratorandroid.R
import com.www.testgeneratorandroid.modules.auth.repositories.AuthRepository
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val authRepository = remember { AuthRepository() }

    val loginTitle = stringResource(id = R.string.login_title)
    val loginSubtitle = stringResource(id = R.string.login_subtitle)
    val emailLabel = stringResource(id = R.string.email)
    val passwordLabel = stringResource(id = R.string.password)
    val signInText = stringResource(id = R.string.sign_in)
    val forgotPasswordText = stringResource(id = R.string.forgot_password)
    val emptyFieldsMessage = stringResource(id = R.string.login_empty_fields)
    val messageTitle = stringResource(id = R.string.message)
    val acceptText = stringResource(id = R.string.accept)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = loginTitle,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = loginSubtitle,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(emailLabel) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(passwordLabel) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    dialogMessage = emptyFieldsMessage
                    showDialog = true
                    return@Button
                }

                scope.launch {
                    val result = authRepository.login(email, password)
                    dialogMessage = result.message
                    showDialog = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(signInText)
        }

        TextButton(
            onClick = {},
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(forgotPasswordText)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(messageTitle) },
            text = { Text(dialogMessage) },
            confirmButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text(acceptText)
                }
            }
        )
    }
}