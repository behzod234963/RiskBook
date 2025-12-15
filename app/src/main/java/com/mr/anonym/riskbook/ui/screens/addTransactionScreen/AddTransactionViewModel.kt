package com.mr.anonym.riskbook.ui.screens.addTransactionScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.riskbook.data.model.TransactionsModel
import com.mr.anonym.riskbook.data.repository.TransactionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val transactionsRepository: TransactionsRepository,
) : ViewModel() {

    //    String
    private val _pairValue = mutableStateOf("")
    val pairValue: State<String> = _pairValue
    private val _riskPercentValue = mutableStateOf("")
    val riskPercentValue: State<String> = _riskPercentValue
    private val _riskVolumeValue = mutableStateOf("")
    val riskVolumeValue: State<String> = _riskVolumeValue
    private val _entryValue = mutableStateOf("")
    val entryValue: State<String> = _entryValue
    private val _stopLossValue = mutableStateOf("")
    val stopLossValue: State<String> = _stopLossValue
    private val _marginValue = mutableStateOf("")
    val marginValue: State<String> = _marginValue
    private val _leverageValue = mutableStateOf("")
    val leverageValue: State<String> = _leverageValue
    private val _commentForEntryValue = mutableStateOf("")
    val commentForEntryValue: State<String> = _commentForEntryValue
    private val _commentForTakeValue = mutableStateOf("")
    val commentForTakeValue: State<String> = _commentForTakeValue
    private val _finalConclusionValue = mutableStateOf("")
    val finalConclusionValue: State<String> = _finalConclusionValue
    private val _profitValue = mutableStateOf("")
    val profitValue: State<String> = _profitValue
    private val _amountValue = mutableDoubleStateOf(0.0)
    val amountValue: State<Double> = _amountValue
    private val _dateAdded = mutableStateOf("")
    val dateAdded: State<String> = _dateAdded
    private val _position = mutableStateOf(true)
    val position: State<Boolean> = _position
    private val _takeProfitValue = mutableStateOf("")
    val takeProfitValue: State<String> = _takeProfitValue

    //    Int
    private val _id = mutableIntStateOf(-1)
    val id: State<Int> = _id

    //    Business-logic
    init {
        savedState.get<Int>("id")?.let {
            _id.intValue = it
            if (it != -1) {
                viewModelScope.launch {
                    transactionsRepository.getTransaction(it).collect { transactionsModel ->
                        _pairValue.value = transactionsModel.pair
                        _riskPercentValue.value = transactionsModel.riskPercent
                        _riskVolumeValue.value = transactionsModel.riskVolume.toString()
                        _entryValue.value = transactionsModel.entryPrice.toString()
                        _stopLossValue.value = transactionsModel.stopLoss.toString()
                        _marginValue.value = transactionsModel.margin.toString()
                        _leverageValue.value = transactionsModel.leverage
                        _commentForEntryValue.value = transactionsModel.commentForEntry
                        _commentForTakeValue.value = transactionsModel.commentForTakeProfit
                        _finalConclusionValue.value = transactionsModel.finalConclusion
                        _profitValue.value = transactionsModel.profit.toString()
                        _amountValue.doubleValue = transactionsModel.amount
                        _dateAdded.value = transactionsModel.dateAdded
                        _position.value = transactionsModel.position
                        _takeProfitValue.value = transactionsModel.takeProfit.toString()
                    }
                }
            }
        }
    }

    fun insertTransaction(model: TransactionsModel) = viewModelScope.launch {
        transactionsRepository.insertTransaction(model)
    }

    fun changePairValue(value: String) {
        _pairValue.value = value
    }

    fun changeRiskPercentValue(value: String) {
        _riskPercentValue.value = value
    }

    fun changeRiskVolumeValue(value: String) {
        _riskVolumeValue.value = value
    }

    fun changeEntryValue(value: String) {
        _entryValue.value = value
    }

    fun changeStopLossValue(value: String) {
        _stopLossValue.value = value
    }

    fun changeMarginValue(value: String) {
        _marginValue.value = value
    }

    fun changeLeverageValue(value: String) {
        _leverageValue.value = value
    }

    fun changeCommentForEntryValue(value: String) {
        _commentForEntryValue.value = value
    }

    fun changeCommentForTakeValue(value: String) {
        _commentForTakeValue.value = value
    }

    fun changeFinalConclusionValue(value: String) {
        _finalConclusionValue.value = value
    }

    fun changeProfitValue(value: String) {
        _profitValue.value = value
    }

    fun changeAmountValue(value: Double) {
        _amountValue.doubleValue = value
    }

    fun changePosition(state: Boolean) {
        _position.value = state
    }

    fun changeTakeProfitValue(value: String) {
        _takeProfitValue.value = value
    }
}