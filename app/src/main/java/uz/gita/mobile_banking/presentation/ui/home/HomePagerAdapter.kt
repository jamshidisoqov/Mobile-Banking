package uz.gita.mobile_banking.presentation.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.mobile_banking.presentation.ui.home.pages.accounts.AccountsScreen
import uz.gita.mobile_banking.presentation.ui.home.pages.cashbacks.CashbackScreen
import uz.gita.mobile_banking.presentation.ui.home.pages.settings.SettingsScreen
import uz.gita.mobile_banking.presentation.ui.home.pages.statistics.StatisticsScreen

// Created by Jamshid Isoqov on 12/27/2022
class HomePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> AccountsScreen()
            2 -> StatisticsScreen()
            3 -> CashbackScreen()
            else -> SettingsScreen()
        }
}