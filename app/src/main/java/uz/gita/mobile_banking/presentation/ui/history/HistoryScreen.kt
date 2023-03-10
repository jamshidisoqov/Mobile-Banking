package uz.gita.mobile_banking.presentation.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenHistoryBinding
import uz.gita.mobile_banking.presentation.presenter.HistoryViewModelImpl
import uz.gita.mobile_banking.presentation.ui.history.adapter.HistoryAdapter
import uz.gita.mobile_banking.utils.*

// Created by Jamshid Isoqov on 1/3/2023
@AndroidEntryPoint
class HistoryScreen : Fragment(R.layout.screen_history) {

    private val viewBinding: ScreenHistoryBinding by viewBinding()

    private val viewModel: HistoryViewModel by viewModels<HistoryViewModelImpl>()

    private val adapter: HistoryAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HistoryAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {
        listHistory.adapter = adapter

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.transferHistoryFlow.onEach {
            adapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.getTransfers()

    }

}