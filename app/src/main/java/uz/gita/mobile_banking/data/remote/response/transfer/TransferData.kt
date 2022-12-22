package uz.gita.mobile_banking.data.remote.response.transfer

data class TransferData(
    val amount: Int,
    val from: String,
    val time: Long,
    val to: String,
    val type: String
)