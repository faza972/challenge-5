package com.faza.challenge_4.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.bumptech.glide.Glide
import com.faza.challenge_4.R
import com.faza.challenge_4.databinding.FragmentDetailBinding
import com.faza.challenge_4.model.Menu
import com.faza.challenge_4.viewModel.DetailViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        setUpCartViewModel()

        viewModel.allPrice.observe(viewLifecycleOwner){
            binding.pesanMkn.text = "Tambah pesanan Ke keranjang -Rp.$it"
        }
        showMenuData()
        addToCart()
        return binding.root

    }

    private fun setUpCartViewModel() {
        val viewModelFactory = ViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
        mViewModel()



    }

    private fun mViewModel() {

        binding.jmlPesanan.setOnClickListener {
            viewModel.increment()
        }

        binding.krgpesanan.setOnClickListener {
            viewModel.decrement()
        }

    }

    private fun setOnClickListener() {
        binding.Lokasi.setOnClickListener{
            navigateToGoogleMaps()
        }
    }

    private fun navigateToGoogleMaps() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-6.3016,$106.65337"))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
    private fun addToCart() {
        binding.pesanMkn.setOnClickListener {
            viewModel.addToCart()
            findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
        }
    }

    private fun showMenuData() {
        menu = arguments?.getParcelable("menu")
        menu?.let {
            Glide.with(binding.ivDetailImage)
                .load(menu!!.image)
                .fitCenter()
                .into(binding.ivDetailImage)
            binding.tvDetailName.text = menu.name
            binding.tvDetailPrice.text = menu.price
            binding.tvDetaildesk.text = menu.desc

            viewModel.initSelectedItem(item!!)
        }
    }

}
