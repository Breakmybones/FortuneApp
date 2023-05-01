package com.example.feature_main_screen_impl.presentation.fragment

import android.animation.*
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.example.feature_main_screen_impl.R
import com.example.feature_main_screen_impl.databinding.FragmentHomeBinding
import com.example.feature_main_screen_impl.domain.GetCardUseCase
import com.example.feature_main_screen_impl.presentation.di.MainRouter
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_main_screen_impl.presentation.viewModel.HomeViewModel
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null

    private var buttonClicks = 0

    @Inject
    lateinit var router: MainRouter

    @Inject
    lateinit var getCardUseCase: GetCardUseCase

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.provideFactory(
            router,
            getCardUseCase
        )
    }

    override fun onAttach(context: Context) {
        val homeComponent  = (requireActivity().application as MainScreenComponentProvider)
            .provideMainScreenComponent()
        homeComponent.injectMainFragment(this)
        super.onAttach(context)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        val imageView = view.findViewById<ImageView>(R.id.iv_first_card)

        val animatorSet = AnimatorSet()

        val rotateOut = ObjectAnimator.ofFloat(imageView, "rotationY", 0f, 90f)
        val rotateIn = ObjectAnimator.ofFloat(imageView, "rotationY", -90f, 0f)

        rotateOut.duration = 500
        rotateIn.duration = 500

        rotateOut.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                // По завершении первой анимации заменяем изображение
                imageView.setImageResource(R.drawable.firstcard)

                // Запускаем вторую анимацию
                rotateIn.start()
            }
        })

        animatorSet.playSequentially(rotateOut, rotateIn)

        binding?.run {
            ivFirstCard.setOnClickListener {
                buttonClicks++
                if (buttonClicks == 1) {

                    animatorSet.start()

                } else {
                    router.openCard()
                }
            }
        }

        observeViewModel()


    }

    private fun observeViewModel() {
        with(viewModel) {
            randomCard.observe(viewLifecycleOwner) {

            }
        }
    }
}


















