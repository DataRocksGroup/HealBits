package thousand.group.healbits.views.main.presenters.exercise

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import thousand.group.healbits.R
import thousand.group.healbits.global.constants.simple.AppConstants
import thousand.group.healbits.global.helpers.ResErrorHelper
import thousand.group.healbits.global.presentation.BasePresenter
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.model.simple.Category
import thousand.group.healbits.model.simple.Exercise
import thousand.group.healbits.views.main.interactors.ExerciseInteractor

@InjectViewState
class ExercisePresenter(
    override val context: Context,
    private val resourceManager: ResourceManager,
    private val interactor: ExerciseInteractor
) : BasePresenter<ExerciseView>() {

    private var model: Category? = null

    private var startLoad = false
    private val dimen = resourceManager.getDimension(R.dimen.margin_between_items).toInt()

    override fun internetSuccess() {
    }

    override fun internetError() {
    }

    override fun onStart() {
        viewState.setTitle(R.string.label_exercise)
        viewState.setAdapter()
        refresh()
    }

    override fun onFinish() {
    }

    fun parseBundle(args: Bundle?) {
        args?.apply {
            model = getParcelable(AppConstants.BUNDLE_MODEL)
        }
    }

    fun getExercises() {
        interactor.apply {
            model?.let {
                getExercises(it.id.toInt())
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
    }

    fun refresh() {
        getExercises()
    }

    fun updateOnResume() {
        viewState.setTitle(R.string.label_exercise)
        viewState.setAdapter()
        refresh()
    }

    fun parseListItem(exercise: Exercise) {
        exercise.youtube_url?.apply {
            val appUri = Uri.parse(
                String.format(
                    resourceManager.getString(R.string.format_app_youtube),
                    this
                )
            )
            val webUri = Uri.parse(
                String.format(
                    resourceManager.getString(R.string.format_web_youtube),
                    this
                )
            )


            val appIntent = Intent(Intent.ACTION_VIEW, appUri)
            val webIntent = Intent(Intent.ACTION_VIEW, webUri)

            viewState.openYouTube(appIntent, webIntent)
        }
    }

    private fun showOnError() {
        if (!startLoad) {
            viewState.showEmptyText(true)
            viewState.showSwipe(false)
        }
    }
}