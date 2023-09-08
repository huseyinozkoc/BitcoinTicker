package com.huseyinozkoc.bitcointicker.domain.usecase

import com.huseyinozkoc.bitcointicker.domain.utils.WorkerInterface
import javax.inject.Inject

class WorkerUseCase @Inject constructor(
    private val workerProvider: WorkerInterface
) {
    operator fun invoke() = workerProvider.onSuccess()

}