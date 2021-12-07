package com.example.the_and_tgu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment


private const val NEXT_IMAGE = "image"

class HomeFragment : Fragment() {

    private lateinit var dodoImageView: ImageView
    private lateinit var nextButton: Button
    private lateinit var feedbackButton: Button

    private val dodoImageList: List<Int> = listOf(
        R.drawable.dodo1,
        R.drawable.dodo2,
        R.drawable.dodo3,
        R.drawable.dodo4,
        R.drawable.dodo5,
        R.drawable.dodo6,
        R.drawable.dodo7,
        R.drawable.dodo8,
    )

    private var lastNextImageRes = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_home, container, false)

        feedbackButton = view.findViewById(R.id.feedback_button)
        feedbackButton.setOnClickListener {
            val feedbackActivityIntent:Intent = Intent(activity, FeedbackActivity::class.java)
            startActivity(feedbackActivityIntent)
        }

        nextButton = view.findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            lastNextImageRes = dodoImageList.random()
            dodoImageView.setImageResource(lastNextImageRes)
        }

        dodoImageView = view.findViewById(R.id.dodo_image_view)


        if (savedInstanceState != null) {
            dodoImageView.setImageResource(savedInstanceState.getInt(NEXT_IMAGE))
        }

        return view


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NEXT_IMAGE, lastNextImageRes)
    }

}