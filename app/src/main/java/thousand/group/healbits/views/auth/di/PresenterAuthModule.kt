package thousand.group.healbits.views.auth.di

import android.content.Context
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import thousand.group.healbits.global.constants.scopes.AuthScope
import thousand.group.healbits.views.auth.presenters.activity.AuthPresenter

val presenterAuthModule = module {
    scope(named(AuthScope.AUTH_ACTIVITY_SCOPE)) {
        scoped { (context: Context) ->
            AuthPresenter(context, get { parametersOf(context) }, get())
        }
    }

}