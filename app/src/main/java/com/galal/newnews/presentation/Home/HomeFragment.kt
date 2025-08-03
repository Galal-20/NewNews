package com.galal.newnews.presentation.Home

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.galal.newnews.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val newsViewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.requisites_recycler_view)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarIndicator)

        val fadeNavOptions = androidx.navigation.navOptions {
            anim {
                enter = R.anim.fade_in
                exit = R.anim.fade_out
                popEnter = R.anim.fade_in
                popExit = R.anim.fade_out
            }
        }

        val adapter = NewsAdapter(emptyList()) { article ->
            val bundle = Bundle().apply {
                putString("title", article.title)
                putString("imageUrl", article.urlToImage)
                putString("date", article.publishedAt)
                putString("content",article.description ?: "No content")
            }

            findNavController().navigate(R.id.detailsFragment, bundle, fadeNavOptions)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launchWhenStarted {
            newsViewModel.newState.collect { state ->
                when (state) {
                    is NewsSealedClass.Loading -> progressBar.visibility = View.VISIBLE
                    is NewsSealedClass.Success -> {
                        progressBar.visibility = View.GONE
                        recyclerView.adapter = NewsAdapter(state.newsResponse.articles) { article ->
                            val bundle = Bundle().apply {
                                putString("title", article.title)
                                putString("imageUrl", article.urlToImage)
                                putString("date", article.publishedAt)
                                putString("content",article.description ?: "No content")
                            }
                            findNavController().navigate(R.id.detailsFragment, bundle, fadeNavOptions)
                        }
                    }
                    is NewsSealedClass.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Error: ${state.message}", Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

}