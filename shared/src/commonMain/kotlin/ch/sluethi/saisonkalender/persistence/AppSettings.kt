package ch.sluethi.saisonkalender.persistence

import com.russhwolf.settings.Settings

class AppSettings {

    val settings: Settings = Settings()

    var versionCode: Int
        set(value) = settings.putInt(KEY_VERSION, value)
        get() = settings.getInt(KEY_VERSION, 0)

    companion object {
        private const val KEY_VERSION = "versionCode"
    }
}