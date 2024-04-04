package com.example.algoritmaapp.ui.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.algoritmaapp.common.BaseFragment
import com.example.algoritmaapp.databinding.FragmentMainBinding
import com.example.algoritmaapp.ui.adapter.InvestAdapter
import com.example.algoritmaapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(
    onInflate = FragmentMainBinding ::inflate
) {
    private val investAdapter = InvestAdapter()
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData(requireContext())
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.adapter = investAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.resultList.collect { result ->
                investAdapter.submitList(result)
                Log.d("result", "$result")
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                Log.d("isLoading", "$isLoading")
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isConnected.collect { isConnected ->
                val backgroundColor = if (isConnected) {
                    Color.GREEN
                } else {
                    Color.RED
                }
                binding.progressIndicator.trackColor = backgroundColor
                binding.progressIndicator.isIndeterminate = !isConnected
                Log.d("isConnected", "$isConnected")
            }
        }
    }



}