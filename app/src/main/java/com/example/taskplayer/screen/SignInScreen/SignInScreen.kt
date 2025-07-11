package com.example.taskplayer.screen.SignInScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskplayer.R
import com.example.taskplayer.ui.theme.LightGreen
import com.example.taskplayer.ui.theme.MediaTheme
import com.example.taskplayer.ui.theme.Purple80

@Composable
fun SignInScreen(){
    val signInViewModel: SignInViewModel = viewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Blue),
        topBar = {
            Column(modifier = Modifier.padding(horizontal = 20.dp))
            {
                Row(
                    modifier = Modifier.padding(top = 35.dp)
                        .fillMaxWidth()
                ) {
                    Image(painter = painterResource(R.drawable.logo),
                        contentDescription = null,)
                }
                Row(modifier = Modifier.padding(top = 15.dp)) {
                    Text("Sign in", style = MediaTheme.typography.alegreyaBoldStart)
                }

            }


    },
        bottomBar = {
            Image(painter =  painterResource(R.drawable.listiki),
                contentDescription = null)
        }
    )
    { paddingValues -> SignInContent(paddingValues, signInViewModel)

    }



}

@Composable
fun SignInContent(
    paddingValues: PaddingValues,
    signInViewModel: SignInViewModel)
{
    val signInState = signInViewModel.signInState
    Column(modifier = Modifier.padding(paddingValues = paddingValues)){

    }
}
