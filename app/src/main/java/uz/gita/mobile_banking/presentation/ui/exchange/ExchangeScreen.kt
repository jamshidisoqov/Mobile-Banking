package uz.gita.mobile_banking.presentation.ui.exchange

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenExchangeBinding
import uz.gita.mobile_banking.presentation.presenter.ExchangeViewModelImpl
import uz.gita.mobile_banking.presentation.ui.exchange.adapter.ExchangeAdapter
import uz.gita.mobile_banking.utils.*

// Created by Jamshid Isoqov on 1/1/2023

@AndroidEntryPoint
class ExchangeScreen : Fragment(R.layout.screen_exchange) {

    private val viewModel: ExchangeViewModel by viewModels<ExchangeViewModelImpl>()

    private val viewBinding: ScreenExchangeBinding by viewBinding()

    private val adapter: ExchangeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ExchangeAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        imageBack.clicks()
            .onEach {
                findNavController().navigateUp()
            }.launchIn(lifecycleScope)

        listExchanges.adapter = adapter

        viewModel.allExchanges.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.getAllExchanges()

    }

}