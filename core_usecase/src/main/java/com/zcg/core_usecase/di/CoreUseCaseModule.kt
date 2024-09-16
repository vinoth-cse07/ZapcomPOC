package com.zcg.core_usecase.di

import com.zcg.core_data.di.CoreDataModule
import com.zcg.core_data.repository.ItemsRepository
import com.zcg.core_usecase.items.ItemsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [CoreDataModule::class])
object CoreUseCaseModule {

    @Provides
    @Singleton
    fun providesItemsUseCase(
        itemsRepository: ItemsRepository
    ) = ItemsUseCase(itemsRepository)

}