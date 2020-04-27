package thousand.group.healbits.global.services.api

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import thousand.group.healbits.global.constants.requests.TaskRequest
import thousand.group.healbits.global.constants.requests.UserRequest
import thousand.group.healbits.global.constants.simple.Endpoints
import thousand.group.healbits.model.rest.RestCategory
import thousand.group.healbits.model.rest.RestExercise
import thousand.group.healbits.model.rest.RestTask
import thousand.group.healbits.model.rest.RestUser
import thousand.group.healbits.model.simple.Task
import thousand.group.healbits.model.simple.User

interface ServerService {

    @GET(Endpoints.SIGN_IN)
    fun signIn(
        @Path(UserRequest.phone) phone: String,
        @Path(UserRequest.password) password: String
    ): Single<Response<RestUser>>

    @FormUrlEncoded
    @POST(Endpoints.SIGN_UP)
    fun signUp(@FieldMap params: MutableMap<String, String>): Single<Response<User>>

    @GET(Endpoints.GET_PROFILE_DATA)
    fun getProfile(
        @Path(UserRequest.id) id: String
    ): Single<Response<RestUser>>

    @FormUrlEncoded
    @POST(Endpoints.EDIT_PROFILE_DATA)
    fun edit(
        @Path(UserRequest.id) id: String,
        @FieldMap params: MutableMap<String, String>
    ): Single<Response<User>>

    @GET(Endpoints.GET_CATEGORIES)
    fun getCategories(): Single<Response<RestCategory>>

    @GET(Endpoints.GET_EXERCISE)
    fun getExercises(@Path(UserRequest.id) id: String): Single<Response<RestExercise>>

    @GET(Endpoints.GET_TASKS)
    fun getTasks(
        @Path(TaskRequest.user_id) user_id: String,
        @Path(TaskRequest.c_date) c_date: String
    ): Single<Response<RestTask>>

    @FormUrlEncoded
    @POST(Endpoints.ADD_TASKS)
    fun addTasks(@FieldMap params: MutableMap<String, String>): Single<Response<Task>>

    @FormUrlEncoded
    @POST(Endpoints.CHANGE_TASK_STATUS)
    fun changeTaskStatus(
        @Path(TaskRequest.id) id: String,
        @Field(TaskRequest.status) status: String
    ): Completable

    @DELETE(Endpoints.DELETE_TASKS)
    fun deleteTask(
        @Path(TaskRequest.id) id: String
    ): Completable
}