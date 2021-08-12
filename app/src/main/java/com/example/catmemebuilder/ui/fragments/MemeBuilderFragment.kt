package com.example.catmemebuilder.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.catmemebuilder.R
import com.example.catmemebuilder.databinding.FragmentMemeBuilderBinding
import com.example.catmemebuilder.ui.viewmodels.MainViewModel
import com.example.catmemebuilder.utils.Resource


class MemeBuilderFragment : Fragment() {
    private var _binding: FragmentMemeBuilderBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private var colors: Array<String>? = null
    private var colorMenuAdapter: ArrayAdapter<String>? = null
    private var textSize: Array<String>? = null
    private var textSizeMenuAdapter: ArrayAdapter<String>? = null
    private var filters: Array<String>? = null
    private var filterMenuAdapter: ArrayAdapter<String>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        colors = resources.getStringArray(R.array.colors)
        textSize = resources.getStringArray(R.array.text_size)
        filters = resources.getStringArray(R.array.filters)
        colorMenuAdapter = ArrayAdapter(context, R.layout.list_item,
            colors as Array<out String>
        )
        textSizeMenuAdapter = ArrayAdapter(context, R.layout.list_item,
            textSize as Array<out String>
        )
        filterMenuAdapter = ArrayAdapter(context, R.layout.list_item,
            filters as Array<out String>
        )
    }

    override fun onResume() {
        super.onResume()
        with(binding){
            (colorSelectMenu.editText as? AutoCompleteTextView)?.setAdapter(colorMenuAdapter)
            (textSizeMenu.editText as? AutoCompleteTextView)?.setAdapter(textSizeMenuAdapter)
            (filterTypeMenu.editText as? AutoCompleteTextView)?.setAdapter(filterMenuAdapter)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemeBuilderBinding.inflate(inflater, container, false)
        with(binding){
            gifOrImageMenu.setOnCheckedChangeListener { _, isChecked ->
                viewModel.isGif(isChecked)
            }
            colorSelectTv.setOnItemClickListener { parent, _, position, _ ->
                viewModel.updateColor(parent.getItemAtPosition(position).toString())
            }
            textSizeTv.setOnItemClickListener { parent, _, position, _ ->
                viewModel.updateTextSize(parent.getItemAtPosition(position).toString())
            }
            filterTypeTv.setOnItemClickListener { parent, _, position, _ ->
                viewModel.updateFilter(parent.getItemAtPosition(position).toString())
            }

            textEntryEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    /* no-op */
                }
                override fun onTextChanged(
                    text: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    textSizeMenu.isVisible = !text.isNullOrEmpty()
                    colorSelectMenu.isVisible = !text.isNullOrEmpty()
                }
                override fun afterTextChanged(text: Editable?) {
                    viewModel.updateText(text.toString())
                }
            })
            viewModel.response.observe(viewLifecycleOwner){
                progressBar.isVisible = it is Resource.Loading
                if(viewModel.gotCat){
                    when (it) {
                        is Resource.Loading -> {
                            progressBar.isVisible = true
                        }
                        is Resource.Error -> {
                            Toast.makeText(requireContext(), it.errorMsg, Toast.LENGTH_LONG).show()
                        }
                        is Resource.Success -> {
                            viewModel.updateUrl(it.data.url)
                        }
                    }
                }
            }
            viewModel.catUrl.observe(viewLifecycleOwner){
                if(viewModel.gotCat){
                    view?.findNavController()?.navigate(MemeBuilderFragmentDirections.actionMemeBuilderFragmentToMemeDisplayFragment())
                }
            }
            createMemeBtn.setOnClickListener {
                viewModel.createMeme()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}