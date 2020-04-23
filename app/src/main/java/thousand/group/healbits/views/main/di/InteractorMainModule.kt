package thousand.group.healbits.views.main.di

import org.koin.dsl.module
import thousand.group.azimutgas.views.main.repositories.profile.ProfileRepository
import thousand.group.azimutgas.views.main.repositories.profile.ProfileRepositoryImpl
import thousand.group.healbits.views.main.interactors.*
import thousand.group.healbits.views.main.repositories.activity.MainRepository
import thousand.group.healbits.views.main.repositories.activity.MainRepositoryImpl
import thousand.group.healbits.views.main.repositories.categories.CategoriesRepository
import thousand.group.healbits.views.main.repositories.categories.CategoriesRepositoryImpl
import thousand.group.healbits.views.main.repositories.exercise.ExerciseRepository
import thousand.group.healbits.views.main.repositories.exercise.ExerciseRepositoryImpl
import thousand.group.healbits.views.main.repositories.profile_edit.ProfileEditRepository
import thousand.group.healbits.views.main.repositories.profile_edit.ProfileEditRepositoryImpl

val interactorMainModule = module {
    single<MainRepository> {
        MainRepositoryImpl(get())
    }
    single { MainInteractor(get(), get(), get()) }

    single<ProfileRepository> {
        ProfileRepositoryImpl(get())
    }
    single { ProfileInteractor(get(), get(), get()) }

    single<ProfileEditRepository> {
        ProfileEditRepositoryImpl(get())
    }
    single { ProfileEditInteractor(get(), get(), get()) }

    single<CategoriesRepository> {
        CategoriesRepositoryImpl(get())
    }
    single { CategoriesInteractor(get(), get(), get()) }

    single<ExerciseRepository> {
        ExerciseRepositoryImpl(get())
    }
    single { ExerciseInteractor(get(), get(), get()) }
}