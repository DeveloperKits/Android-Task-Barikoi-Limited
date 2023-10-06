package com.akashdas.task2barikoi.Dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.akashdas.task2barikoi.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog : BottomSheetDialogFragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)

        // Find the TextView widgets and set their text
        val textView1 = view.findViewById<TextView>(R.id.textView2)
        val textView2 = view.findViewById<TextView>(R.id.textView2)

        // Get data passed from the activity or fragment
        val text1 = arguments?.getString("text1")
        val text2 = arguments?.getString("text2")

        textView1.text = text1
        textView2.text = text2

        return view
    }

}