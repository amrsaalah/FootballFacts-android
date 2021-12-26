package com.football.facts.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.football.facts.domain.valueObject.Resource
import com.football.facts.domain.valueObject.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

}