package com.mikhailbabenkov.wheather.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.mikhailbabenkov.wheather.R
import com.mikhailbabenkov.wheather.databinding.MainFragmentBinding
import com.mikhailbabenkov.wheather.ui.base.BaseFragment
import com.mikhailbabenkov.wheather.ui.epoxy.ForecastController
import com.mikhailbabenkov.wheather.ui.selectcity.SelectCityViewModel
import com.mikhailbabenkov.wheather.ui.shared.WeatherSharedViewModel

class MainFragment : BaseFragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    private val sharedViewModel: WeatherSharedViewModel by viewModels({ requireParentFragment() }) { viewModelFactory }
    private val controller by lazy { ForecastController(viewModel.uiModel, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initLoadData()
        sharedViewModel.citySelected.observe(this, Observer { event ->
            if (!event.consumed) {
                event.consume()
                viewModel.initLoadData()
            }
        })
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentView.setController(controller)
        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.selectCityAction -> {
                openSelectCity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openSelectCity() {
        findNavController().navigate(R.id.selectCity)
    }
}
