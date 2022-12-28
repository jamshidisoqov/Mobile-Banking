package uz.gita.mobile_banking.presentation.ui.card.add

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
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenAddCardBinding
import uz.gita.mobile_banking.presentation.dialogs.ChooseDateDialog
import uz.gita.mobile_banking.presentation.presenter.AddCardViewModelImpl
import uz.gita.mobile_banking.utils.*
import java.util.*

// Created by Jamshid Isoqov on 12/25/2022
@AndroidEntryPoint
class AddCardScreen : Fragment(R.layout.screen_add_card) {


    private val viewBinding: ScreenAddCardBinding by viewBinding()

    private val boolName: Boolean = false

    private val boolCardNumber: Boolean = false

    private var bornDate = 0L


    private val viewModel: AddCardViewModel by viewModels<AddCardViewModelImpl>()

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

        viewModel.backSharedFlow.onEach {
            toast(it)
            findNavController().navigateUp()
        }.launchIn(lifecycleScope)

        inputCardName.textChanges().onEach {

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        inputCardNumber.textChanges().onEach {

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        inputExpireDate.clicks().onEach {
            showChooseDate()
        }.launchIn(lifecycleScope)
    }

    private fun showChooseDate() = viewBinding.include {
        val dialog = ChooseDateDialog(requireContext(), Date())
        dialog.setConfirmClickListener {
            bornDate = it.time
            inputExpireDate.setText(getCurrentDate(it))
        }
        dialog.show()
    }

}