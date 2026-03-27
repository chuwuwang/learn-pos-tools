package com.sea.pos.extension

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object AppScope {

    private val job = SupervisorJob()

    val scope = CoroutineScope(context = Dispatchers.Default + job + CoroutineExceptionHandler { _, e ->
            println("Coroutine error: " + e.message)
        }
    )

    fun cancel() {
        job.cancel()
    }

}