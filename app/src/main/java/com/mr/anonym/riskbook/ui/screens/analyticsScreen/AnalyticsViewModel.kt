package com.mr.anonym.riskbook.ui.screens.analyticsScreen

import android.icu.util.Calendar
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.riskbook.data.model.MonthlyProfit
import com.mr.anonym.riskbook.data.model.TransactionsModel
import com.mr.anonym.riskbook.data.repository.TransactionsRepository
import com.mr.anonym.riskbook.presentation.extensions.monthConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val repository: TransactionsRepository
) : ViewModel() {

//    Object
    val calendarInstance: Calendar? = Calendar.getInstance()

//    Int
    val currentYear = calendarInstance?.get(Calendar.YEAR) ?:0
    val currentMonth = calendarInstance?.get(Calendar.MONTH)?:0

//    List
    private val _yearlyTransactions = mutableStateOf( emptyList<MonthlyProfit>() )
    private val _monthlyTransactions = mutableStateOf( emptyList<TransactionsModel>() )
    val monthlyTransactions: State<List<TransactionsModel>> = _monthlyTransactions
    private val _profits = mutableStateListOf<Float>()
    val profits: List<Float> = _profits
    private val _labels = mutableStateListOf<String>()
    val labels: List<String> = _labels
    private val _years = mutableStateOf( emptyList<Int>() )
    val years: State<List<Int>> = _years
    private val _months = mutableStateOf( emptyList<Int>() )
    val months: State<List<Int>> = _months

    init {
//        getTransactions()
        getYearlyTransactions(currentYear)
        getMonthlyTransactions(currentYear,currentMonth)
        getYears()
        getMonths()
//        getMonthlyTransactions(currentMonth)
    }
    fun getYearlyTransactions(year: Int) = viewModelScope.launch {
        repository.getYearlyTransactions(year).collect { list->
            _yearlyTransactions.value = list
//            _yearlyTransactions.value.groupBy { it.month }
            _profits.clear()
            _labels.clear()

            _yearlyTransactions.value.forEach { model ->
                _profits.add(model.totalProfit.toFloat())
                _labels.add(model.month.monthConverter())
            }
        }
    }

    fun getMonthlyTransactions(year: Int,month: Int) = viewModelScope.launch {
        repository.getMonthlyTransactions(year,month).collect {
            _monthlyTransactions.value = it
        }
    }
    fun getYears() = viewModelScope.launch {
        repository.getYears().collect {
            _years.value = it
        }
    }
    fun getMonths() = viewModelScope.launch {
        _months.value = listOf(
            0,1,2,3,4,5,6,7,8,9,10,11
        )
    }
}