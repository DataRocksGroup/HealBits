package thousand.group.healbits.views.main.presenters.categories

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import thousand.group.healbits.R
import thousand.group.healbits.global.constants.simple.AppConstants
import thousand.group.healbits.global.helpers.ResErrorHelper
import thousand.group.healbits.global.presentation.BasePresenter
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.views.main.interactors.CategoriesInteractor

@InjectViewState
class CategoriesPresenter(
    override val context: Context,
    private val resourceManager: ResourceManager,
    private val interactor: CategoriesInteractor
) : BasePresenter<CategoriesView>() {

    private var startLoad = false
    private val dimen = resourceManager.getDimension(R.dimen.margin_between_items).toInt()

    override fun internetSuccess() {
    }

    override fun internetError() {
    }

    override fun onStart() {
        viewState.setTitle(R.string.label_category)
        viewState.setAdapter()
        refresh()
    }

    override fun onFinish() {
    }

    fun getCategories() {
        interactor.apply {
            getCategories()
                .doOnSubscribe {
                    if (!startLoad) {
                        viewState.showProgressBar(true)
                        viewState.showEmptyText(false)
                    }
                }
                .doFinally {
                    viewState.showProgressBar(false)
                    viewState.showSwipe(false)
                }
                .subscribe({
                    Log.i(TAG, "Code: ${it.code()}")
                    Log.i(TAG, "Body: ${it.body()}")

                    when (it.code()) {
                        AppConstants.STATUS_CODE_200 -> {
                            it.body()?.apply {
                                items?.apply {
                                    startLoad = true

                                    viewState.showEmptyText(false)
                                    viewState.setList(this)
                                    viewState.setDecor(dimen)
                                }
                            }
                        }
                        else -> {
                            viewState.showMessageError(ResErrorHelper.showErrorMessage(TAG, it))

                            showOnError()
                        }
                    }

                }, {
                    viewState.showMessageError(
                        ResErrorHelper.showThrowableMessage(
                            resourceManager,
                            TAG,
                            it
                        )
                    )
                })
                .connect()
        }
    }

    fun refresh() {
        getCategories()
    }

    fun updateOnResume() {
        viewState.setTitle(R.string.label_category)
        viewState.setAdapter()
        refresh()
    }

    private fun showOnError() {
        if (!startLoad) {
            viewState.showEmptyText(true)
            viewState.showSwipe(false)
        }
    }

}