package com.example.feature_alignment_impl.presentation.routers

import android.content.Context
import android.os.Bundle

interface SelectCardsRouter {

    fun openHome(context: Context)

    fun openAlignmentFragment(list: ArrayList<Long>, bundle: Bundle)

}