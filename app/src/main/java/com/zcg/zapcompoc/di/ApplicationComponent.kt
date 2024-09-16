package com.zcg.zapcompoc.di

import com.zcg.core_data.di.CoreDataModule
import com.zcg.core_usecase.di.CoreUseCaseModule
import com.zcg.zapcompoc.home.MainActivity
import dagger.Component
import javax.inject.Singleton

@ApplicationScope
@Component(
    modules = [
        UIModule::class,
        CoreUseCaseModule::class,
        CoreDataModule::class
    ]
)
@Singleton
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(utilsModule: UIModule): ApplicationComponent
    }

    fun inject(homeActivity: MainActivity)
}
