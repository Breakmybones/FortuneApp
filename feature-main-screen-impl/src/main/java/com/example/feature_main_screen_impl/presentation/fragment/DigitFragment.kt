package com.example.feature_main_screen_impl.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.feature_main_screen_api.model.DigitModel
import com.example.feature_main_screen_impl.R
import com.example.feature_main_screen_impl.databinding.FragmentDigitBinding
import com.example.feature_main_screen_impl.domain.GetDigitUseCase
import com.example.feature_main_screen_impl.domain.GetYesUseCase
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_main_screen_impl.presentation.viewModel.DigitViewModel
import com.example.feature_main_screen_impl.presentation.viewModel.YesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DigitFragment : Fragment(R.layout.fragment_digit) {


    private var binding: FragmentDigitBinding? = null

    @Inject
    lateinit var getDigitUseCase: GetDigitUseCase

    private var isViewModelInitialized = false

    private val viewModel: DigitViewModel by viewModels {
        DigitViewModel.provideFactory(
            getDigitUseCase
        )
    }

    override fun onAttach(context: Context) {
        val homeComponent = (requireActivity().application as MainScreenComponentProvider)
            .provideMainScreenComponent()
        homeComponent.injectDigitFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDigitBinding.bind(view)

        showProgressBar()

        getRandomDigit()

        observeViewModel()
    }

    private fun setRandomDigit(digit: DigitModel?) {
        binding?.run {
            tvName.text = digit?.number
            tvDes.text = digit?.description
        }
    }

    private fun getRandomDigit() {
        lifecycleScope.launch {
            viewModel.getRandomDigit()
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
        viewModel.randomDigit.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            setRandomDigit(it)
            hideProgressBar()
        }
    }

}