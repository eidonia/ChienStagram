package com.exalt.post.fragments

import android.net.Uri
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.exalt.post.adapters.CommentPostAdapter
import com.exalt.post.viewmodels.DetailedPostViewModel
import com.example.post.R
import com.example.post.databinding.FragmentPostBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class PostFragment : Fragment() {

    private val detailedPostViewModel: DetailedPostViewModel by viewModels()

    private var postId: String? = null
    private lateinit var binding: FragmentPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            postId = it.getString("postId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    private fun initViews() {
        binding.commentRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CommentPostAdapter(context).apply {
                onUserClick = { userId ->
                    val uri = Uri.parse("App://com.exalt.profile/$userId")
                    val request = NavDeepLinkRequest.Builder
                        .fromUri(uri)
                        .build()
                    findNavController().navigate(request)

                }

            }
        }
        binding.topBar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.buttonRetry.setOnClickListener {
            detailedPostViewModel.apply {
                getPost(postId!!)
                getComment(postId!!)
            }
            binding.textError.isGone = true
            binding.buttonRetry.isGone = true
        }
    }

    private fun initObservers() {
        detailedPostViewModel.apply {
            getPost(postId!!)
            getComment(postId!!)
        }
        detailedPostViewModel.postState.observe(viewLifecycleOwner) { postFlow ->
            visibilityUI(postFlow.isLoading)
            postFlow.post?.let { post ->
                with(post) {
                    Glide.with(requireContext())
                        .load(imageUri)
                        .into(binding.imagePost)

                    Glide.with(requireContext())
                        .load(ownerPictureUri)
                        .circleCrop()
                        .into(binding.imageUser)

                    binding.constraintNameUser.setOnClickListener {
                        val uri = Uri.parse("App://com.exalt.profile/$ownerId")
                        val request = NavDeepLinkRequest.Builder
                            .fromUri(uri)
                            .build()
                        findNavController().navigate(request)
                    }

                    binding.textDatePost.text = publishDate
                    binding.textNameUser.text = ownerName
                    binding.textPost.text = text

                    binding.chipTag.apply {
                        text = if (tags.isNotEmpty()) {
                            isVisible = true
                            tags.size.toString()
                        } else {
                            isVisible = false
                            ""
                        }
                    }

                    binding.chipLike.apply {
                        text = if (likes != 0) {
                            isVisible = true
                            likes.toString()
                        } else {
                            isVisible = false
                            ""
                        }
                    }
                    createTagsChips(tags)
                }
            } ?: kotlin.run {
                binding.textError.apply {

                    text = if (!postFlow.isLoading) {
                        setUIInvisible()
                        isVisible = true
                        postFlow.message ?: "erreur, rechargez la page"
                    } else {
                        isVisible = false
                        ""
                    }
                }
                binding.buttonRetry.isVisible = !postFlow.isLoading
            }
        }

        detailedPostViewModel.commentState.observe(viewLifecycleOwner) {
            val comments = it.data
            binding.chipComment.apply {
                isVisible = comments.isNotEmpty()
                text = comments.size.toString()
            }
            (binding.commentRecycler.adapter as CommentPostAdapter).submitList(comments)
        }
    }

    private fun createTagsChips(tags: List<String>) {
        val constraintSet = ConstraintSet()
        val layoutParams = binding.chipGroupTags.layoutParams
        layoutParams?.width = LayoutParams.WRAP_CONTENT
        layoutParams?.height = LayoutParams.WRAP_CONTENT
        binding.chipGroupTags.layoutParams = layoutParams
        constraintSet.apply {
            clone(binding.constraintInfotags)

            if (tags.size > 2) {
                connect(R.id.chipGroupTags, ConstraintSet.START, R.id.chipGroupInfoPost, ConstraintSet.START)
                connect(R.id.chipGroupTags, ConstraintSet.TOP, R.id.chipGroupInfoPost, ConstraintSet.BOTTOM)
            } else {
                connect(R.id.chipGroupTags, ConstraintSet.END, R.id.constraintInfotags, ConstraintSet.END)
                connect(R.id.chipGroupTags, ConstraintSet.TOP, R.id.chipGroupInfoPost, ConstraintSet.TOP)
            }
            applyTo(binding.constraintInfotags)
        }

        val newContext = ContextThemeWrapper(requireContext(), com.google.android.material.R.style.Theme_MaterialComponents)

        for (tag in tags) {
            val chip = Chip(newContext)
            chip.text = tag
            chip.isClickable = false
            chip.setChipBackgroundColorResource(R.color.white)
            chip.setTextColor(resources.getColor(R.color.black))
            binding.chipGroupTags.addView(chip)
        }
    }

    private fun visibilityUI(isLoading: Boolean) =
        with(binding) {
            constraintNameUser.isVisible = !isLoading
            imagePost.isVisible = !isLoading
            constraintInfotags.isVisible = !isLoading
            progressPost.isVisible = isLoading
            commentRecycler.isVisible = !isLoading
            buttonRetry.isGone = true
            textError.isGone = true
        }

    private fun setUIInvisible()  =
        with(binding) {
            constraintNameUser.isVisible = false
            imagePost.isVisible = false
            constraintInfotags.isVisible = false
            progressPost.isVisible = false
            commentRecycler.isVisible = false
        }
}