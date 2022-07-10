package ch.sluethi.saisonkalender.di


import ch.sluethi.saisonkalender.network.Firestore
import ch.sluethi.saisonkalender.persistence.AppSettings
import ch.sluethi.saisonkalender.persistence.Persistence
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::Persistence)
    singleOf(::AppSettings)
    singleOf(::Firestore)
}