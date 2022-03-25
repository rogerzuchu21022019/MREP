package nam.zuchu.asm.models.userswithtypes

data class UsersWithTypesItem(
    val Type: Any,
    val User: Any,
    val date: String,
    val description: String,
    val idType: Int,
    val stt: Int,
    val totalMoney: Double,
    val userName: String
)