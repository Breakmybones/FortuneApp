package com.example.feature_profile_screen_impl.presentation.fragment

sealed class Screen(val route: String) {
    object Profile: Screen(route = "profile_screen")
    object EditProfile: Screen(route = "edit_profile_screen")
}
