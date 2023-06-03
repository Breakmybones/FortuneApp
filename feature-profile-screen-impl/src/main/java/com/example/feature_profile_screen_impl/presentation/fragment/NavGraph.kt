package com.example.feature_profile_screen_impl.presentation.fragment

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature_profile_screen_api.model.UserProfileModel

//@Composable
//fun SetupNavGraph(
//    navHostController: NavHostController
//) {
//    NavHost(
//        navController = navHostController,
//        startDestination = Screen.Profile.route
//    ) {
//        composable(
//            route = Screen.Profile.route
//        ) {
//            ProfileScreen(userProfile = UserProfileModel("p", "p", "", false), navHostController)
//        }
//        composable(
//            route = Screen.EditProfile.route
//        ) {
//            EditProfileScreen(UserProfileModel("p", "p", "", false))
//        }
//    }
//}