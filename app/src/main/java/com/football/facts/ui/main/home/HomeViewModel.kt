package com.football.facts.ui.main.home

import androidx.lifecycle.viewModelScope
import com.football.facts.R
import com.football.facts.domain.usecase.CheckApiStatusUseCase
import com.football.facts.domain.valueObject.Status
import com.football.facts.ui.base.BaseViewModel
import com.football.facts.ui.utils.navigation.NavigationManager
import com.football.facts.ui.utils.notification.ToastManager
import com.football.facts.ui.utils.resource.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkApiStatusUseCase: CheckApiStatusUseCase ,
    private val toastManager: ToastManager ,
    private val navigationManager: NavigationManager ,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {


    val isReady = checkApiStatusUseCase().map {
        if(it.status == Status.SUCCESS && it.data == false){
            toastManager.showMessage(resourceProvider.getString(R.string.api_status_failed))
        }
        it.data ?: false
    }.stateIn(viewModelScope , SharingStarted.Eagerly , false)



}