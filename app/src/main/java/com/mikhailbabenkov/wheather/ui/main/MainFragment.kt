package com.mikhailbabenkov.wheather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mikhailbabenkov.wheather.databinding.MainFragmentBinding
import com.mikhailbabenkov.wheather.ui.base.BaseFragment
import com.mikhailbabenkov.wheather.ui.epoxy.ForecastController

class MainFragment : BaseFragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels { viewModelFactory  }
    private val controller by lazy { ForecastController(viewModel.uiModel, this) }

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.initLoadData()
    }
}
