package uz.gita.mobile_banking.presentation.ui.history.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.data.remote.response.transfer.TransferData
import uz.gita.mobile_banking.databinding.ListItemHistoryBinding
import uz.gita.mobile_banking.utils.getCurrentDate
import uz.gita.mobile_banking.utils.getFinanceType
import uz.gita.mobile_banking.utils.include
import uz.gita.mobile_banking.utils.inflate
import java.util.*

// Created by Jamshid Isoqov on 1/3/2023

private val transferItemCallback = object : DiffUtil.ItemCallback<TransferData>() {
    override fun areItemsTheSame(oldItem: TransferData, newItem: TransferData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: TransferData, newItem: TransferData): Boolean =
        oldItem.amount == newItem.amount &&
                oldItem.to == newItem.to &&
                oldItem.time == newItem.time

}

class HistoryAdapter :
    PagingDataAdapter<TransferData, HistoryAdapter.ViewHolder>(transferItemCallback) {

    private var itemClickListener: ((TransferData) -> Unit)? = null


    fun setItemClickListener(block: (TransferData) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.apply {
                    itemClickListener?.invoke(this)
                }
            }
        }

        fun onBind() = binding.include {
            val data = getItem(absoluteAdapterPosition)
            data?.apply {
                tvTitle.text = to
                tvAmount.text = amount.toDouble().getFinanceType()
                tvDate.text = getCurrentDate(Date(time))
                tvHistoryType.text = type
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemHistoryBinding.bind(parent.inflate(R.layout.list_item_history)))

}