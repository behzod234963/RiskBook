package com.mr.anonym.riskbook.di

import android.content.Context
import androidx.room.Room
import com.mr.anonym.riskbook.data.dao.TransactionsDAO
import com.mr.anonym.riskbook.data.instance.RoomInstance
import com.mr.anonym.riskbook.data.repository.TransactionsRepository
import com.mr.anonym.riskbook.presentation.constants.RISK_ROOM_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRoomInstance(@ApplicationContext context: Context): RoomInstance =
        Room.databaseBuilder(
            context,
            RoomInstance::class.java,
            RISK_ROOM_DB,
        ).build()

    @Provides
    @Singleton
    fun provideTransactionsDAO(roomInstance: RoomInstance): TransactionsDAO =
        roomInstance.transactionsDAO

    @Provides
    @Singleton
    fun provideTransactionsRepository(roomInstance: RoomInstance): TransactionsRepository =
        TransactionsRepository(roomInstance.transactionsDAO)
}