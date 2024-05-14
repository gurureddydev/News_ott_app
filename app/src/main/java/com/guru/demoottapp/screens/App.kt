package com.guru.demoottapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.guru.demoottapp.screens.categories.CategoryNewsListScreen
import com.guru.demoottapp.screens.dashboard.DashboardScreen
import com.guru.demoottapp.screens.videoPlayer.VideoPlayerScreen

@Composable
fun App(
    onBackPressed: () -> Unit,
) {
    val navController = rememberNavController()
    var isComingBackFromDifferentScreen by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = Screens.Dashboard(), builder = {
        composable(
            route = Screens.CategoryMovieList(),
            arguments = listOf(navArgument(CategoryNewsListScreen.CategoryIdBundleKey) {
                type = NavType.StringType
            })
        ) {
            CategoryNewsListScreen(
                onBackPressed = {
                    if (navController.navigateUp()) {
                        isComingBackFromDifferentScreen = true
                    }
                },
                onMovieSelected = { movie ->
//                    navController.navigate(
//                        Screens.MovieDetails.withArgs(movie.id)
//                    )
                }
            )

        }
        composable(route = Screens.Dashboard()) {
            DashboardScreen(openCategoryMovieList = { categoryId ->
                navController.navigate(
                    Screens.CategoryMovieList.withArgs(categoryId)
                )
            },
                openMovieDetailsScreen = { movieId ->
                    // Navigate to MovieDetailsScreen when a movie is clicked
//                        navController.navigate("${Screens.MovieDetails}/$movieId")
                },
                openVideoPlayer = {
                    navController.navigate(Screens.VideoPlayer())
                },
                onBackPressed = onBackPressed,
                isComingBackFromDifferentScreen = isComingBackFromDifferentScreen,
                resetIsComingBackFromDifferentScreen = {
                    isComingBackFromDifferentScreen = false
                })
        }
        composable(route = Screens.VideoPlayer()) {
            VideoPlayerScreen(
                onBackPressed = {
                    if (navController.navigateUp()) {
                        isComingBackFromDifferentScreen = true
                    }
                }
            )
        }
    })
}
