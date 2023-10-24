package com.example.myweatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myweatherapp.presentation.ui.add_edit_todo.AddEditTodoScreen
import com.example.myweatherapp.presentation.ui.theme.MyWeatherApp
import com.example.myweatherapp.presentation.ui.todo_list.TodoListScreen
import com.example.myweatherapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherApp {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.TODO_LIST
                ) {
                    composable(Routes.TODO_LIST) {
                        TodoListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(
                        Routes.ADD_EDIT_TODO + "?todoId={todoId}&todoColor={todoColor}",
                        arguments = listOf(
                            navArgument(name = "todoId") {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(name = "todoColor") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        val color = it.arguments?.getInt("todoColor") ?: -1
                        AddEditTodoScreen(
                            onPopBackStack = {
                            navController.popBackStack()
                        },
                            todoColor = color
                        )

                    }
                }

            }
        }
    }
}
