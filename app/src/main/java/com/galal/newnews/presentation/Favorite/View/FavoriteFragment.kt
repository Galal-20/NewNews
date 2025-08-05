package com.galal.newnews.presentation.Favorite.View

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.galal.newnews.R
import com.galal.newnews.presentation.Favorite.ViewModel.FavoriteViewModel
import com.galal.newnews.presentation.Home.Adapter.NewsAdapter
import com.galal.newnews.utils.ShareFunctions.Companion.showCustomSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.requisites_recycler_view)


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
                    viewModel.deleteArticle(article)
                    showCustomSnackbar(
                        view = view,
                        message = getString(R.string.article_deleted_successfully),
                        backgroundColor = ContextCompat.getColor(requireContext(), R.color.green),
                        textColor = ContextCompat.getColor(requireContext(), R.color.white)
                    )
                    viewModel.loadSavedArticles()
                }

            }
        )
        recyclerView.adapter = adapter

        viewModel.loadSavedArticles()

        viewModel.savedArticles.observe(viewLifecycleOwner) { savedArticles ->
            adapter.updateNews(savedArticles)
            adapter.setSavedArticles(savedArticles)

            val lottieEmpty = view.findViewById<LottieAnimationView>(R.id.noInternetAnimation)
            val recyclerView = view.findViewById<RecyclerView>(R.id.requisites_recycler_view)

            if (savedArticles.isNullOrEmpty()) {
                lottieEmpty.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                lottieEmpty.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        view.findViewById<ImageView>(R.id.back).setOnClickListener {
            findNavController().popBackStack()
        }
    }
}