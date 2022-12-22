package uz.gita.mobile_banking.data.remote.response.user

data class LastTransferData(
    val amount: Int,
    val from: String,
    val time: Long,
    val to: String,
    val type: String
)