package uz.gita.mobile_banking.presentation.ui.pin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenPinBinding
import uz.gita.mobile_banking.presentation.presenter.PinViewModelImpl
import uz.gita.mobile_banking.utils.include
import uz.gita.mobile_banking.utils.toast

// Created by Jamshid Isoqov on 12/23/2022
@AndroidEntryPoint
class PinScreen : Fragment(R.layout.screen_pin) {

    private val viewBinding: ScreenPinBinding by viewBinding()

    private val viewModel: PinViewModel by viewModels<PinViewModelImpl>()

    private var isCompleted: Boolean = true

    private var isFirst: Boolean = true

    private var oldPassword = ""

    private var counter = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        pinView.textChanges()
            .onEach {
                isCompleted = it.length == 4
                check()
            }.launchIn(viewLifecycleOwner.lifecycleScope)


    }

    private fun check() = viewBinding.include {
        if (isCompleted) {
            if (counter == 0) {
                oldPassword = pinView.text.toString()
                if (isFirst) {
                    counter++
                    pinView.setText("")
                    tvInputPinCode.text = resources.getString(R.string.confirm_new_password)
                } else {
                    viewModel.check(oldPassword)
                }
            } else {
                val password: String = pinView.text.toString()
                if (password == oldPassword) {
                    viewModel.savePassword(password)
                } else {
                    toast("Incorrect password!!!")
                }
            }
        }
    }

}