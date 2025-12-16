package com.mr.anonym.riskbook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.anonym.riskbook.data.model.MonthlyProfit
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

    @Query("""
        SELECT *, SUM(profit) AS totalProfit
         FROM transactionsmodel
         WHERE year = :year
          GROUP BY month 
          ORDER BY month""")
    fun getYearlyTransactions(year: Int): Flow<List<MonthlyProfit>>

    @Query("SELECT year FROM transactionsmodel GROUP BY year")
    fun getYears(): Flow<List<Int>>

    @Query("SELECT month FROM transactionsmodel GROUP BY month")
    fun getMonths(): Flow<List<Int>>

    @Query("SELECT * FROM transactionsmodel WHERE year = :year AND month = :month ORDER BY month")
    fun getMonthlyTransactions(year: Int,month: Int): Flow<List<TransactionsModel>>

    @Delete
    suspend fun deleteTransaction(model: TransactionsModel)
}