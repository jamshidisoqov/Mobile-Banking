package uz.gita.mobile_banking.presentation.ui.login

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
import uz.gita.mobile_banking.databinding.ScreenLoginBinding
import uz.gita.mobile_banking.presentation.presenter.LoginViewModelImpl
import uz.gita.mobile_banking.utils.*

// Created by Jamshid Isoqov on 12/23/2022
@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private var boolPhone = false

    private var boolPassword = false

    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    private val viewBinding: ScreenLoginBinding by viewBinding()

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
        inputPhone.textChanges().onEach {
            boolPhone = it.length >= 17
            check()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        inputPassword.textChanges().onEach {
            boolPassword = it.length > 5
            check()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        tvForgetPassword.clicks()
            .onEach {
                viewModel.forgetPassword()
            }.launchIn(lifecycleScope)

        btnLogin.clicks()
            .onEach {
                if (boolPassword && boolPhone) {
                    val phone = inputPhone.text.toString()
                    val password = inputPassword.text.toString()
                    viewModel.login(phone, password)
                } else {
                    viewModel.navigateToRegister()
                }
            }.launchIn(lifecycleScope)

    }

    private fun check() = viewBinding.include {
        btnLogin.apply {
            if (boolPassword && boolPhone) {
                backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3862F8"))
                text = resources.getString(R.string.sign_in)
            } else {
                backgroundTintList = ColorStateList.valueOf(Color.parseColor("#717171"))
                text = resources.getString(R.string.sign_up)
            }
        }
    }
}