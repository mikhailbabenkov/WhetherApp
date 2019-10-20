package com.mikhailbabenkov.wheather.ui.selectcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mikhailbabenkov.wheather.R
import com.mikhailbabenkov.wheather.databinding.SelectCityFragmentBinding
import com.mikhailbabenkov.wheather.ui.base.BaseFragment
import com.mikhailbabenkov.wheather.ui.epoxy.CitiesController
import com.mikhailbabenkov.wheather.ui.shared.WeatherSharedViewModel

class SelectCityFragment : BaseFragment(), SearchView.OnQueryTextListener,
    CitiesController.Callback {

    private lateinit var binding: SelectCityFragmentBinding
    private val viewModel: SelectCityViewModel by viewModels { viewModelFactory }
    private val sharedViewModel: WeatherSharedViewModel by viewModels({ requireParentFragment() })  { viewModelFactory }
    private val controller by lazy { CitiesController(viewModel.uiModel, this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.selectCityResult.observe(this, Observer {result->
            if(!result.consumed) {
                result.consume()
                sharedViewModel.notifyCitySelected()
                findNavController().navigateUp()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SelectCityFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentView.setController(controller)
        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.searchAction)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint_lbl)
        searchView.setOnQueryTextListener(this)
        searchItem.expandActionView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.searchForCity(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return true
    }

    override fun onCitySelected(cityId: Int) {
        viewModel.setCitySelected(cityId)
    }
}