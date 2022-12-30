package uz.gita.mobile_banking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.mobile_banking.navigation.NavigationHandler
import uz.gita.mobile_banking.presentation.dialogs.ProgressDialog
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    private lateinit var dialog: ProgressDialog

    private var job: Job? = null

    private var isOpenPin = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navigationHandler.navigationStack
            .onEach { it.invoke(fragment.findNavController()) }
            .launchIn(lifecycleScope)
        dialog = ProgressDialog(this)
    }

    fun showProgress() {
        dialog.show()
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
        job = lifecycleScope.launch {
            delay(5000)
            isOpenPin = true
        }
    }

    override fun onResume() {
        super.onResume()
        job?.cancel()
        if (isOpenPin) {
            isOpenPin = false
            isFirstApp = false
            findNavController(R.id.fragmentContainerView).navigate(R.id.action_global_pinScreen)
        }
    }


    fun hideProgress() {
        dialog.cancel()
    }

    companion object {
        var isFirstApp = true
    }
}