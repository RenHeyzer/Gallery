package com.example.gallery.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gallery.R
import com.example.gallery.databinding.FragmentPictureBinding
import com.example.gallery.utils.loadFitImage

class PictureFragment : Fragment(R.layout.fragment_picture) {
    private val binding by viewBinding(FragmentPictureBinding::bind)
    private val args: PictureFragmentArgs by navArgs()
    private val viewModel: GalleryViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPictureList()
        getImageUrl()
        randomClickListener()
    }

    private fun randomClickListener() {
        binding.randomButton.setOnClickListener() {
            binding.imgPicture.loadFitImage(viewModel.randomAddImage())
        }
    }

    private fun getImageUrl() {
        binding.imgPicture.loadFitImage(args.image)
    }
}