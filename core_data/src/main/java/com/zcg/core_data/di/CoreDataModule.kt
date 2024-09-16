package com.zcg.core_data.di

import com.zcg.core_data.remote.RemoteHelper
import com.zcg.core_data.repository.ItemsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CoreDataModule {

    @Provides
    @Singleton
    fun providesItemsRepository(
        remoteHelper: RemoteHelper
    ) = ItemsRepository(remoteHelper)

}