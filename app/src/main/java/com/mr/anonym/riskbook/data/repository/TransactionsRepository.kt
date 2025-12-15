package com.mr.anonym.riskbook.data.repository

import android.util.Log
import com.mr.anonym.riskbook.data.dao.TransactionsDAO
import com.mr.anonym.riskbook.data.model.TransactionsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
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

    override suspend fun deleteTransaction(model: TransactionsModel) {
        try {
            dao.deleteTransaction(model)
        }catch (e: Exception){
            Log.d("LocalLogging", "TransactionsRepositoryDeleteTransaction: ${e.message},")
        }
    }
}