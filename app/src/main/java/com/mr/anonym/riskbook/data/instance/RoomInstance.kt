package com.mr.anonym.riskbook.data.instance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.anonym.riskbook.data.dao.TransactionsDAO
import com.mr.anonym.riskbook.data.model.TransactionsModel

@Database(entities = [TransactionsModel::class], version = 1)
abstract class RoomInstance : RoomDatabase(){
    abstract val transactionsDAO: TransactionsDAO
}