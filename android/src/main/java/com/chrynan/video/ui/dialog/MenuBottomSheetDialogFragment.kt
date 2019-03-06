package com.chrynan.video.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import com.chrynan.video.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.dialog_menu_bottom_sheet.*

class MenuBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {

        private const val KEY_MENU_RES_ID = "menuResIdKey"

        fun newInstance(@MenuRes menuResId: Int) = MenuBottomSheetDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_MENU_RES_ID, menuResId)
            }
        }
    }

    var navigationListener: NavigationView.OnNavigationItemSelectedListener? = null

    private val menuResId by lazy { arguments?.getInt(KEY_MENU_RES_ID) ?: 0 }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_menu_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navigationView?.apply {
            inflateMenu(menuResId)
            setNavigationItemSelectedListener { menuItem ->
                navigationListener?.run {
                    onNavigationItemSelected(menuItem)

                    false
                } ?: false
            }
        }
    }
}