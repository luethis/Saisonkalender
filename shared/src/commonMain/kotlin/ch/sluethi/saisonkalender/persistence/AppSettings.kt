package ch.sluethi.saisonkalender.persistence

import com.russhwolf.settings.Settings

object AppSettings {

    private const val KEY_VERSION = "versionCode"

    val settings: Settings = Settings()

    var versionCode: Int
        set(value) = settings.putInt(KEY_VERSION, value)
        get() = settings.getInt(KEY_VERSION, 0)
}