package com.football.facts.ui.base

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment(resId : Int) : Fragment(resId) {
    abstract val viewModel : BaseViewModel
    abstract val binding : ViewBinding
}