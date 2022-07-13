package ch.sluethi.saisonkalender.usecase

import ch.sluethi.saisonkalender.network.Firestore
import ch.sluethi.saisonkalender.persistence.AppSettings
import ch.sluethi.saisonkalender.persistence.Persistence
import co.touchlab.kermit.Logger
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class UpdateCalendarUseCase(
    private val firestore: Firestore,
    private val settings: AppSettings,
    private val cache: Persistence
) {

    suspend fun updateProducts(): UpdateResult {
        writeLog("updateProducts() invoked")
        runCatching { Firebase.auth.signInAnonymously() }
            .onSuccess {
                val remoteVersion = firestore.fetchVersion()
                val localVersion = settings.versionCode

                return if (remoteVersion > localVersion) {
                    writeLog("update needed")
                    cache.deleteProducts()
                    cache.insertProducts(firestore.fetchData())
                    settings.versionCode = remoteVersion
                    UpdateResult.UPDATED
                } else {
                    writeLog("update not needed")
                    UpdateResult.NOT_NEEDED
                }
            }
            .onFailure { writeLog("update failed ${it.message}") }
        return UpdateResult.UPDATE_FAILED
    }

    enum class UpdateResult {
        UPDATED,
        NOT_NEEDED,
        UPDATE_FAILED
    }

    private fun writeLog(message: String) {
        Logger.v("UpdateCalendarUseCase - $message")
    }
}