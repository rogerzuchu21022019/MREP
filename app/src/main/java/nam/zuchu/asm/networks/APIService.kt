package nam.zuchu.asm.networks

import nam.zuchu.asm.models.types.TypesItem
import nam.zuchu.asm.models.users.UsersItem
import nam.zuchu.asm.models.userswithtypes.UsersWithTypesItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    // GET
    // URL: https://asm.congtydacap.club/api/users
    // URL: https://asm.congtydacap.club/api/types
    // URL: https://asm.congtydacap.club/api/users-with-types

    // POST
    // URL: https://asm.congtydacap.club/api/users
    // URL: https://asm.congtydacap.club/api/types
    // URL: https://asm.congtydacap.club/api/users-with-types
    // URL: https://asm.congtydacap.club/api/users
    @GET("api/users")
    suspend fun getAllUsers(): Response<List<UsersItem>>

    @GET("api/types")
    suspend fun getAllTypes(): Response<List<TypesItem>>

    @GET("api/users-with-types")
    suspend fun getAllUsersWithTypes(): Response<List<UsersWithTypesItem>>

    @GET("api/users/{username}")
    suspend fun getUserByUserName(): Response<List<UsersItem>>

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
    ): UsersItem

    @FormUrlEncoded
    @POST("api/types")
    suspend fun createNewType(
        @Field("status") status: String,
        @Field("typeOfName") typeOfName: String
    ): Response<List<TypesItem>>

    @FormUrlEncoded
    @POST("api/users-with-types")
    suspend fun createNewUsersWithTypes(
        @Field("date") date: String,
        @Field("idType") idType: Int,
        @Field("description") description: String,
        @Field("totalMoney") totalMoney: Double,
        @Field("userName") userName: String,
    ): Response<List<UsersWithTypesItem>>
}