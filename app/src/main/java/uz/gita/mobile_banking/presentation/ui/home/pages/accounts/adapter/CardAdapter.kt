package uz.gita.mobile_banking.presentation.ui.home.pages.accounts.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.data.remote.response.card.CardData
import uz.gita.mobile_banking.databinding.ListItemCardsBinding
import uz.gita.mobile_banking.utils.getFinanceType
import uz.gita.mobile_banking.utils.include
import uz.gita.mobile_banking.utils.inflate

// Created by Jamshid Isoqov on 12/28/2022


private val cardItemCallback = object : DiffUtil.ItemCallback<CardData>() {
    override fun areItemsTheSame(oldItem: CardData, newItem: CardData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: CardData, newItem: CardData): Boolean =
        oldItem.id == newItem.id && oldItem.isVisible == newItem.isVisible
                && oldItem.amount == newItem.amount && oldItem.expiredMonth == newItem.expiredMonth
                && oldItem.expiredYear == newItem.expiredYear && oldItem.name == newItem.name
                && oldItem.owner == newItem.owner && oldItem.themeType == newItem.themeType

}

class CardAdapter : ListAdapter<CardData, CardAdapter.ViewHolder>(cardItemCallback) {


    private var itemClickListener: ((CardData) -> Unit)? = null

    fun setItemClick(block: (CardData) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemCardsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBind() = binding.include {
            val data = getItem(absoluteAdapterPosition)
            tvCardNumber.text = data.name
            tvCardExpireDate.text = "${data.expiredMonth}/${data.expiredYear}"
            tvBalance.text = data.amount.toDouble().getFinanceType()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemCardsBinding.bind(parent.inflate(R.layout.list_item_cards)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

}