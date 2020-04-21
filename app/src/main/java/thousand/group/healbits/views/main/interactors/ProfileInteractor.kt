package thousand.group.healbits.views.main.interactors

import thousand.group.azimutgas.views.main.repositories.profile.ProfileRepository
import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider

class ProfileInteractor(
    private val repository: ProfileRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
)