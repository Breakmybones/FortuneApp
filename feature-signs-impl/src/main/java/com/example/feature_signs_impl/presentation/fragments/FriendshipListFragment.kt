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
import kotlinx.coroutines.launch
import javax.inject.Inject

class FriendshipListFragment : Fragment(R.layout.fragment_love_list){

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
        zodiacComponent.injectFriendshipListFragment(this)
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
        val sign = arguments?.getString("sign")  //delete line
//        val email = getCurrentUser()?.email //
        if(sign!=null) {
            rvCreator(sign)
        }
        super.onViewCreated(view, savedInstanceState)
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
        router.openZodiacInfoFragmentFromFriend(bundle)
    }

    private fun initObservers(){
        viewModel.zodiac.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = { listModel ->
                    recyclerView?.run {
                        lifecycleScope.launch {
                            adapter = ZodiacAdapter(filterByFriendship(listModel), false){
                                    name -> onZodiacItemClick(name)
                            }
                        }
                    }
                },
                onFailure = { Log.e("LoveListFragment", "shit")})
        }
    }

    private fun filterByFriendship(zodiacList: ListResponse): List<ZodiacInfo> {
        return zodiacList.list.sortedByDescending { it.friendship }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}