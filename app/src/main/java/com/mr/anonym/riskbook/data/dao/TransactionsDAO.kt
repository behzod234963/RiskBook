package com.mr.anonym.riskbook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.anonym.riskbook.data.model.TransactionsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(model: TransactionsModel)

    @Query("SELECT * FROM transactionsmodel")
    fun getTransactions(): Flow<List<TransactionsModel>>

    @Query("SELECT * FROM transactionsmodel WHERE id = :id")
    fun getTransaction(id: Int): Flow<TransactionsModel>

    @Delete
    suspend fun deleteTransaction(model: TransactionsModel)
}