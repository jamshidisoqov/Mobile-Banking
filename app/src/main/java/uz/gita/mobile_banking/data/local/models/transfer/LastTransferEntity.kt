package uz.gita.mobile_banking.data.local.models.transfer

data class LastTransferEntity(
    val amount: Int,
    val from: String,
    val time: Long,
    val to: String,
    val type: String
)