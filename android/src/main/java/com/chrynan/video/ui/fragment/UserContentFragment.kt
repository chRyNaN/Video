package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chrynan.video.ui.view.UserContentView
import com.chrynan.video.R

class UserContentFragment : Fragment(),
    UserContentView {

    companion object {

        fun newInstance() = UserContentFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_user_content, container, false)
}