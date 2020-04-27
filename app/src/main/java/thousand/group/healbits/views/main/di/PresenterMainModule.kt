package thousand.group.healbits.views.main.di

import android.content.Context
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import thousand.group.azimutgas.views.main.presentations.profile.ProfilePresenter
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.views.main.presenters.activity.MainPresenter
import thousand.group.healbits.views.main.presenters.categories.CategoriesPresenter
import thousand.group.healbits.views.main.presenters.exercise.ExercisePresenter
import thousand.group.healbits.views.main.presenters.profile_edit.ProfileEditPresenter
import thousand.group.healbits.views.main.presenters.tasks.TaskPresenter

val presenterMainModule = module {
    scope(named(MainScope.MAIN_SCOPE)) {
        scoped { (context: Context) ->
            MainPresenter(context, get { parametersOf(context) }, get())
        }
    }

    scope(named(MainScope.PROFILE_SCOPE)) {
        scoped { (context: Context) ->
            ProfilePresenter(context, get { parametersOf(context) }, get())
        }
    }

    scope(named(MainScope.PROFILE_EDIT_SCOPE)) {
        scoped { (context: Context) ->
            ProfileEditPresenter(context, get { parametersOf(context) }, get())
        }
    }

    scope(named(MainScope.CATEGORIES_SCOPE)) {
        scoped { (context: Context) ->
            CategoriesPresenter(context, get { parametersOf(context) }, get())
        }
    }

    scope(named(MainScope.EXERCISE_SCOPE)) {
        scoped { (context: Context) ->
            ExercisePresenter(context, get { parametersOf(context) }, get())
        }
    }

    scope(named(MainScope.TASKS_SCOPE)) {
        scoped { (context: Context) ->
            TaskPresenter(context, get { parametersOf(context) }, get())
        }
    }
}