package com.faza.challenge_4.Home

import android.os.Bundle
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
import com.faza.challenge_4.R
import com.faza.challenge_4.data.MenuDataImpl
import com.faza.challenge_4.databinding.FragmentHomeBinding
import com.faza.challenge_4.menu.Menu
import com.faza.challenge_4.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var preference: SharedPreference
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menuDataImpl: MenuDataImpl
    private var isGrid: Boolean = true
    private val data = ArrayList<Menu>()
    private var layoutMode = true

    private val drawable = arrayListOf(
        R.drawable.bullet_list
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        homeViewModel.menuView.observe(viewLifecycleOwner) {
            toggleCurrent()
        }

        homeViewModel.menuItem.observe(viewLifecycleOwner) { menuItem ->
            updateHome(menuItem)
        }

        clickItem()

        return binding.root
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
            toggleCurrent()
            toggleImageViewImage(toggleButton)
        }
        toggleCurrent()
    }

    private fun toggleImageViewImage(imageView: ImageView) {
        imageView.setImageResource(drawable[if (isGrid) 0 else 1])
    }

    private fun clickItem() {
        menuAdapter = MenuAdapter(data, homeViewModel.menuView.value ?: true) { item ->
            val bundle = bundleOf("item" to item)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
        binding.rvListMenu.adapter = menuAdapter
    }

    private fun updateHome(menuItem: ArrayList<Menu>) {
        menuAdapter.reloadData(menuItem)
        menuAdapter.isGrid = homeViewModel.menuView.value ?: true
        binding.rvListMenu.adapter?.notifyDataSetChanged()
    }

    private fun toggleCurrent() {
        val toggleImage = binding.ivGantiMode
        val currentLayout: Boolean = homeViewModel.menuView.value ?: preference.isGrid
        toggleRecyclerView(currentLayout)
    }

    private fun toggleRecyclerView(viewList: Boolean) {
        data.clear()

        layoutMode = if (viewList) {
            gridMenu()
            true
        } else {
            linearMenu()
            false
        }
        val adapter = MenuAdapter(data, isGrid = layoutMode) {
            clickItem()
        }
        data.addAll(getMenu())
        binding.rvListMenu.adapter = adapter
    }

    private fun getMenu(): List<Menu> {
        val menuData = MenuDataImpl()
        return menuData.getMenuData()
    }

    private fun linearMenu() {
        binding.rvListMenu.layoutManager = LinearLayoutManager(requireActivity())
        val adapterFood = MenuAdapter(data, isGrid = false)
        binding.rvListMenu.adapter = adapterFood
    }

    private fun gridMenu() {
        binding.rvListMenu.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapterGrid = MenuAdapter(data, isGrid = true)
        binding.rvListMenu.adapter = adapterGrid
    }
}
