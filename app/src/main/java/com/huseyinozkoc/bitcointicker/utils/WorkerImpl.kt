package com.huseyinozkoc.bitcointicker.utils

import android.content.Context
import androidx.work.*
import com.huseyinozkoc.bitcointicker.common.Constants
import com.huseyinozkoc.bitcointicker.common.Constants.SYNC_DATA
import com.huseyinozkoc.bitcointicker.domain.utils.WorkerInterface
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerImpl @Inject constructor(context: Context) : WorkerInterface {

    private val workManager = WorkManager.getInstance(context)

    private val workConstraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true)
        .build()

    override fun createWork() {

        val workRequest = PeriodicWorkRequestBuilder<CoinWorker>(
            15, TimeUnit.MINUTES,
            15, TimeUnit.MINUTES
        ).setConstraints(workConstraints).setInitialDelay(15, TimeUnit.MINUTES).addTag(SYNC_DATA)
            .build()

        workManager.enqueueUniquePeriodicWork(
            Constants.SYNC_DATA_WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    override fun onSuccess() = workManager.getWorkInfosByTagLiveData(SYNC_DATA)
}