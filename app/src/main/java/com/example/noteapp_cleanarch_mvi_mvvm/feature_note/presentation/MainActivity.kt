package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation.note_list.NoteListScreen
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation.util.Screen
import com.example.noteapp_cleanarch_mvi_mvvm.ui.theme.NoteAppCleanArchMviMvvMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppCleanArchMviMvvMTheme {
                Surface(color = MaterialTheme.colorScheme.background)
                {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.NoteListScreen.route )
                    {
                        composable(
                            route = Screen.NoteListScreen.route
                        ){
                            NoteListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                            val noteColor = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNoteScreen(navController = navController, noteColor = noteColor)
                        }
                    }
                }
            }
        }
    }
}