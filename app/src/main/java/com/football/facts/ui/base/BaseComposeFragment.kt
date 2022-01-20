package com.football.facts.ui.base

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import com.football.facts.R
import com.football.facts.databinding.FragmentComposeBinding
import com.football.facts.ui.theme.FootballFactsTheme
import com.football.facts.ui.utils.viewBinding.viewBinding

abstract class BaseComposeFragment : BaseFragment(R.layout.fragment_compose) {

    override val binding by viewBinding(FragmentComposeBinding::bind)

    @Composable
    abstract fun SetComposeContent()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.apply {
            setContent {
                FootballFactsTheme {
                    SetComposeContent()
                }
            }
        }
    }
}