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
import com.example.feature_main_screen_impl.databinding.FragmentCardBinding
import com.example.feature_main_screen_impl.domain.GetCardUseCase
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_main_screen_impl.presentation.viewModel.CardViewModel
import com.example.feature_main_screen_impl.presentation.viewModel.HomeViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class CardFragment : Fragment(R.layout.fragment_card) {

    private var binding: FragmentCardBinding? = null

    @Inject
    lateinit var getCardUseCase: GetCardUseCase

    private val viewModel: CardViewModel by viewModels {
        CardViewModel.provideFactory(
            getCardUseCase
        )
    }

    override fun onAttach(context: Context) {
        val homeComponent = (requireActivity().application as MainScreenComponentProvider)
            .provideMainScreenComponent()
        homeComponent.injectCardFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCardBinding.bind(view)

        getRandomCard()

        observeViewModel()
    }

    private fun setRandomCard(card: CardModel) {
        binding?.run {
            tvName.text = card.suit
            tvDes.text = card.description
        }
    }

    private fun getRandomCard() {
        lifecycleScope.launch {
            viewModel.getRandomCard()
        }
    }

    private fun observeViewModel() {
        viewModel.randomCard.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            setRandomCard(it)
        }
    }
}