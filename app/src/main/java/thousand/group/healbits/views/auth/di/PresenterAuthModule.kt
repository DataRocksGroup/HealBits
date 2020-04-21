package thousand.group.healbits.views.auth.di

import android.content.Context
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import thousand.group.healbits.global.constants.scopes.AuthScope
import thousand.group.healbits.views.auth.presenters.activity.AuthPresenter
import thousand.group.healbits.views.auth.presenters.login.LoginPresenter
import thousand.group.healbits.views.auth.presenters.signup.SignUpPresenter

val presenterAuthModule = module {
    scope(named(AuthScope.AUTH_ACTIVITY_SCOPE)) {
        scoped { (context: Context) ->
            AuthPresenter(context, get { parametersOf(context) }, get())
        }
    }

    scope(named(AuthScope.LOGIN_SCOPE)) {
        scoped { (context: Context) ->
            LoginPresenter(context, get { parametersOf(context) }, get())
        }
    }

    scope(named(AuthScope.SIGN_UP_SCOPE)) {
        scoped { (context: Context) ->
            SignUpPresenter(context, get { parametersOf(context) }, get())
        }
    }
}