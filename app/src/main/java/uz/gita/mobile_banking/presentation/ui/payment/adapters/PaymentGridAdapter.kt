package uz.gita.mobile_banking.presentation.ui.payment.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ListItemPaymentGridBinding
import uz.gita.mobile_banking.domain.models.enums.PaymentType
import uz.gita.mobile_banking.utils.getOrderString
import uz.gita.mobile_banking.utils.include
import uz.gita.mobile_banking.utils.inflate

// Created by Jamshid Isoqov on 1/5/2023


class PaymentGridAdapter :
    ListAdapter<PaymentType, PaymentGridAdapter.ViewHolder>(paymentItemCallback) {

    private var itemClickListener: ((PaymentType) -> Unit)? = null

    fun setItemClickListener(block: (PaymentType) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemPaymentGridBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() = binding.include {
            val data = getItem(absoluteAdapterPosition)
            tvPayment.text = data.name.getOrderString()
            imagePayment.setImageResource(data.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemPaymentGridBinding.bind(parent.inflate(R.layout.list_item_payment_grid)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

}


