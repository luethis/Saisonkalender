package ch.sluethi.saisonkalender.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ch.sluethi.saisonkalender.usecase.GetCalendarDataUseCase
import ch.sluethi.saisonkalender.usecase.GetProductUseCase
import ch.sluethi.saisonkalender.usecase.UpdateCalendarUseCase

val useCaseModule = module {
    singleOf(::GetCalendarDataUseCase)
    singleOf(::GetProductUseCase)
    singleOf(::UpdateCalendarUseCase)
}
