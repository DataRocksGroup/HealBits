package thousand.group.healbits.views.main.di

import org.koin.dsl.module
import thousand.group.healbits.views.main.interactors.MainInteractor
import thousand.group.healbits.views.main.repositories.activity.MainRepository
import thousand.group.healbits.views.main.repositories.activity.MainRepositoryImpl

val interactorMainModule = module {
    single<MainRepository> {
        MainRepositoryImpl(get())
    }
    single { MainInteractor(get(), get(), get()) }
}