package com.example.fortuneproject.di

import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import com.example.feature_alignment_impl.presentation.routers.AlignmentRouter
import com.example.feature_alignment_impl.presentation.routers.AlignmentsRouter
import com.example.feature_alignment_impl.presentation.routers.CardInfoRouter
import com.example.feature_alignment_impl.presentation.routers.SelectCardsRouter
import com.example.feature_main_screen_impl.presentation.di.MainRouter
import com.example.feature_profile_screen_impl.presentation.di.EditProfileRouter
import com.example.feature_profile_screen_impl.presentation.di.ProfileRouter
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import com.example.featureregistrationimpl.presentation.di.LoginRouter
import com.example.featureregistrationimpl.presentation.di.RegisterRouter
import com.example.fortuneproject.MainActivity
import com.example.fortuneproject.R

class Navigator: LoginRouter, RegisterRouter, MainRouter, ProfileRouter, EditProfileRouter,
    ZodiacRouter,
    AlignmentsRouter,
    SelectCardsRouter,
    AlignmentRouter,
    CardInfoRouter {

    private var navController: NavController? = null

    fun initialize(navController: NavController) {
        this.navController = navController
    }

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun openRegister() {
        navController?.navigate(R.id.register_fragment)
    }

    override fun openHome() {
        navController?.navigate(R.id.home_fragment)
    }

    override fun openLogin(context: Context) {
        MainActivity.start(context)
    }

    override fun openProfile() {
        navController?.navigate(R.id.profile_fragment)
    }

    override fun openFortune() {
        TODO("Not yet implemented")
    }

    override fun openZodiac() {
        navController?.navigate(R.id.selectDialogFragment)
    }

    override fun openNumbers() {
        TODO("Not yet implemented")
    }

    override fun openCard() {
        navController?.navigate(R.id.card_fragment)
    }

    override fun openColor() {
        navController?.navigate(R.id.color_fragment)
    }

    override fun openDigit() {
        navController?.navigate(R.id.digit_fragment)
    }

    override fun openYesOrNo() {
        navController?.navigate(R.id.yes_fragment)
    }

    override fun openCookie() {
        navController?.navigate(R.id.cookie_fragment)
    }

    override fun openAligment() {
        navController?.navigate(R.id.fragment_alignments)
    }

    override fun openChangeProfile() {
        navController?.navigate(R.id.edit_profile_fragment)
    }

    override fun openHomeFragment() {
        navController?.navigate(R.id.home_fragment)
    }

    override fun openAlignments() {
        navController?.navigate(R.id.action_fragment_detail_to_fragment_alignments)

    }

    override fun openSelectCards(bundle: Bundle) {
        navController?.navigate(R.id.action_fragment_dialog_to_fragment_select_cards, bundle)

    }

    override fun openDialogFragment(bundle: Bundle) {
        navController?.navigate(R.id.action_fragment_alignments_to_fragment_dialog, bundle)
    }

    override fun openHome(context: Context) {
//        navController?.navigate(R.id.register_fragment)
    }

    override fun openAlignmentFragment(list: ArrayList<Long>, bundle: Bundle) {
        val res = ArrayList<Int>()
        list.forEach{ elem ->
            res.add(elem.toInt())
        }
        res.also {
            bundle.putIntegerArrayList("list", res)

        }
        navController?.navigate(R.id.fragment_alignment, bundle)
    }

    override fun openStart(context: Context) {
        MainActivity.start(context)
    }

    override fun openDetailFragment(bundle: Bundle) {
        navController?.navigate(R.id.fragment_detail, bundle)
    }

    override fun openChat(bundle: Bundle) {
        navController?.navigate(R.id.action_zodiacInfoFragment_to_chatFragment, bundle)
    }

    override fun openLoveFragment(bundle: Bundle) {
        navController?.navigate(R.id.action_selectDialogFragment_to_loveListFragment, bundle)
    }

    override fun openFriendshipFragment(bundle: Bundle) {
        navController?.navigate(R.id.action_selectDialogFragment_to_friendshipListFragment, bundle)
    }

    override fun openZodiacInfoFragment(bundle: Bundle) {
        navController?.navigate(R.id.action_loveListFragment_to_zodiacInfoFragment, bundle)
    }

    override fun openZodiacInfoFragmentFromFriend(bundle: Bundle) {
        navController?.navigate(R.id.action_friendshipListFragment_to_zodiacInfoFragment, bundle)
    }
}

