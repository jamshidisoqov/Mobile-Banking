package uz.gita.mobile_banking.presentation.ui.register

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.mobile_banking.R
import uz.gita.mobile_banking.databinding.ScreenRegisterBinding
import uz.gita.mobile_banking.presentation.dialogs.ChooseDateDialog
import uz.gita.mobile_banking.presentation.presenter.RegisterViewModelImpl
import uz.gita.mobile_banking.utils.*
import java.util.*

// Created by Jamshid Isoqov on 12/23/2022
@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {


    private var boolPhone = false

    private var boolPassword = false

    private var boolBornDate = false

    private var boolFirstName = false

    private var boolLastName = false

    private var gender: Int = 0

    private var bornDate = 0L

    private val checker: Boolean
        get() = boolFirstName && boolLastName && boolBornDate && boolPhone && boolPassword

    private val viewBinding: ScreenRegisterBinding by viewBinding()

    private val viewModel: RegisterViewModel by viewModels<RegisterViewModelImpl>()

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

        inputFirstName.textChanges()
            .onEach {
                boolFirstName = it.isNotEmpty()
                check()
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        inputLastName.textChanges()
            .onEach {
                boolLastName = it.isNotEmpty()
                check()
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        inputPassword.textChanges()
            .onEach {
                boolPassword = it.isNotEmpty()
                check()
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        inputPhone.textChanges()
            .onEach {
                boolPhone = it.length >= 17
                check()
            }.launchIn(viewLifecycleOwner.lifecycleScope)


        inputDateOfBirth.clicks()
            .onEach {
                showChooseDate()
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        btnRegister.clicks()
            .onEach {
                if (checker) {
                    val firstName = inputFirstName.text.toString()
                    val lastName = inputLastName.text.toString()
                    val phone = inputPhone.text.toString()
                    val password = inputPassword.text.toString()
                    viewModel.register(
                        firstName = firstName,
                        lastName = lastName,
                        phone = phone,
                        password = password,
                        bornDate = bornDate,
                        gender = gender
                    )
                } else {
                    viewModel.navigateToSignIn()
                }
            }.launchIn(lifecycleScope)
        tvMale.clicks()
            .onEach {
                gender = 0
                imageGenderMale.setImageResource(R.drawable.ic_check)
                imageGenderFeMale.setImageResource(R.drawable.bg_circle)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        tvFeMale.clicks()
            .onEach {
                gender = 1
                imageGenderMale.setImageResource(R.drawable.bg_circle)
                imageGenderFeMale.setImageResource(R.drawable.ic_check)
            }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun showChooseDate() {
        val dialog = ChooseDateDialog(requireContext(), Date())
        dialog.setConfirmClickListener {
            boolBornDate = true
            bornDate = it.time
            viewBinding.inputDateOfBirth.setText(getCurrentDate(it))
            check()
        }
        dialog.show()
    }

    private fun check() = viewBinding.include {
        btnRegister.apply {
            if (checker) {
                backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3862F8"))
                text = resources.getString(R.string.sign_up)
            } else {
                backgroundTintList = ColorStateList.valueOf(Color.parseColor("#717171"))
                text = resources.getString(R.string.sign_in)
            }
        }
    }
}