package com.exalt.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.exalt.profile.R
import com.exalt.profile.ui.compose.ProfilePage
import com.exalt.profile.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilePageFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    private var userId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString("userId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews(view)
    }

    private fun initObservers() {
        viewModel.getUser(userId!!)
    }


    private fun initViews(view: View) {
        viewModel.userState.observe(viewLifecycleOwner) { user ->
            view.findViewById<ComposeView>(R.id.compose_view).setContent {
                ProfilePage(
                    isLoading = user.isLoading,
                    user = user.data
                ) {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }
}