package com.example.myapplication.di

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.myapplication.App

class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, App::class.java.name, context)
    }
}