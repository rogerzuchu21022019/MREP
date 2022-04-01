package nam.zuchu.asm.models.userswithtypes

import com.google.gson.annotations.Expose

data class UsersWithTypesItem(
    val Type: Any,
    val User: Any,
    @Expose
    val date: String,
    @Expose
    val description: String,
    @Expose
    val idType: Int,
    @Expose
    val stt: Int,
    @Expose
    val totalMoney: Double,
    @Expose
    val userName: String
)