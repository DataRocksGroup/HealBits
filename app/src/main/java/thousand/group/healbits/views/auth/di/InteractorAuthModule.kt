package thousand.group.healbits.views.auth.di

import org.koin.dsl.module
import thousand.group.healbits.views.auth.interactors.AuthInteractor
import thousand.group.healbits.views.auth.interactors.LoginInteractor
import thousand.group.healbits.views.auth.interactors.SignUpInteractor
import thousand.group.healbits.views.auth.repositories.activity.AuthRepository
import thousand.group.healbits.views.auth.repositories.activity.AuthRepositoryImpl
import thousand.group.healbits.views.auth.repositories.login.LoginRepository
import thousand.group.healbits.views.auth.repositories.login.LoginRepositoryImpl
import thousand.group.healbits.views.auth.repositories.signup.SignUpRepository
import thousand.group.healbits.views.auth.repositories.signup.SignUpRepositoryImpl

val interactorAuthModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
    single { AuthInteractor(get(), get(), get()) }

    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }
    single { LoginInteractor(get(), get(), get()) }

    single<SignUpRepository> {
        SignUpRepositoryImpl(get())
    }
    single { SignUpInteractor(get(), get(), get()) }
}