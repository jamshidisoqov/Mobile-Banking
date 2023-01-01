package uz.gita.mobile_banking.presentation.ui.home.pages.accounts.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ListItemMenuBinding
import uz.gita.mobile_banking.utils.MoreMenuData
import uz.gita.mobile_banking.utils.include
import uz.gita.mobile_banking.utils.inflate

// Created by Jamshid Isoqov on 1/1/2023

private val menuItemCallback = object : DiffUtil.ItemCallback<MoreMenuData>() {
    override fun areItemsTheSame(oldItem: MoreMenuData, newItem: MoreMenuData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MoreMenuData, newItem: MoreMenuData): Boolean =
        oldItem.imageId == newItem.imageId && oldItem.menu == newItem.menu

}

class MenuAdapter : ListAdapter<MoreMenuData, MenuAdapter.ViewHolder>(menuItemCallback) {

    private var itemClickListener: ((MoreMenuData) -> Unit)? = null

    fun setItemClickListener(block: (MoreMenuData) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() = binding.include {
            val data = getItem(absoluteAdapterPosition)
            imageDetail.setImageResource(data.imageId)
            tvDetailName.text = data.menu.menuName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemMenuBinding.bind(parent.inflate(R.layout.list_item_menu))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

}