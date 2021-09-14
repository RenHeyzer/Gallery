package com.example.gallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.gallery.databinding.FragmentPictureBinding
import com.squareup.picasso.Picasso
import java.util.*

class PictureFragment : Fragment() {
    lateinit var binding: FragmentPictureBinding
    private val args: PictureFragmentArgs by navArgs()
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPictureList()
        getImageUrl()
        randomClickListener()
    }

    private fun randomClickListener() {
        binding.randomButton.setOnClickListener() {
            val randomImage = Random().nextInt(viewModel.listOfPicture.size)
            Picasso.get()
                .load(viewModel.listOfPicture[randomImage])
                .fit()
                .into(binding.imgPicture)
        }
    }

    private fun getImageUrl() {
        Picasso.get()
            .load(args.image)
            .fit()
            .into(binding.imgPicture)
    }
}