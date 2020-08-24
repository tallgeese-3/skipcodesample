package lightcycleconsulting.com.skipcodeexample.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.product.*

abstract class BaseFragment: Fragment() {
    @SuppressLint("NewApi")
    protected fun adjustToolbarMarginForNotch() {
        // Notch is only supported by >= Android 9
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val windowInsets = activity?.window?.decorView?.rootWindowInsets
            if (windowInsets != null) {
                val displayCutout = windowInsets.displayCutout
                if (displayCutout != null) {
                    val safeInsetTop = displayCutout.safeInsetTop
                    val newLayoutParams = toolbar.layoutParams as ViewGroup.MarginLayoutParams
                    newLayoutParams.setMargins(0, safeInsetTop, 0, 0)
                    toolbar.layoutParams = newLayoutParams
                }
            }
        }
    }
}