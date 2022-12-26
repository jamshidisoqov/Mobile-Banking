package uz.gita.mobile_banking.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import uz.gita.mobile_banking.MainActivity
import uz.gita.mobile_banking.presentation.dialogs.ErrorDialog
import uz.gita.mobile_banking.presentation.dialogs.MessageDialog

// Created by Jamshid Isoqov on 12/12/2022


fun Fragment.hasPermission(
    permission: String,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    Dexter.withContext(requireContext())
        .withPermission(permission).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                onPermissionGranted.invoke()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                onPermissionDenied.invoke()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
                p1?.continuePermissionRequest()
            }
        }).check()
}

fun Fragment.hasPermission(
    permissions: List<String>,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    Dexter.withContext(requireContext())
        .withPermissions(permissions).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                if (permissions.size == p0?.grantedPermissionResponses?.size) {
                    onPermissionGranted.invoke()
                } else {
                    onPermissionDenied.invoke()
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
                p1?.continuePermissionRequest()
            }

        })
}


fun Fragment.hasPermission(permission: String): Boolean {
    return requireActivity().applicationContext.hasPermission(permission)
}

fun Context.hasPermission(permission: String): Boolean {
    if (permission == Manifest.permission.ACCESS_BACKGROUND_LOCATION &&
        android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q
    ) return true

    return ActivityCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}

fun Fragment.isLocationEnabled(): Boolean {
    val locationManager =
        requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER
    )
}

fun Fragment.hideProgress() {
    (requireActivity() as MainActivity).hideProgress()
}

fun Fragment.showProgress() {
    (requireActivity() as MainActivity).showProgress()
}

fun Fragment.showErrorDialog(message: String) {
    val dialog = ErrorDialog(requireContext(), message)
    dialog.show()
}

fun Fragment.showMessageDialog(message: String) {
    val dialog = MessageDialog(requireContext(), message)
    dialog.show()

}


fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}


