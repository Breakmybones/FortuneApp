package com.example.feature_alignment_impl.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.feature_alignment_api.domain.models.CardsModel
import com.example.feature_alignment_impl.R
import com.example.feature_alignment_impl.databinding.FragmentAlignmentBinding
import com.example.feature_alignment_impl.databinding.FragmentDetailBinding
import com.example.feature_alignment_impl.domain.CardByIdUseCase
import com.example.feature_alignment_impl.domain.CardsByIdUseCase
import com.example.feature_alignment_impl.presentation.di.AlignmentComponentProvider
import com.example.feature_alignment_impl.presentation.routers.AlignmentRouter
import com.example.feature_alignment_impl.presentation.routers.CardInfoRouter
import com.example.feature_alignment_impl.presentation.viewmodels.AlignmentViewModel
import com.example.feature_alignment_impl.presentation.viewmodels.DetailViewModel
import timber.log.Timber
import javax.inject.Inject

class DetailFragment: Fragment(R.layout.fragment_detail) {
    private var binding: FragmentDetailBinding? = null
    @Inject
    lateinit var cardByIdUseCase: CardByIdUseCase
    @Inject
    lateinit var router: CardInfoRouter

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(
            cardByIdUseCase
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initObservers()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val alignmentComponent = (requireActivity().application as AlignmentComponentProvider)
            .provideAlignmentComponent()
        alignmentComponent.injectDetailFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDetailBinding.bind(view)
        val cardId = arguments?.getLong("cardId")
        if (cardId != null) {
            initCard(cardId)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadCard(card: CardsModel){
        card.also{
            if(card.name == null){
                binding?.tvName?.text = "${card.dignity} ${card.suit}"
            }else{
                binding?.tvName?.text = card.name
            }
            binding?.tvDescription?.text = card.description
        }
    }

    private fun initCard(id: Long){
        viewModel.getCardById(id)
    }

    private fun initObservers() {
        viewModel.card.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = {
                            loadCard(it)
                },
                onFailure = {
                    Log.e("DetailFragment", "shit happend")
                }
            )
        }

    }
}