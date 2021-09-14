package com.example.gallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.databinding.FragmentGalleryBinding
import com.example.gallery.interfaces.OnItemClickListener
import com.example.gallery.ui.adapter.GalleryAdapter

class GalleryFragment : Fragment() {
    lateinit var binding: FragmentGalleryBinding
    private val galleryAdapter: GalleryAdapter = GalleryAdapter()
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPictureList()
        setupRecycler()
        setupRequests()
        addPictureUrlClickListener()
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

        galleryAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(url: String) {
                val action = GalleryFragmentDirections.actionGalleryFragmentToPictureFragment(url)
                findNavController().navigate(action)
            }
        })
    }

    private fun setupRequests() {
        viewModel.liveData.observe(viewLifecycleOwner, {
            galleryAdapter.addList(it)
        })
    }

    private fun addPictureUrlClickListener() {
        binding.btnAddPictures.setOnClickListener() {
            val getUrl = binding.etAddPictures.text.toString()
            if (getUrl.trim().isEmpty()) {
                binding.etAddPictures.error = getString(R.string.url_error)
            } else {
                viewModel.setImage(getUrl)
                binding.etAddPictures.setText("")
            }
        }
    }
}
