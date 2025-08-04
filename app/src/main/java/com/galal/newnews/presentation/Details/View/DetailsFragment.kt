package com.galal.newnews.presentation.Details.View

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.galal.newnews.R
import com.galal.newnews.domain.entities.Article
import com.galal.newnews.presentation.Home.ViewModel.HomeViewModel
import com.galal.newnews.utils.ShareFunctions.Companion.getTimeAgo
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val newsViewModel: HomeViewModel by viewModels({ requireActivity() })
    private var isSaved = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.decorView.windowInsetsController?.hide(
            android.view.WindowInsets.Type.statusBars()
        )

        val title = arguments?.getString("title")
        val imageUrl = arguments?.getString("imageUrl")
        val date = arguments?.getString("date")
        val content = arguments?.getString("content")
        val author = arguments?.getString("author")
        val url = arguments?.getString("url")

        view.findViewById<TextView>(R.id.news_title).text = title
        view.findViewById<TextView>(R.id.news_date).text = getTimeAgo(date)
        view.findViewById<TextView>(R.id.news_content).text = content
        view.findViewById<TextView>(R.id.author_date).text = author

        Glide.with(this)
            .load(imageUrl)
            .into(view.findViewById(R.id.news_image))

        val shareButton = view.findViewById<ImageView>(R.id.share_button)
        shareButton.setOnClickListener {
            shareArticle(title, url)
        }

        val saveButton = view.findViewById<ImageView>(R.id.save_button)

        lifecycleScope.launch {
            val savedArticles = newsViewModel.getAllSavedArticles()
            isSaved = savedArticles.any { it.url == url }

            val icon = if (isSaved) R.drawable.saved_filled else R.drawable.save_icon
            saveButton.setImageResource(icon)

            saveButton.setOnClickListener {
                val article = Article(author, title, content, url, imageUrl, date)

                if (isSaved) {
                    newsViewModel.deleteArticle(article)
                    saveButton.setImageResource(R.drawable.save_icon)
                    Snackbar.make(view, "Article deleted", Snackbar.LENGTH_SHORT).show()
                    isSaved = false
                } else {
                    newsViewModel.saveArticle(article)
                    saveButton.setImageResource(R.drawable.saved_filled)
                    Snackbar.make(view, "Article saved", Snackbar.LENGTH_SHORT).show()
                    isSaved = true
                }
            }

            view.findViewById<ImageView>(R.id.back_button).setOnClickListener {
                requireActivity().window.decorView.windowInsetsController?.show(
                    android.view.WindowInsets.Type.statusBars()
                )
                findNavController().popBackStack()
            }
        }
    }
    private fun shareArticle(title: String?, url: String?) {
        val shareText = "$title\n$url"

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, shareText)
        }

        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}

