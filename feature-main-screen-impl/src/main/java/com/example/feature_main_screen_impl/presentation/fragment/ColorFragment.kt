package com.example.feature_main_screen_impl.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.feature_main_screen_api.model.ColorModel
import com.example.feature_main_screen_impl.R
import com.example.feature_main_screen_impl.databinding.FragmentColorBinding
import com.example.feature_main_screen_impl.domain.GetColorUseCase
import com.example.feature_main_screen_impl.domain.GetYesUseCase
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_main_screen_impl.presentation.viewModel.ColorViewModel
import com.example.feature_main_screen_impl.presentation.viewModel.YesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class ColorFragment : Fragment(R.layout.fragment_color) {

    private var binding: FragmentColorBinding? = null

    @Inject
    lateinit var getColorUseCase: GetColorUseCase

    private var isViewModelInitialized = false

    private val viewModel: ColorViewModel by viewModels {
        ColorViewModel.provideFactory(
            getColorUseCase
        )
    }

    override fun onAttach(context: Context) {
        val homeComponent = (requireActivity().application as MainScreenComponentProvider)
            .provideMainScreenComponent()
        homeComponent.injectColorFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressBar()

        binding = FragmentColorBinding.bind(view)

        getRandomColor()

        observeViewModel()
    }

    private fun setRandomColor(color: ColorModel?) {
        binding?.run {
            tvName.text = color?.color
            tvDes.text = color?.description
        }
    }

    private fun getRandomColor() {
        lifecycleScope.launch {
            viewModel.getRandomColor()
            isViewModelInitialized = true
        }
    }

    private fun showProgressBar() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        if (isViewModelInitialized) { // Проверяем, что ViewModel уже была инициализирована
            binding?.progressBar?.visibility = View.GONE
        }
    }

    private fun observeViewModel() {
        viewModel.randomColor.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            setRandomColor(it)
            hideProgressBar()
        }
    }
}