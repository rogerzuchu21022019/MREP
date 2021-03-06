package nam.zuchu.asm.networks

import nam.zuchu.asm.models.types.TypesItem
import nam.zuchu.asm.models.users.Users
import nam.zuchu.asm.models.users.UsersItem
import nam.zuchu.asm.models.userswithtypes.UsersWithTypesItem
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    // TODO: GET
    // TODO: URL: https://asm.congtydacap.club/api/users
    // TODO: URL: https://asm.congtydacap.club/api/types
    // TODO: URL: https://asm.congtydacap.club/api/users-with-types
    // TODO: GET BY ID

    // TODO: URL: https://asm.congtydacap.club/api/users/id
    // TODO: URL: https://asm.congtydacap.club/api/types/id
    // TODO: POST

    // TODO: URL: https://asm.congtydacap.club/api/users
    // TODO: URL: https://asm.congtydacap.club/api/types
    // TODO: URL: https://asm.congtydacap.club/api/users-with-types
    // TODO: URL: https://asm.congtydacap.club/api/users

    // TODO: Update
    // TODO: URL: https://asm.congtydacap.club/api/update-type
    @GET("api/users")
    suspend fun getAllUsers(): Response<List<UsersItem>>

    @GET("api/types")
    suspend fun getAllTypes(): Response<List<TypesItem>>

    @GET("api/users-with-types")
    suspend fun getAllUsersWithTypes(): Response<List<UsersWithTypesItem>>



    @GET("api/types")
    suspend fun getTypeByStatus(
        @Query("status") status: String
    ): Response<List<TypesItem>>

    @GET("api/users")
    suspend fun getUsers(
        @Query("username") username: String
    ) :Response<UsersItem>

    // TODO: POST
    @FormUrlEncoded
    @POST("api/users")
    suspend fun createNewUser(
        @Field("userName") userName: String,
        @Field("age") age: Int,
        @Field("email") email: String,
        @Field("avartar") avartar: String,
        @Field("password") password: String,
        @Field("phone") phone: String,
        @Field("fullName") fullName: String,
    ): Response<UsersItem>

    @FormUrlEncoded
    @POST("api/types")
    suspend fun createNewType(
        @Field("status") status: String,
        @Field("typeOfName") typeOfName: String
    ): Response<TypesItem>

    @FormUrlEncoded
    @POST("api/users-with-types")
    suspend fun createNewUsersWithTypes(
        @Field("date") date: String,
        @Field("idType") idType: Int,
        @Field("description") description: String,
        @Field("totalMoney") totalMoney: Double,
        @Field("userName") userName: String,
    ): Response<UsersWithTypesItem>


    // TODO: DELETE
    @DELETE("api/delete-type/{id}")
    suspend fun deleteTypeOfExpenditure(
        @Path("id") id:Int
    )

    @DELETE("api/delete-detail/{id}/{userName}")
    suspend fun deleteDetail(
        @Path("id") id:Int,
        @Path("userName") userName: String
    )
}