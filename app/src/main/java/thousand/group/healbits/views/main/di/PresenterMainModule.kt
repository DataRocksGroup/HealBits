package thousand.group.healbits.views.main.di

import android.content.Context
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.views.main.presenters.activity.MainPresenter

val presenterMainModule = module {
    scope(named(MainScope.MAIN_SCOPE)) {
        scoped { (context: Context) ->
            MainPresenter(context, get { parametersOf(context) }, get())
        }
    }
}