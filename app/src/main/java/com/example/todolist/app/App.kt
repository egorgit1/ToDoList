package com.example.todolist.app

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.http.ContentType

@HiltAndroidApp
class App: Application()