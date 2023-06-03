package com.example.feature_main_screen_impl.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.feature_main_screen_api.model.CardModel
import com.example.feature_main_screen_impl.R
import com.example.feature_main_screen_impl.databinding.FragmentYesBinding
import com.example.feature_main_screen_impl.domain.GetYesUseCase
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_main_screen_impl.presentation.viewModel.YesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class YesFragment : Fragment(R.layout.fragment_yes) {

    private var binding: FragmentYesBinding? = null

    @Inject
    lateinit var getYesUseCase: GetYesUseCase

    private val viewModel: YesViewModel by viewModels {
        YesViewModel.provideFactory(
            getYesUseCase
        )
    }

    override fun onAttach(context: Context) {
        val homeComponent = (requireActivity().application as MainScreenComponentProvider)
            .provideMainScreenComponent()
        homeComponent.injectYesFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentYesBinding.bind(view)

        getRandomYes()

        observeViewModel()
    }

    private fun setRandomYes(yes: String?) {
        binding?.run {
            tvDes.text = yes
        }
    }

    private fun getRandomYes() {
        lifecycleScope.launch {
            viewModel.getRandomYes()
        }
    }

    private fun observeViewModel() {
        viewModel.randomYes.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            setRandomYes(it)
        }
    }
}