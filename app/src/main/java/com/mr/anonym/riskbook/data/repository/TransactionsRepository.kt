package com.mr.anonym.riskbook.data.repository

import android.util.Log
import com.mr.anonym.riskbook.data.dao.TransactionsDAO
import com.mr.anonym.riskbook.data.model.MonthlyProfit
import com.mr.anonym.riskbook.data.model.TransactionsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionsRepository @Inject constructor(private val dao: TransactionsDAO): TransactionsDAO {
    override suspend fun insertTransaction(model: TransactionsModel) {
        try {
            dao.insertTransaction(model)
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryInsertTransaction: ${e.message},")
        }
    }

    override fun getTransactions(): Flow<List<TransactionsModel>> = flow {
        try {
            dao.getTransactions().collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryGetTransactions: ${e.message},")
        }
    }

    override fun getTransaction(id: Int): Flow<TransactionsModel> = flow {
        try {
            dao.getTransaction(id).collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryGetTransaction: ${e.message},")
        }
    }

    override fun getYearlyTransactions(year: Int): Flow<List<MonthlyProfit>> = flow {
        try {
            dao.getYearlyTransactions(year).collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryGetYearlyTransactions: ${e.message},")
        }
    }

    override fun getMonthlyTransactions(year: Int, month: Int): Flow<List<TransactionsModel>> = flow {
        try {
            dao.getMonthlyTransactions(year,month).collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryGetMonthlyTransactions: ${e.message},")
        }
    }

    override fun getYears(): Flow<List<Int>> = flow {
        try {
            dao.getYears().collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryGetYears: ${e.message},")
        }
    }

    override fun getMonths(): Flow<List<Int>> = flow {
        try {
            dao.getMonths().collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryGetMonths: ${e.message},")
        }
    }

    override fun getMarkets(): Flow<List<String>> = flow {
        try {
            dao.getMarkets().collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryGetMarkets: ${e.message},")
        }
    }

    override fun getTransactionsByMarket(market: String): Flow<List<TransactionsModel>> = flow {
        try {
            dao.getTransactionsByMarket(market).collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryGetTransactionsByMarket: ${e.message},")
        }
    }

    override suspend fun deleteTransaction(model: TransactionsModel) {
        try {
            dao.deleteTransaction(model)
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryDeleteTransaction: ${e.message},")
        }
    }
}