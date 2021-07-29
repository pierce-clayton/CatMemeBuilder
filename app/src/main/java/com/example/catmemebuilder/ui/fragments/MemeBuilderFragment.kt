package com.example.catmemebuilder.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import com.example.catmemebuilder.R
import com.example.catmemebuilder.databinding.FragmentMemeBuilderBinding
import com.example.catmemebuilder.ui.viewmodels.MainViewModel


class MemeBuilderFragment : Fragment() {
    private var _binding: FragmentMemeBuilderBinding? = null
    private val binding get() = _binding!!

    private var viewModel = MainViewModel()

    private var colors: Array<String>? = null
    private var colorMenuAdapter: ArrayAdapter<String>? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        colors = resources.getStringArray(R.array.colors)
        colorMenuAdapter = ArrayAdapter(context, R.layout.color_list_item,
            colors as Array<out String>
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemeBuilderBinding.inflate(inflater, container, false)
        (binding.colorSelectMenu.editText as? AutoCompleteTextView)?.setAdapter(colorMenuAdapter)

        binding.textEntryEt.addTextChangedListener (object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /* no-op */
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                binding.textSizeMenu.isVisible = !text.isNullOrEmpty()
                binding.colorSelectMenu.isVisible = !text.isNullOrEmpty()
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.updateText(text.toString())
            }
        })

        return binding.root
    }
}