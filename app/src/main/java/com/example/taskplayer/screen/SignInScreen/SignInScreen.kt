package com.example.taskplayer.screen.SignInScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat.Style
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskplayer.R
import com.example.taskplayer.common.UnderLineTextField
import com.example.taskplayer.component.AuthButton
import com.example.taskplayer.component.AuthPaswwordTextField
import com.example.taskplayer.component.AuthTextField
import com.example.taskplayer.data.local.TokenManager
import com.example.taskplayer.data.remote.RetrofitClient
import com.example.taskplayer.data.remote.repository.AuthRepository
import com.example.taskplayer.ui.theme.DarkGreen
import com.example.taskplayer.ui.theme.LightGreen
import com.example.taskplayer.ui.theme.MediaTheme
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    onNavigateToMain: () -> Unit,
    navController: NavController,
    tokenManager: TokenManager
) {
    val context = LocalContext.current

//    можно попробовать через hilt

    val signInViewModel: SignInViewModel = viewModel(
        factory = object :ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SignInViewModel(
                    authRepository = AuthRepository(RetrofitClient.authService),
                    tokenManager = TokenManager(context)
                ) as T
            }
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkGreen,
        topBar = {
            Column {
                Row(
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = null,
                    )
                }
                Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(
                        stringResource(R.string.sign_in),
                        style = MediaTheme.typography.alegreyaBoldTittle,
                        color = MediaTheme.colors.text
                    )
                }
            }
        }
    ) { paddingValues ->
        SignInContent(paddingValues, signInViewModel, navController)
    }
}

@Composable
fun SignInContent(
    paddingValues: PaddingValues,
    signInViewModel: SignInViewModel,
    navController: NavController
) {
    val signInState = signInViewModel.signInState
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        UnderLineTextField(
            value = signInState.value.email,
            onValueChange = { signInViewModel.setEmail(it) },
            label = "Email",
            isError = signInViewModel.emailHasError.value,
            supportingText = {
                if (signInViewModel.emailHasError.value) {
                    Text("Ошибка в почте")
                }
            }
        )

        UnderLineTextField(
            value = signInState.value.password,
            onValueChange = { signInViewModel.setPassword(it) },
            isError = false,
            label = "Пароль",
        )

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AuthButton(
                onClick = {
                    coroutineScope.launch {
                        if (signInViewModel.login()) {
                            navController.navigate("main") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    }
                }
            ) {
                Text("Sign in", style = MediaTheme.typography.alegreyaSans25)
            }
        }

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            Text(
                "Register",
                style = MediaTheme.typography.alegreyaSans20400,
                color = MediaTheme.colors.text,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AuthButton(
                        onClick = { /* TODO: Добавить навигацию */ }
                    ) {
                        Text(
                            "Профиль",
                            style = MediaTheme.typography.alegreyaSans25,
                            color = MediaTheme.colors.text
                        )
                    }
                }

                Image(
                    painter = painterResource(R.drawable.listiki),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

