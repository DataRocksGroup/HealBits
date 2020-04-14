package thousand.group.healbits.views.auth.di

import org.koin.dsl.module
import thousand.group.healbits.views.auth.interactors.AuthInteractor
import thousand.group.healbits.views.auth.repositories.activity.AuthRepository
import thousand.group.healbits.views.auth.repositories.activity.AuthRepositoryImpl

val interactorAuthModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
    single { AuthInteractor(get(), get(), get()) }
}