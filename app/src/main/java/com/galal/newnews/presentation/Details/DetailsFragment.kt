package com.galal.newnews.presentation.Details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.galal.newnews.R
import com.galal.newnews.utils.ShareFunctions.Companion.getTimeAgo

class DetailsFragment : Fragment() {


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

        view.findViewById<TextView>(R.id.news_title).text = title
        view.findViewById<TextView>(R.id.news_date).text = getTimeAgo(date)
        view.findViewById<TextView>(R.id.news_content).text = content
        view.findViewById<TextView>(R.id.author_date).text = author

        Glide.with(this)
            .load(imageUrl)
            .into(view.findViewById(R.id.news_image))

        view.findViewById<ImageView>(R.id.back_button).setOnClickListener {
            requireActivity().window.decorView.windowInsetsController?.show(
                android.view.WindowInsets.Type.statusBars()
            )
            findNavController().popBackStack()
        }
    }


}

