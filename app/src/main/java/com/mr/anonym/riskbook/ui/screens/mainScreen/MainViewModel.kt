package com.mr.anonym.riskbook.ui.screens.mainScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import com.mr.anonym.riskbook.data.model.TransactionsModel
import com.mr.anonym.riskbook.data.repository.TransactionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TransactionsRepository,
) : ViewModel() {

    private val _transactions = mutableStateOf(emptyList<TransactionsModel>())
    val transactions: State<List<TransactionsModel>> = _transactions
    val points = mutableStateListOf<Point>()
    val labels = mutableStateListOf<String>()
    private val _steps = mutableIntStateOf(0)
    private val _tabs = mutableStateListOf<String>()
    val tabs : List<String> = _tabs

    init {
        getTransactions()
        getMarkets()
    }

    fun getTransactions() = viewModelScope.launch {
        repository.getTransactions().collect {
            _transactions.value = it.sortedWith(
                compareByDescending <TransactionsModel>{ value-> value.year }.thenByDescending { child-> child.dateAdded }
            )

            points.clear()
            labels.clear()
            _steps.intValue = 0
            transactions.value
                .asReversed()
                .forEach { model ->
                    _steps.intValue += 1
                    points.add(Point(_steps.intValue.toFloat(), model.profit.toFloat()))
                    labels.add(model.profit.toString())
                }
        }
    }
    fun getTransactionsByMarket(market: String) = viewModelScope.launch {
        repository.getTransactionsByMarket(market).collect {
            _transactions.value = it.sortedWith(
                compareByDescending <TransactionsModel>{ value-> value.year }.thenByDescending { child-> child.dateAdded }
            )

            points.clear()
            labels.clear()
            _steps.intValue = 0
            transactions.value
                .asReversed()
                .forEach { model ->
                    _steps.intValue += 1
                    points.add(Point(_steps.intValue.toFloat(),model.profit.toFloat()))
                    labels.add(model.profit.toString())
                }
        }
    }
    fun getMarkets() = viewModelScope.launch {
        repository.getMarkets().collect {
            _tabs.clear()
            _tabs.add("All")
            _tabs.addAll(it)
        }
    }

    fun deleteTransaction(model: TransactionsModel) = viewModelScope.launch {
        repository.deleteTransaction(model)
    }
}