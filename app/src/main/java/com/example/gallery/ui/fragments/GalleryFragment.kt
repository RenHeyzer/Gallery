package com.example.gallery.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gallery.R
import com.example.gallery.databinding.FragmentGalleryBinding
import com.example.gallery.interfaces.OnItemClickListener
import com.example.gallery.ui.adapter.GalleryAdapter
import com.example.gallery.utils.getTextEt
import com.example.gallery.utils.isEmptyEt

class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private val binding by viewBinding(FragmentGalleryBinding::bind)
    private val galleryAdapter: GalleryAdapter = GalleryAdapter()
    private val viewModel: GalleryViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPictureList()
        setupRecycler()
        setupListener()
        setupRequests()
    }

    private fun setupListener() {
        setupOnItemClick()
        addPictureUrlClickListener()
    }

    private fun setupOnItemClick() {
        galleryAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(url: String) {
                val action = GalleryFragmentDirections.actionGalleryFragmentToPictureFragment(url)
                findNavController().navigate(action)
            }
        })
    }

    private fun setPictureList() {
        viewModel.getPictureList()
    }

    private fun setupRecycler() {
        binding.picturesRecycler.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            adapter = galleryAdapter
        }
    }

    private fun setupRequests() {
        viewModel.liveData.observeForever {
            galleryAdapter.addList(it)
        }
    }

    private fun addPictureUrlClickListener() {
        binding.btnAddPictures.setOnClickListener() {
            val getUrl = binding.etAddPictures.getTextEt()
            if (binding.etAddPictures.isEmptyEt()) {
                viewModel.setImage(getUrl)
                binding.etAddPictures.setText("")
            }
        }
    }
}
