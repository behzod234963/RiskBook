package com.mr.anonym.riskbook.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("TransactionsModel")
data class TransactionsModel(
    @PrimaryKey val id: Int = (0..999999).random(),
    val market: String = "",
    val pair: String = "",
    val riskPercent: String = "",
    val riskVolume: Int = -1,
    val entryPrice: Double = 0.0,
    val stopLoss: Double = 0.0,
    val takeProfit: Double = 0.0,
    val position: Boolean = true,
    val margin: Double = 0.0,
    val leverage: String = "",
    val amount: Double = 0.0,
    val commentForEntry: String = "",
    val commentForTakeProfit: String = "",
    val finalConclusion: String = "",
    val profit: Double = 0.0,
    val dateAdded: String = "",
    val month: Int = 0,
    val year: Int = 0
)