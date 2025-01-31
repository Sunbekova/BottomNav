package com.example.exampleapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exampleapp.databinding.FragmentAddTextBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddTextFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTextFragment : Fragment() {
    private lateinit var binding: FragmentAddTextBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentAddTextBinding.inflate(inflater, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("EditablePagePrefs", Context.MODE_PRIVATE)

        // Load saved text when the fragment is created
        val savedText = sharedPreferences.getString("savedText", "")
        binding.editText.setText(savedText)

        // Set a TextWatcher to save the text as it's edited
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed here
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // Save the text to SharedPreferences as it's edited
                val editor = sharedPreferences.edit()
                editor.putString("savedText", charSequence.toString())
                editor.apply() // Apply the changes asynchronously
            }

            override fun afterTextChanged(editable: Editable?) {
                // No action needed here
            }
        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTextFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}