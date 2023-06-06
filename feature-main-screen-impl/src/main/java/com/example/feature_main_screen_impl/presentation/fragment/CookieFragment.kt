package com.example.feature_main_screen_impl.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.feature_main_screen_api.model.CookieModel
import com.example.feature_main_screen_impl.R
import com.example.feature_main_screen_impl.databinding.FragmentColorBinding
import com.example.feature_main_screen_impl.databinding.FragmentCookieBinding
import com.example.feature_main_screen_impl.domain.GetCookieUseCase
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_main_screen_impl.presentation.viewModel.ColorViewModel
import com.example.feature_main_screen_impl.presentation.viewModel.CookieViewModel
import com.example.feature_main_screen_impl.presentation.viewModel.YesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class CookieFragment : Fragment(R.layout.fragment_cookie) {

    private var binding: FragmentCookieBinding? = null

    @Inject
    lateinit var getCookieUseCase: GetCookieUseCase

    private var isViewModelInitialized = false

    private val viewModel: CookieViewModel by viewModels {
        CookieViewModel.provideFactory(
            getCookieUseCase
        )
    }

    override fun onAttach(context: Context) {
        val homeComponent = (requireActivity().application as MainScreenComponentProvider)
            .provideMainScreenComponent()
        homeComponent.injectCookieFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCookieBinding.bind(view)

        showProgressBar()

        getRandomCookie()

        observeViewModel()
    }

    private fun setRandomCookie(cookie: CookieModel?) {
        binding?.run {
            tvDes.text = cookie?.description
            isViewModelInitialized = true
        }
    }

    private fun getRandomCookie() {
        lifecycleScope.launch {
            viewModel.getRandomCookie()
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
        viewModel.randomCookie.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            setRandomCookie(it)
            hideProgressBar()
        }
    }
}
