package uz.gita.mobile_banking.presentation.ui.exchange.adapter

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.data.remote.response.exchange.ExchangeData
import uz.gita.mobile_banking.databinding.ListItemExchangeBinding
import uz.gita.mobile_banking.utils.combine
import uz.gita.mobile_banking.utils.include
import uz.gita.mobile_banking.utils.inflate

// Created by Jamshid Isoqov on 1/1/2023
private var exchangeItemCallback = object : DiffUtil.ItemCallback<ExchangeData>() {
    override fun areItemsTheSame(oldItem: ExchangeData, newItem: ExchangeData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ExchangeData, newItem: ExchangeData): Boolean =
        oldItem.id == newItem.id &&
                oldItem.code == newItem.code &&
                oldItem.exchangeEngName == newItem.exchangeEngName &&
                oldItem.date == newItem.date

}

class ExchangeAdapter :
    ListAdapter<ExchangeData, ExchangeAdapter.ViewHolder>(exchangeItemCallback) {
    inner class ViewHolder(private val binding: ListItemExchangeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() = binding.include {

            val data = getItem(absoluteAdapterPosition)

            tvEngName.apply {
                text = data.exchangeEngName
                setSingleLine()
                isSelected = true
            }

            tvDate.text = data.date

            tvRate.text =
                "1".combine(data.exchangeShortName)
                    .combine("=")
                    .combine(data.rate)
                    .combine("UZS")

            tvDiffPercent.apply {
                if (data.status.toDouble() < 0) {
                    setTextColor(Color.parseColor("#F44336"))
                    imageCurrencyStatus.setImageResource(R.drawable.ic_down)
                } else {
                    imageCurrencyStatus.setImageResource(R.drawable.ic_up)
                    setTextColor(Color.parseColor("#4CAF50"))
                }
                text = data.status
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemExchangeBinding.bind(parent.inflate(R.layout.list_item_exchange)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}