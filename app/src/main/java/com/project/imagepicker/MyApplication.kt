package com.project.imagepicker

import android.app.Application
import com.project.imagepicker.core.common.ExceptionToMessageMapper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {

    @Inject
    lateinit var exceptionToMessageMapper: ExceptionToMessageMapper

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        ExceptionToMessageMapper.set(exceptionToMessageMapper)
    }

}