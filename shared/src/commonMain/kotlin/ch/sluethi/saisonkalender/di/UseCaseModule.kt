package ch.sluethi.saisonkalender.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ch.sluethi.saisonkalender.usecase.GetCalendarDataUseCase
import ch.sluethi.saisonkalender.usecase.GetProductUseCase

val useCaseModule = module {
    singleOf(::GetCalendarDataUseCase)
    singleOf(::GetProductUseCase)
}
