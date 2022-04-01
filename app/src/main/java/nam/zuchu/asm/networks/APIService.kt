package nam.zuchu.asm.networks

import nam.zuchu.asm.models.types.TypesItem
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

    @GET("api/users")
    suspend fun getUserByUserName(
        @Query("username") userName: String
    ): Response<UsersItem>

    @GET("api/types")
    suspend fun getTypeByStatus(
        @Query("status") status: String
    ): Response<List<TypesItem>>


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
        @Field("typeOfName") typeOfName: String,
        @Field("status") status: String
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
}