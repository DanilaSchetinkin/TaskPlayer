package com.example.taskplayer.screen.SignInScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskplayer.R
import com.example.taskplayer.component.AuthPaswwordTextField
import com.example.taskplayer.component.AuthTextField
import com.example.taskplayer.ui.theme.LightGreen
import com.example.taskplayer.ui.theme.MediaTheme

@Composable
fun SignInScreen(){
    val signInViewModel: SignInViewModel = viewModel()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = LightGreen,
            topBar = {
                Column(modifier = Modifier.padding(horizontal = 20.dp))
                {
                    Row(
                        modifier = Modifier.padding(top = 35.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = null,
                        )
                    }
                    Row(modifier = Modifier.padding(top = 15.dp)) {
                        Text("Sign in",
                            style = MediaTheme.typography.alegreyaBoldStart,
                            color = MediaTheme.colors.text)
                    }

                }


            },
            bottomBar = {
                Image(
                    painter = painterResource(R.drawable.listiki),
                    contentDescription = null
                )
            }
        )
        { paddingValues ->
            SignInContent(paddingValues, signInViewModel)

        }
}

@Composable
fun SignInContent(
    paddingValues: PaddingValues,
    signInViewModel: SignInViewModel)
{
    val signInState = signInViewModel.signInState
    Column(modifier = Modifier.padding(paddingValues = paddingValues)){
        AuthTextField(
            value = signInState.value.email,
            onChangeValue = {
                signInViewModel.setEmail(it)
            },
            isError = signInViewModel.emailHasError.value,
            placeholder = {
                Text(text = "email")
                          },
            supportingText = {
                Text("Ошибка в почте")
            },
            label = {
                Text(text = "email")
            }
        )

        AuthPaswwordTextField(
        value = signInState.value.password,
            onChangeValue = {
                signInViewModel.setPassword(it)
            },
            isError = false,
            placeholder = {
                Text(text = "password")
            },
            supportingText = {
                Text(text = "неправильный пароль")
            },
            label = {
                Text(text = "password")
            },
        )

    }
}
