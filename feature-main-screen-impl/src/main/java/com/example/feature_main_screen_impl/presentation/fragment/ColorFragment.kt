package com.example.feature_main_screen_impl.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.feature_main_screen_impl.R
import com.example.feature_main_screen_impl.databinding.FragmentColorBinding
import com.example.feature_main_screen_impl.domain.GetYesUseCase
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_main_screen_impl.presentation.viewModel.ColorViewModel
import com.example.feature_main_screen_impl.presentation.viewModel.YesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class ColorFragment : Fragment(R.layout.fragment_color) {

    private var binding: FragmentColorBinding? = null

    @Inject
    lateinit var getColorUseCase: GetYesUseCase

    private val viewModel: ColorViewModel by viewModels {
        YesViewModel.provideFactory(
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

        binding = FragmentColorBinding.bind(view)

        getRandomColor()

        observeViewModel()
    }

    private fun setRandomColor(color: Map<String, String>?) {
        binding?.run {
            tvName.text = color?.values?.firstOrNull()
            tvDes.text = color?.values?.elementAtOrNull(1)
        }
    }

    private fun getRandomColor() {
        lifecycleScope.launch {
            viewModel.getRandomColor()
        }
    }

    private fun observeViewModel() {
        viewModel.randomColor.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            setRandomColor(it)
        }
    }
}