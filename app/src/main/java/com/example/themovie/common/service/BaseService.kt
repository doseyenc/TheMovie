package com.example.themovie.common.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

abstract class BaseService :Service() {

    override fun onBind(intent: Intent): IBinder? {
        // We don't provide binding, so return null
        init()
        return null
    }

    abstract fun init()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }




}