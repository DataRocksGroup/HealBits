package thousand.group.healbits.global.services.api

interface ServerService {

//    @Multipart
//    @POST(Endpoints.SIGN_IN)
//    fun signIn(@PartMap params: MutableMap<String, RequestBody>): Single<Response<User>>
//
//    @Multipart
//    @POST(Endpoints.SIGN_UP)
//    fun signUp(@PartMap params: MutableMap<String, RequestBody>): Single<Response<User>>
//
//    @POST(Endpoints.RESEND_CODE)
//    fun resendCode(@Query(UserRequest.phone) phone: String): Completable
//
//    @Multipart
//    @POST(Endpoints.VERIFY_CODE)
//    fun verifyCode(@PartMap params: MutableMap<String, RequestBody>): Single<Response<User>>
//
//    @Multipart
//    @POST(Endpoints.USER_EDIT)
//    fun uploadPhoto(@Part image: MultipartBody.Part): Single<Response<User>>
//
//    @Multipart
//    @POST(Endpoints.USER_EDIT)
//    fun userEdit(@PartMap params: MutableMap<String, RequestBody>): Single<Response<User>>
//
//    @Multipart
//    @POST(Endpoints.USER_EDIT)
//    fun userEditWithNullParams(@PartMap params: MutableMap<String, RequestBody?>): Single<Response<User>>
//
//    @POST(Endpoints.GET_RESET_PASSWORD_CODE)
//    fun getResetPasswordCode(@Query(UserRequest.phone) phone: String): Single<Response<User>>
//
//    @Multipart
//    @POST(Endpoints.CHECK_RESET_PASSWORD_CODE)
//    fun checkResetPasswordCode(@PartMap params: MutableMap<String, RequestBody>): Single<Response<User>>
//
//    @POST(Endpoints.CHANGE_PASSWORD)
//    fun changePassword(@Query(UserRequest.new_password) new_password: String): Single<Response<User>>
//
//    @GET(Endpoints.GET_BASKET_COUNT)
//    fun getBasketCount(): Single<Response<User>>
//
//    @GET(Endpoints.AUTH)
//    fun auth(): Single<Response<User>>
//
//    @GET(Endpoints.GET_MAIN_LIST)
//    fun getMainList(): Single<Response<MainModel>>
//
//    @GET(Endpoints.GET_MOST_PAYED)
//    fun getMostPayed(): Single<Response<Section>>
//
//    @GET(Endpoints.GET_SUPPORT_PHONE)
//    fun getSupportPhone(): Single<Response<ValueModel>>
//
//    @GET(Endpoints.GET_GOOD_DETAIL)
//    fun getGoodDetail(@Path(GoodRequest.id) id: String): Single<Response<GoodDetail>>
//
//    @POST(Endpoints.FAVOURITE_CONTROL)
//    fun checkHeart(@Path(GoodRequest.id) id: String): Single<Response<GoodDetail>>
//
//    @FormUrlEncoded
//    @POST(Endpoints.BASKET_ADD)
//    fun basketAdd(
//        @Field(GoodRequest.product_id) product_id: String,
//        @Field(GoodRequest.count) count: String
//    ): Completable
//
//    @GET(Endpoints.GET_BASKET_LIST)
//    fun getBasketList(): Single<Response<ResBasketGoodOut>>
//
//    @DELETE(Endpoints.DELETE_BASKET_ITEM)
//    fun deleteBasketItem(@Query(GoodRequest.product_id) id: String): Completable
//
//    @FormUrlEncoded
//    @POST(Endpoints.UPDATE_BASKET_ITEM_COUNT)
//    fun updateBasketItemCount(
//        @Field(GoodRequest.product_id) product_id: String,
//        @Field(GoodRequest.count) count: String
//    ): Single<Response<BasketGood>>
//
//    @GET(Endpoints.GET_CARDS)
//    fun getCards(): Single<Response<MutableList<PaymentCard>>>
//
//    @GET(Endpoints.GET_PICKUP_ADDRESS)
//    fun getPickupAddress(): Single<Response<ValueModel>>
//
//    @Multipart
//    @POST(Endpoints.CREATE_ORDER)
//    fun createOrder(@PartMap params: MutableMap<String, RequestBody>): Single<Response<CombinedBonusOrder>>
//
//    @GET(Endpoints.SEARCH_GOOD)
//    fun searchGood(@QueryMap params: MutableMap<String, String>): Single<Pagination>
//
//    @GET(Endpoints.GET_SECTION_NAMES)
//    fun getSectionNames(): Single<Response<MutableList<SectionName>>>
//
//    @GET(Endpoints.GET_ORDERS)
//    fun getOrders(@Query(GoodRequest.page) page: String): Single<Pagination>
//
//    @GET(Endpoints.GET_ORDER_DETAIL)
//    fun getOrderDetail(@Path(OrderRequest.id) id: String): Single<Response<OrderDetail>>
//
//    @GET(Endpoints.COURIER_SHOW)
//    fun courierShow(@Path(UserRequest.id) id: String): Single<Response<CourierUser>>
//
//    @GET(Endpoints.GET_FAVOURITES)
//    fun getFavourites(@Query(GoodRequest.page) page: String): Single<Pagination>
//
//    @POST(Endpoints.CREATE_COMMENT)
//    fun createComment(
//        @Path(UserRequest.id) id: String,
//        @QueryMap params: MutableMap<String, String>
//    ): Single<Response<Comment>>
//
//    @GET(Endpoints.GET_COMMENTS)
//    fun getComments(
//        @Path(UserRequest.id) id: String,
//        @Query(GoodRequest.page) page: String
//    ): Single<Pagination>
//
//    @GET(Endpoints.GET_ADDRESSES)
//    fun getAddresses(): Single<Response<MutableList<Address>>>
//
//    @DELETE(Endpoints.DELETE_ADDRESS)
//    fun deleteAddress(@Path(UserRequest.id) id: String): Completable
//
//    @Multipart
//    @POST(Endpoints.CREATE_ADDRESS)
//    fun createAddress(@PartMap params: MutableMap<String, RequestBody>): Completable
//
//    @DELETE(Endpoints.DELETE_CARDS)
//    fun deleteCards(@Path(UserRequest.id) id: String): Completable
//
//    @Multipart
//    @POST(Endpoints.CREATE_CARD)
//    fun createCard(@PartMap params: MutableMap<String, RequestBody>): Completable
//
//    @GET(Endpoints.SIGN_OUT)
//    fun signOut(): Completable


}