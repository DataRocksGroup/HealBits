package thousand.group.healbits.global.constants.simple

object Endpoints {
    internal const val SIGN_IN = "users/login/{phone}/{password}"
    internal const val SIGN_UP = "users/register"
    internal const val GET_PROFILE_DATA = "users/profile/{id}"
    internal const val EDIT_PROFILE_DATA = "users/edit/profile/{id}"
    internal const val GET_CATEGORIES = "category_of_exercises/get"
    internal const val GET_EXERCISE = "exercises/{id}"
    internal const val GET_TASKS = "tasks/{user_id}/{c_date}"
    internal const val ADD_TASKS = "tasks/addtask"
    internal const val CHANGE_TASK_STATUS = "tasks/edit/status/{id}"
}