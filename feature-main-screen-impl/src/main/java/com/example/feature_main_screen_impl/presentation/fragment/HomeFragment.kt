package com.example.feature_main_screen_impl.presentation.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.feature_main_screen_impl.R
import com.example.feature_main_screen_impl.databinding.FragmentHomeBinding
import com.example.feature_main_screen_impl.domain.GetCardUseCase
import com.example.feature_main_screen_impl.presentation.di.MainRouter
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_main_screen_impl.presentation.viewModel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*
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

    @SuppressLint("ResourceType", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        val currentDate = calendar.time
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val dateString = dateFormat.format(currentDate)
        val textView = view.findViewById<TextView>(R.id.tv_date)
        textView.text = dateString


        binding = FragmentHomeBinding.bind(view)
        val imageViewFirst = view.findViewById<ImageView>(R.id.iv_second_card)
        val imageViewSecond = view.findViewById<ImageView>(R.id.iv_third_card)
        val imageViewThird = view.findViewById<ImageView>(R.id.iv_fourth_card)


        val animatorSetFirst = AnimatorSet()
        val animatorSetSecond = AnimatorSet()
        val animatorSetThird = AnimatorSet()

        val rotateOutFirst = ObjectAnimator.ofFloat(imageViewFirst, "rotationY", 0f, 90f)
        val rotateInFirst = ObjectAnimator.ofFloat(imageViewFirst, "rotationY", -90f, 0f)

        val rotateOutSecond = ObjectAnimator.ofFloat(imageViewSecond, "rotationY", 0f, 90f)
        val rotateInSecond = ObjectAnimator.ofFloat(imageViewSecond, "rotationY", -90f, 0f)

        val rotateOutThird = ObjectAnimator.ofFloat(imageViewThird, "rotationY", 0f, 90f)
        val rotateInThird = ObjectAnimator.ofFloat(imageViewThird, "rotationY", -90f, 0f)

        rotateOutFirst.duration = 500
        rotateInFirst.duration = 500

        rotateOutSecond.duration = 500
        rotateInSecond.duration = 500

        rotateOutThird.duration = 500
        rotateInThird.duration = 500

        rotateOutFirst.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                // По завершении первой анимации заменяем изображение
                imageViewFirst.setImageResource(R.drawable.firstcard)


                // Запускаем вторую анимацию
                rotateInFirst.start()
            }
        })

        rotateOutSecond.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                // По завершении первой анимации заменяем изображение
                imageViewSecond.setImageResource(R.drawable.firstcard)


                // Запускаем вторую анимацию
                rotateInSecond.start()
            }
        })

        rotateOutThird.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                // По завершении первой анимации заменяем изображение
                imageViewThird.setImageResource(R.drawable.firstcard)


                // Запускаем вторую анимацию
                rotateInThird.start()
            }
        })

        animatorSetFirst.playSequentially(rotateOutFirst, rotateInFirst)

        animatorSetSecond.playSequentially(rotateOutSecond, rotateInSecond)

        animatorSetThird.playSequentially(rotateOutThird, rotateInThird)

        binding?.run {
            ivSecondCard.setOnClickListener {
                if (buttonClicks == 0) {
                    buttonClicks++
                    animatorSetFirst.start()
                    viewModel.clickOnRandomCard()

                }
                else if (buttonClicks == 1) {
                    router.openCard()
                }
            }
            ivThirdCard.setOnClickListener {
                if (buttonClicks == 0) {
                    buttonClicks++

                    animatorSetSecond.start()

                }
                else if (buttonClicks == 1) {
                    router.openCard()
                }
            }
            ivFourthCard.setOnClickListener {
                if (buttonClicks == 0) {
                    buttonClicks++

                    animatorSetThird.start()

                }
                else if (buttonClicks == 1) {
                    router.openCard()
                }
            }
            btnColor.setOnClickListener {
                router.openColor()
            }
            btnDigit.setOnClickListener {
                router.openDigit()
            }
            btnCookie.setOnClickListener {
                router.openCookie()
            }
            btnYes.setOnClickListener {
                router.openYesOrNo()
            }
        }

        val bottomNavigation = view.findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                com.example.base.R.id.home_bottom -> {
                    return@setOnItemSelectedListener true
                }
                com.example.base.R.id.profile_bottom -> {
                    router.openProfile()
                    return@setOnItemSelectedListener true
                }
                com.example.base.R.id.taro_bottom -> {
                    router.openFortune()
                    return@setOnItemSelectedListener true
                }
                com.example.base.R.id.numbers_bottom -> {
                    router.openNumbers()
                }
                com.example.base.R.id.zodiac_bottom -> {
                    router.openZodiac()
                    return@setOnItemSelectedListener true
                }
            }
            false
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


















