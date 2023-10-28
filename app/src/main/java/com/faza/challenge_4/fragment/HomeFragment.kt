package com.faza.challenge_4.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.faza.challenge_4.adapter.MenuAdapter
import com.faza.challenge_4.SharedPreference
import com.faza.challenge_4.R
import com.faza.challenge_4.adapter.CategoryAdapter
import com.faza.challenge_4.api.ApiClient
import com.faza.challenge_4.databinding.FragmentHomeBinding
import com.faza.challenge_4.model.CategoryMenu
import com.faza.challenge_4.model.Menu
import com.faza.challenge_4.viewModel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var preference: SharedPreference
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menu: Menu
    private var isGrid: Boolean = true
    private val data = ArrayList<Menu>()
    private var layoutMode = true

    private val drawable = arrayListOf(
        R.drawable.button_list
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        preference = SharedPreference(requireContext())
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        homeViewModel.menuView.value = preference.isGrid
        menuAdapter = MenuAdapter(data, homeViewModel.menuView.value ?: true)
        binding.rvListMenu.adapter = menuAdapter

        binding.rvListMenu.setHasFixedSize(true)
        if (data.isEmpty()) {
            data.addAll(getMenu())
        }
        showRecyclerView()

        val toggleButton = binding.ivGantiMode
        toggleButton.setOnClickListener{
            isGrid =! isGrid
            toggleImageViewImage(toggleButton)
            homeViewModel.menuView
            fetchListMenu()
        }
        fetchCategoryMenu()
        fetchListMenu()

        return binding.root
    }

    private fun fetchListMenu() {
        ApiClient.instance.getCategory()
            .enqueue(object : Callback<CategoryMenu>{
                override fun onResponse(
                    call: Call<CategoryMenu>,
                    response: Response<CategoryMenu>
                ) {
                    val body : CategoryMenu? = response.body()
                    if ( response.code() == 200) {
                        showCategory(body!!)
                    }

                }

                override fun onFailure(call: Call<CategoryMenu>, t: Throwable) {
                    Log.e("CategoryMenu Error", t.message.toString())
                }
            })
    }


    private fun showRecyclerView() {
        binding.rvListMenu.layoutManager = GridLayoutManager(requireActivity(), 2)
        val dataAdapter = MenuAdapter(data)
        binding.rvListMenu.adapter = dataAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toggleButton = binding.ivGantiMode
        toggleButton.setOnClickListener {
            isGrid = !isGrid
            toggleImageViewImage(toggleButton)
        }
    }

    private fun toggleImageViewImage(imageView: ImageView) {
        imageView.setImageResource(drawable[if (isGrid) 0 else 1])
    }

    private fun updateHome(menuItem: ArrayList<Menu>) {
        menuAdapter.reloadData(menuItem)
        menuAdapter.isGrid = homeViewModel.menuView.value ?: true
        binding.rvListMenu.adapter?.notifyDataSetChanged()
    }

    private fun getMenu(): List<Menu> {
        return emptyList()
    }
    private fun linearMenu() {
        binding.rvListMenu.layoutManager = LinearLayoutManager(requireActivity())
        val adapterFood = MenuAdapter(data, isGrid = false)
        binding.rvListMenu.adapter = adapterFood
        navigateToDetail(data)
    }



    private fun gridMenu() {
        binding.rvListMenu.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapterGrid = MenuAdapter(data, isGrid = true)
        binding.rvListMenu.adapter = adapterGrid
    }

    private fun navigateToDetail(data: ArrayList<Menu>) {
        val bundle = bundleOf("key" to data)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }
    private fun fetchCategoryMenu() {
        ApiClient.instance.getCategory().enqueue(object : Callback<CategoryMenu>{
            override fun onResponse(call : Call<CategoryMenu>, response: Response<CategoryMenu>) {
                val body: CategoryMenu? = response.body()
                if (response.code() == 200) {
                    showCategory(body!!)
                }
            }
            override fun onFailure(call: Call<CategoryMenu>, t: Throwable){

            }
        })
    }
    private fun showCategory(body: CategoryMenu) {
        val adapter = CategoryAdapter()
        adapter.sendCategoryData(body.data)
        binding.root
    }
}
