package nam.zuchu.asm.models.users

import com.google.gson.annotations.Expose

data class UsersItem(
    val UsersWithTypes: List<Any>,
    @Expose
    val age: Int,
    @Expose
    val avartar: String,
    @Expose
    val email: String,
    @Expose
    val fullName: String,
    @Expose
    val password: String,
    @Expose
    val phone: String,
    @Expose
    val userName: String
)