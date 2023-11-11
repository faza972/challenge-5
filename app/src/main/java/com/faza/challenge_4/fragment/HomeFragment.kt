package com.faza.challenge_4.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.faza.challenge_4.R
import com.faza.challenge_4.SharedPreference
import com.faza.challenge_4.adapter.CategoryAdapter
import com.faza.challenge_4.adapter.MenuAdapter
import com.faza.challenge_4.api.ApiClient
import com.faza.challenge_4.databinding.FragmentHomeBinding
import com.faza.challenge_4.model.CategoryMenu
import com.faza.challenge_4.model.ListData
import com.faza.challenge_4.model.ListMenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var isGrid: Boolean = true


    private val drawable = arrayListOf(
        R.drawable.button_list,
        R.drawable.ic_grid
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        isGrid = SharedPreference.getPreference("VIEW LIST", true)

        val toggleButton = binding.ivGantiMode
        toggleButton.setOnClickListener {
            isGrid = !isGrid
            toggleImageViewImage(toggleButton)
            SharedPreference.setPreference("view", isGrid)
            fetchListMenu()
        }
        fetchCategoryMenu()
        fetchListMenu()

        return binding.root
    }

    private fun showCategoryMenu(menu: CategoryMenu?) {
        val adapter = CategoryAdapter()
        adapter.sendCategoryData(menu?.data)
        binding.cvKategori.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun fetchCategoryMenu() {
        ApiClient.instance.getCategory().enqueue(object : Callback<CategoryMenu>{
            override fun onResponse(call: Call<CategoryMenu>, response: Response<CategoryMenu>) {
                val body: CategoryMenu? = response.body()
                if (response.code() == 200) {
                    showCategoryMenu(body)
                }
            }

            override fun onFailure(call: Call<CategoryMenu>, t: Throwable) {
                Log.e(" CategoryMenu Error", t.message.toString())
            }

        })
    }

    private fun toggleImageViewImage(imageView: ImageView) {
        imageView.setImageResource(drawable[if (isGrid)0 else 1])
    }

    private fun showListMenu(menu: ListMenu) {
        if (isGrid){
            val adapter = MenuAdapter(isGrid, object : MenuAdapter.OnClickListener{
                override fun onClick(listdata: ListData) {
                    navigateToDetail(listdata)
                }
            })
            adapter.putListMenu(menu.data)
            binding.rvListMenu.layoutManager = GridLayoutManager(requireActivity(), 2)
            binding.rvListMenu.adapter = adapter
        } else {
            val adapter = MenuAdapter(isGrid, object : MenuAdapter.OnClickListener{
                override fun onClick(listdata: ListData) {
                    navigateToDetail(listdata)
                }
            })
            adapter.putListMenu(menu.data)
            binding.rvListMenu.layoutManager = LinearLayoutManager(requireActivity())
            binding.rvListMenu.adapter = adapter
        }
    }

    private fun fetchListMenu() {
        ApiClient.instance.getListMenu()
            .enqueue(object : Callback<ListMenu> {
                override fun onResponse(call: Call<ListMenu>, response: Response<ListMenu>) {
                    val body: ListMenu? = response.body()
                    if (response.code() == 200) {
                        showListMenu(body!!)
                    }
                }

                override fun onFailure(call: Call<ListMenu>, t: Throwable) {
                    Log.e("ListMenu Error", t.message.toString())
                }
            })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toggleButton = binding.ivGantiMode
        toggleButton.setOnClickListener {
            isGrid = !isGrid
            toggleImageViewImage(toggleButton)
        }
    }


    private fun navigateToDetail(data: ListData) {
        val bundle = bundleOf("key" to data)
        Log.e("Bundle", bundle.toString())
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }
}
