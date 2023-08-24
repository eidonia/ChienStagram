package com.exalt.home.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eXalt.chienstagram.R
import com.exalt.home.adapters.PostListAdapter
import com.exalt.home.extensions.handleVisibility
import com.exalt.home.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var postRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postRecyclerView = requireView().findViewById(R.id.posts_list)
        initViews()
        initObservers()
    }

    private fun initViews() {
        postRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PostListAdapter(this@HomeFragment.requireContext()).apply {
                onUserClick = { userId ->
                    val uri = Uri.parse("App://com.exalt.profile/$userId")
                    val request = NavDeepLinkRequest.Builder
                        .fromUri(uri)
                        .build()
                    findNavController().navigate(request)
                }
                onPostClick = { postId ->
                    val uri = Uri.parse("App://com.exalt.post/$postId")
                    val request = NavDeepLinkRequest.Builder
                        .fromUri(uri)
                        .build()
                    findNavController().navigate(request)
                }
            }
        }
    }

    private fun initObservers() {
        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            requireView().findViewById<ProgressBar>(R.id.progress_home).handleVisibility(it)
        }
        homeViewModel.posts.observe(viewLifecycleOwner) {
            (postRecyclerView.adapter as PostListAdapter).submitList(it)
        }
    }
}