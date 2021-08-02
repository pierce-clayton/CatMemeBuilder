package com.example.catmemebuilder.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.catmemebuilder.databinding.FragmentMemeDisplayBinding
import com.example.catmemebuilder.ui.viewmodels.MainViewModel
import com.example.catmemebuilder.utils.Constants.FINAL_URL

class MemeDisplayFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    private var shareIntent: Intent? = null

    private var _binding: FragmentMemeDisplayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemeDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.catUrl.observe(viewLifecycleOwner){
            if(viewModel.isGif.value == true){
                Glide.with(view).asGif().load(FINAL_URL + it).into(binding.memeIv)
                shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, FINAL_URL + it)
                    type = "image/gif"
                }
            }else{
                Glide.with(view).load(FINAL_URL + it).into(binding.memeIv)
                shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, FINAL_URL + it)
                    type = "image/jpeg"
                }
            }

            viewModel.gotCat(false)
        }
        binding.shareBtn.setOnClickListener {
            startActivity(Intent.createChooser(shareIntent, "Share this cat with friends"))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}