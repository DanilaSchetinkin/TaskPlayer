package com.example.taskplayer.screen.component

import com.example.taskplayer.R


val bottomNavItem = listOf(
    BottomNavItem(
        route = "Main",
        selectedIcon = R.drawable.logo1,
        unselectedIcon = R.drawable.logo1
    ),
    BottomNavItem(
        route = "player",
        selectedIcon = R.drawable.sound,
        unselectedIcon = R.drawable.sound
    ),
    BottomNavItem(
        route = "profile",
        selectedIcon = R.drawable.user,
        unselectedIcon = R.drawable.profile
    )
)
