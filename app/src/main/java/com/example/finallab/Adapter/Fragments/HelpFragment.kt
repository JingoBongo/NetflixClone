package com.example.finallab.Adapter.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finallab.R
import com.example.finallab.RetrofitApi.RetrofitCalls


class HelpFragment : Fragment() {

    private lateinit var wishButton: Button
    private lateinit var wishEditText: EditText
    private lateinit var faq: TextView
    private lateinit var retrofitCalls: RetrofitCalls


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val helpView: View = inflater.inflate(R.layout.fragment_help, container, false)

        wishEditText = helpView.findViewById(R.id.editText)
        faq = helpView.findViewById(R.id.suggestions)
        wishButton = helpView.findViewById(R.id.insertWishButton)
        retrofitCalls = RetrofitCalls()
        wishEditText.setSingleLine(false)
        wishEditText.maxLines = 7
        faq.text =
            "This app is designed to let you watch movies online and download them if needed. \n First tab is for browsing, second tab allows to search files." +
                    " This page should help you navigate between functionality of tabs and let you leave a suggestion for " +
                    "further improvement. Feel free to address me at mihai.scebec@ati.utm.md or simply by leaving a comment below."

        wishButton.setOnClickListener(View.OnClickListener {
            var wishText: String = wishEditText.text.toString()
            retrofitCalls.sendWish(helpView.context, wishText)
            wishEditText.text.clear();
        })
        //

        return helpView
    }

}
