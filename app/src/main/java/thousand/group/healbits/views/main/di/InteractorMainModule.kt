package thousand.group.healbits.views.main.di

import org.koin.dsl.module
import thousand.group.azimutgas.views.main.repositories.profile.ProfileRepository
import thousand.group.azimutgas.views.main.repositories.profile.ProfileRepositoryImpl
import thousand.group.healbits.views.main.interactors.MainInteractor
import thousand.group.healbits.views.main.interactors.ProfileInteractor
import thousand.group.healbits.views.main.repositories.activity.MainRepository
import thousand.group.healbits.views.main.repositories.activity.MainRepositoryImpl

val interactorMainModule = module {
    single<MainRepository> {
        MainRepositoryImpl(get())
    }
    single { MainInteractor(get(), get(), get()) }

    single<ProfileRepository> {
        ProfileRepositoryImpl(get())
    }
    single { ProfileInteractor(get(), get(), get()) }
}