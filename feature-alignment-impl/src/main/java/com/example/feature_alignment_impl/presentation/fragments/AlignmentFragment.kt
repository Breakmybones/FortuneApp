package com.example.feature_alignment_impl.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_alignment_impl.R
import com.example.feature_alignment_impl.databinding.FragmentAlignmentBinding
import com.example.feature_alignment_impl.domain.CardsByIdUseCase
import com.example.feature_alignment_impl.presentation.di.AlignmentComponentProvider
import com.example.feature_alignment_impl.presentation.routers.AlignmentRouter
import com.example.feature_alignment_impl.presentation.rv_detail.DetailAdapter
import com.example.feature_alignment_impl.presentation.viewmodels.AlignmentViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class AlignmentFragment: Fragment(R.layout.fragment_alignment) {

    private var binding: FragmentAlignmentBinding? = null
    private var recyclerView: RecyclerView? = null

    @Inject
    lateinit var cardsByIdUseCase: CardsByIdUseCase


    @Inject
    lateinit var router: AlignmentRouter

    private val viewModel: AlignmentViewModel by viewModels {
        AlignmentViewModel.provideFactory(
            router,
            cardsByIdUseCase
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
        alignmentComponent.injectAlignmentFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAlignmentBinding.bind(view)
        val listId = ArrayList<Long>()
        val name = arguments?.getString("alignment")
        arguments?.getIntegerArrayList("list")?.forEach {
            element -> listId.add(element.toLong())
        }
        with(binding){
            recyclerView = this?.rv
            this?.txt?.text = name
        }
        recyclerView.run{
            this?.layoutManager = GridLayoutManager(context, 3)
        }
        rvCreator(listId)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onAlignmentItemClick(id: Long){
        var bundle: Bundle
        id.also {
            bundle = Bundle().apply {
                putLong("cardId", id)
            }
        }
        viewModel.openDetailFragment(bundle)
    }

    private fun rvCreator(listId: List<Long>){
        val idsString: String = TextUtils.join(",", listId)
        viewModel.getCardsById(idsString)
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
                            adapter = DetailAdapter(listModel){
                                    idCard -> onAlignmentItemClick(idCard)
                            }
                        }
                    }
                },
                onFailure = { Log.e("AlignmentFragment", "shit")})
        }
    }
}
