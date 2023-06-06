package com.example.feature_alignment_impl.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_alignment_impl.R
import com.example.feature_alignment_impl.databinding.FragmentAlignmentsBinding
import com.example.feature_alignment_impl.presentation.routers.AlignmentsRouter
import com.example.feature_alignment_impl.presentation.di.AlignmentComponentProvider
import com.example.feature_alignment_impl.presentation.rv_alignment.AlignmentAdapter
import com.example.feature_alignment_impl.presentation.rv_alignment.ItemsRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlignmentsFragment : Fragment(R.layout.fragment_alignments) {

    private var binding: FragmentAlignmentsBinding? = null

    private var recyclerView: RecyclerView? = null

    @Inject
    lateinit var router: AlignmentsRouter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var alignmentComponent = (requireActivity().application as AlignmentComponentProvider)
            .provideAlignmentComponent()
        alignmentComponent.injectAlignmentsFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAlignmentsBinding.bind(view)
        recyclerView = binding?.rv
        rvCreator()
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigation = view.findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                com.example.base.R.id.home_bottom -> {
                    router.openHome(requireContext())
                    return@setOnItemSelectedListener true
                }
                com.example.base.R.id.profile_bottom -> {
                    router.openProfile()
                    return@setOnItemSelectedListener true
                }
                com.example.base.R.id.taro_bottom -> {
                    router.openAligment()
                    return@setOnItemSelectedListener true
                }
                com.example.base.R.id.zodiac_bottom -> {
                    router.openZodiac()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun rvCreator() {
        recyclerView?.run {
            lifecycleScope.launch {
                adapter = AlignmentAdapter(
                    ItemsRepository.alignments,
                    onItemClick = ::onAlignmentItemClick
                )
            }
        }
    }

    private fun onAlignmentItemClick(name: String, description: String) {
        var bundle : Bundle
        name.also {
            bundle = Bundle().apply {
                putString("ALIGNMENT", name)
                putString("description", description)
            }
        }
        router.openDialogFragment(bundle)
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
