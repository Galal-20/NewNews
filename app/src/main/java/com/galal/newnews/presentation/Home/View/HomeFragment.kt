package com.galal.newnews.presentation.Home.View

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.galal.newnews.R
import com.galal.newnews.presentation.Home.Adapter.NewsAdapter
import com.galal.newnews.presentation.Home.ViewModel.HomeViewModel
import com.galal.newnews.presentation.Home.ViewModel.States.NewsState
import com.galal.newnews.utils.ShareFunctions.Companion.showCustomSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val newsViewModel: HomeViewModel by viewModels()
    private var isConnected = false



    private lateinit var connectivityManager: ConnectivityManager
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            if (!isConnected) {
                isConnected = true
                activity?.runOnUiThread {
                    val noInternetAnimation = view?.findViewById<LottieAnimationView>(R.id.noInternetAnimation)
                    val progressBarIndicator = view?.findViewById<LottieAnimationView>(R.id.progressBarIndicator)
                    val dataContainer = view?.findViewById<RecyclerView>(R.id.requisites_recycler_view)
                    noInternetAnimation?.visibility = View.GONE
                    progressBarIndicator?.visibility = View.VISIBLE
                    dataContainer?.visibility = View.GONE
                    newsViewModel.getNews()
                }
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            if (isConnected) {
                isConnected = false
                activity?.runOnUiThread {
                    val noInternetAnimation = view?.findViewById<LottieAnimationView>(R.id.noInternetAnimation)
                    val progressBarIndicator = view?.findViewById<LottieAnimationView>(R.id.progressBarIndicator)
                    val dataContainer = view?.findViewById<RecyclerView>(R.id.requisites_recycler_view)
                    progressBarIndicator?.visibility = View.GONE
                    dataContainer?.visibility = View.GONE
                    noInternetAnimation?.visibility = View.VISIBLE
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityManager = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var adapter: NewsAdapter

        val recyclerView = view.findViewById<RecyclerView>(R.id.requisites_recycler_view)
        val noInternetAnimation = view.findViewById<LottieAnimationView>(R.id.noInternetAnimation)
        var progressBarIndicator = view.findViewById<LottieAnimationView>(R.id.progressBarIndicator)


        progressBarIndicator.visibility = View.VISIBLE
        noInternetAnimation.visibility = View.GONE

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)


        val fadeNavOptions = androidx.navigation.navOptions {
            anim {
                enter = R.anim.fade_in
                exit = R.anim.fade_out
                popEnter = R.anim.fade_in
                popExit = R.anim.fade_out
            }
        }


        adapter = NewsAdapter(
            mutableListOf(),
            onReadMoreClick = { article ->
                val bundle = Bundle().apply {
                    putString("title", article.title)
                    putString("imageUrl", article.urlToImage)
                    putString("date", article.publishedAt)
                    putString("content", article.description ?: "No content")
                    putString("author", article.author)
                    putString("url", article.url)
                }
                findNavController().navigate(R.id.detailsFragment, bundle, fadeNavOptions)
            },
            onSaveClick = { article, isSaved ->
                if(isSaved){
                    newsViewModel.deleteArticle(article)
                    showCustomSnackbar(
                        view = view,
                        message = getString(R.string.article_deleted_successfully),
                        backgroundColor = ContextCompat.getColor(requireContext(), R.color.green),
                        textColor = ContextCompat.getColor(requireContext(), R.color.white)
                    )
                    adapter.notifyDataSetChanged()
                }else{
                    newsViewModel.saveArticle(article)
                    showCustomSnackbar(
                        view = view,
                        message = getString(R.string.article_saved_successfully),
                        backgroundColor = ContextCompat.getColor(requireContext(), R.color.green),
                        textColor = ContextCompat.getColor(requireContext(), R.color.white)
                    )
                    adapter.notifyDataSetChanged()
                }

            }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            newsViewModel.newState.collect { state ->
                when (state) {
                    is NewsState.Loading -> progressBarIndicator.visibility = View.VISIBLE
                    is NewsState.Success -> {
                        progressBarIndicator.visibility = View.GONE
                        adapter.updateNews(state.newsResponse.articles)
                        recyclerView.visibility = View.VISIBLE
                        noInternetAnimation.visibility = View.GONE
                    }
                    is NewsState.Error -> {
                        progressBarIndicator.visibility = View.GONE
                        if (newsViewModel.lastSuccessData == null) {
                            noInternetAnimation.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        } else {
                            showCustomSnackbar(
                                view = view,
                                message = "No new data",
                                backgroundColor = ContextCompat.getColor(requireContext(), R.color.red),
                                textColor = ContextCompat.getColor(requireContext(), R.color.white)
                            )
                        }
                    }
                    else -> Unit
                }
            }
        }
        lifecycleScope.launch {
            val savedArticles = newsViewModel.getAllSavedArticles()
            adapter.setSavedArticles(savedArticles)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }



}

