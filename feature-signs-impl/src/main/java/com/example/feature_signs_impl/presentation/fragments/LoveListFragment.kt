package com.example.feature_signs_impl.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_signs_api.model.ListResponse
import com.example.feature_signs_api.model.ZodiacInfo
import com.example.feature_signs_impl.R
import com.example.feature_signs_impl.databinding.FragmentLoveListBinding
import com.example.feature_signs_impl.domain.ZodiacUseCase
import com.example.feature_signs_impl.presentation.di.ZodiacComponentProvider
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import com.example.feature_signs_impl.presentation.rv_zodiac.ZodiacAdapter
import com.example.feature_signs_impl.presentation.viewmodel.ZodiacViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoveListFragment: Fragment(R.layout.fragment_love_list) {

    private var binding: FragmentLoveListBinding? = null
    private var recyclerView: RecyclerView? = null

    @Inject
    lateinit var zodiacUseCase: ZodiacUseCase

    @Inject
    lateinit var router: ZodiacRouter

    private val viewModel: ZodiacViewModel by viewModels {
        ZodiacViewModel.provideFactory(
            router,
            zodiacUseCase
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var zodiacComponent = (requireActivity().application as ZodiacComponentProvider)
            .provideZodiacComponent()
        zodiacComponent.injectLoveListFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initObservers()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoveListBinding.bind(view)

        recyclerView = binding?.rv
        val sign = arguments?.getString("sign")
        if(sign!=null) {
            rvCreator(sign)
        }
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

    private fun rvCreator(sign: String) {
        viewModel.getZodiac(sign)
    }

    private fun onZodiacItemClick(name: String) {
        var bundle : Bundle
        name.also {
            bundle = Bundle().apply {
                putString("sign", name)
            }
        }
        router.openZodiacInfoFragment(bundle)
    }

    private fun initObservers(){
        viewModel.zodiac.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = { listModel ->
                    recyclerView?.run {
                        lifecycleScope.launch {
                            adapter = ZodiacAdapter(filterByLove(listModel), true){
                                    name -> onZodiacItemClick(name)
                            }
                        }
                    }
                },
                onFailure = { Log.e("LoveListFragment", "shit")})
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun filterByLove(zodiacList: ListResponse): List<ZodiacInfo> {
        return zodiacList.list.sortedByDescending { it.love }
    }
}