package com.mr.anonym.riskbook.ui.screens.conclusionsScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.riskbook.data.model.TransactionsModel
import com.mr.anonym.riskbook.data.repository.TransactionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ConclusionViewModel @Inject constructor(
    private val repository: TransactionsRepository
): ViewModel() {

//    List
    private val _transactions = mutableStateOf( emptyList<TransactionsModel>() )
    val transactions: State<List<TransactionsModel>> = _transactions

//    Business-logic
    init {
        getTransactions()
    }
    fun getTransactions() = viewModelScope.launch {
        repository.getTransactions().collect {
            _transactions.value = it
        }
    }
}