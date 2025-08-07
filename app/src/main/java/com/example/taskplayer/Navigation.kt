package com.example.taskplayer

import androidx.navigation.NavHostController

interface AppNavigator {
    fun navigateToMain()
    fun navigateToLogin()
    fun navigateToOnboarding()
    fun navigateToProfile()
    fun navigateToPhoto(path: String)
    fun navigateBack()
}

// Реализация навигатора
class AppNavigatorImpl(
    private val navController: NavHostController
) : AppNavigator {
    override fun navigateToMain() {
        navController.navigate(Routes.MAIN) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    override fun navigateToLogin() {
        navController.navigate(Routes.LOGIN) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    override fun navigateToOnboarding() {
        navController.navigate(Routes.ONBOARDING) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    override fun navigateToProfile() {
        navController.navigate(Routes.PROFILE)
    }

    override fun navigateToPhoto(path: String) {
        navController.navigate(Routes.photoScreenPath(path))
    }

    override fun navigateBack() {
        navController.popBackStack()
    }
}

// Объект с маршрутами
object Routes {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val LOGIN = "login"
    const val MAIN = "main"
    const val PROFILE = "profile"
    const val PHOTO_SCREEN = "photo_screen/{path}"

    fun photoScreenPath(path: String): String {
        require(path.isNotBlank()) { "Path cannot be blank" }
        return "photo_screen/$path"
    }
}

// Sealed class для типобезопасной навигации (опционально)
sealed class Screen(val route: String) {
    object Splash : Screen(Routes.SPLASH)
    object Onboarding : Screen(Routes.ONBOARDING)
    object Login : Screen(Routes.LOGIN)
    object Main : Screen(Routes.MAIN)
    object Profile : Screen(Routes.PROFILE)
    data class Photo(val path: String) : Screen(Routes.photoScreenPath(path)) {
        companion object {
            const val BASE = Routes.PHOTO_SCREEN
        }
    }
}


fun NavHostController.navigateToPhoto(path: String) {
    navigate(Screen.Photo(path).route)
}

fun NavHostController.navigateToMain() {
    navigate(Screen.Main.route) {
        popUpTo(Screen.Splash.route) { inclusive = true }
    }
}

fun NavHostController.navigateToLogin() {
    navigate(Screen.Login.route) {
        popUpTo(Screen.Splash.route) { inclusive = true }
    }
}