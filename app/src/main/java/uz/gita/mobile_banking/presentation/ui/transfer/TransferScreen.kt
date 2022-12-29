package uz.gita.mobile_banking.presentation.ui.transfer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenTransferBinding
import uz.gita.mobile_banking.presentation.ui.home.pages.accounts.adapter.CardAdapter
import uz.gita.mobile_banking.utils.include

// Created by Jamshid Isoqov on 12/25/2022
@AndroidEntryPoint
class TransferScreen : Fragment(R.layout.screen_transfer) {

    private val viewBinding:ScreenTransferBinding by viewBinding()

    private val cardAdapter: CardAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CardAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {



    }

}