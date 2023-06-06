package com.example.feature_alignment_impl.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_alignment_impl.R
import com.example.feature_alignment_impl.databinding.FragmentSelectCardsBinding
import com.example.feature_alignment_impl.domain.AlignmentsUseCase
import com.example.feature_alignment_impl.domain.CardByIdUseCase
import com.example.feature_alignment_impl.presentation.viewmodels.AlignmentsViewModel
import com.example.feature_alignment_impl.presentation.di.AlignmentComponentProvider
import com.example.feature_alignment_impl.presentation.routers.SelectCardsRouter
import com.example.feature_alignment_impl.presentation.rv_cards.CardAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SelectCardsFragment: Fragment(R.layout.fragment_select_cards) {

    private var binding: FragmentSelectCardsBinding? = null
    private var recyclerView: RecyclerView? = null
    private var countCards: Int? =0

    private var text: TextView? = null
    private val listId = ArrayList<Long>()
    @Inject
    lateinit var router: SelectCardsRouter

    @Inject
    lateinit var alignmentsUseCase: AlignmentsUseCase

    private var name: String? = null

    private val viewModel: AlignmentsViewModel by viewModels {
        AlignmentsViewModel.provideFactory(
            router,
            alignmentsUseCase
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
        alignmentComponent.injectSelectCardsFragment(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSelectCardsBinding.bind(view)
        name = arguments?.getString("ALIGNMENT")
        rvCreator()
        text = binding?.txt
        recyclerView = binding?.rv
        recyclerView.run{
            this?.layoutManager = GridLayoutManager(context, 3)
        }
        binding?.run{
            when(name){
                "Отношения" -> {
                    txt.text = "ВЫБЕРИТЕ 7 КАРТ"
                    countCards = 7
                }
                "Финансовое благополучие" -> {
                    txt.text = "ВЫБЕРИТЕ 7 КАРТ"
                    countCards = 7
                }
                "Да или нет" -> {
                    txt.text = "ВЫБЕРИТЕ 7 КАРТ"
                    countCards = 7
                }
                "Рабочий прогноз" -> {
                    txt.text = "ВЫБЕРИТЕ 8 КАРТ"
                    countCards = 8
                }
                "Ваше будущее" -> {
                    txt.text = "ВЫБЕРИТЕ 9 КАРТ"
                    countCards = 9
                }
                else -> {
                    txt.text = "ВЫБЕРИТЕ 10 КАРТ"
                    countCards = 10
                }
            }
            selectBtn.setOnClickListener{
                onButtonClick(name)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initObservers() {
        viewModel.card.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = { detailModel ->
                    onAlignmentItemClick(detailModel.id)
                },
                onFailure = { Timber.e("error") })
        }
        viewModel.cards.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = { listModel ->
                    recyclerView?.run {
                        lifecycleScope.launch {
                            adapter = CardAdapter(
                                listModel){
                                idCard -> onAlignmentItemClick(idCard)
                            }

                        }
                    }
                },
                onFailure = { Log.e("SelectCardsFragment", "shit")})
        }
    }

    private fun rvCreator(){
        viewModel.getCards()
    }

    private fun onButtonClick(name: String?){
        when (listId.size) {
            in 0..6 -> Snackbar.make(requireView(), "Вы выбрали недостаточное количество карт", Snackbar.LENGTH_SHORT).show()
            7 -> {
                when(name){
                    "Отношения" -> openFragment()
                    "Финансовое благополучие" -> openFragment()
                    "Да или нет" -> openFragment()
                    else -> Snackbar.make(requireView(), "Вы выбрали недостаточное количество карт", Snackbar.LENGTH_SHORT).show()
                }
            }
            8 -> {
                when(name){
                    "Рабочий прогноз" -> openFragment()
                    else -> {
                        if(name == "Финансовое благополучие" || name == "Отношения" || name == "Да или нет"){
                            Snackbar.make(requireView(), "Вы выбрали слишком большое количество карт (их должно быть 7)", Snackbar.LENGTH_SHORT).show()
                        } else{
                            Snackbar.make(requireView(), "Вы выбрали недостаточное количество карт", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            9 -> {
                when(name){
                    "Ваше будущее" -> openFragment()
                    else -> {
                        if (name == "Кельтский крест"){
                            Snackbar.make(requireView(), "Вы выбрали недостаточное количество карт", Snackbar.LENGTH_SHORT).show()
                        } else{
                            Snackbar.make(requireView(), "Вы выбрали слишком большое количество карт", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            10 -> {
                when(name){
                    "Кельтский крест" -> openFragment()
                    else -> Snackbar.make(requireView(), "Вы выбрали слишком большое количество карт", Snackbar.LENGTH_SHORT).show()
                }
            }
            else -> Snackbar.make(requireView(), "Вы выбрали слишком большое количество карт", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun onAlignmentItemClick(id: Long): Boolean{
        return if (!listId.contains(id)){
            listId.add(id)
            countCards = countCards?.minus(1)
            if(countCards!! < 0){
                setText(0)
            }else{
                setText(countCards)
            }
            true
        }else{
            listId.remove(id)
            countCards = countCards?.plus(1)
            setText(countCards)
            false
        }

    }

    private fun openFragment(){
        var bundle: Bundle
        name.also {
            bundle = Bundle().apply {
                putString("alignment", name)
            }
        }
        viewModel.openAlignmentFragment(listId, bundle)
    }

    @SuppressLint("SetTextI18n")
    private fun setText(count:Int?){
        when(count){
            1-> text?.text = "ВЫБЕРИТЕ 1 КАРТУ"
            in 2..4 -> text?.text = "ВЫБЕРИТЕ $count КАРТЫ"
            else -> text?.text = "ВЫБЕРИТЕ $count КАРТ"
        }
    }
}