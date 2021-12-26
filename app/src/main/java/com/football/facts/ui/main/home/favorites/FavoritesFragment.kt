package com.football.facts.ui.main.home.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.football.facts.R
import com.football.facts.databinding.FragmentFavoritesBinding
import com.football.facts.ui.base.BaseFragment
import com.football.facts.ui.utils.extensions.collectWithLifecycle
import com.football.facts.ui.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {
    override val binding by viewBinding(FragmentFavoritesBinding::bind)
    override val viewModel by viewModels<FavoritesViewModel>()

    private val adapter by lazy {
        CountriesAdapter(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val snapHelperCarousel = PagerSnapHelper()
        snapHelperCarousel.attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext() , 2 , GridLayoutManager.HORIZONTAL , false)
        binding.recyclerView.adapter = adapter


//        collectWithLifecycle(viewModel.countriesDisplay) {
//            adapter.countries = ArrayList(it.countries)
//        }

        collectWithLifecycle(viewModel.events){
            adapter.notifyCountryChanged(it)
        }
    }


}