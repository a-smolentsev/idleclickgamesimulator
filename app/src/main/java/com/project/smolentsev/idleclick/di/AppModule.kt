package com.project.smolentsev.idleclick.di

import android.app.Application
import androidx.room.Room
import com.project.smolentsev.idleclick.data.room.ItemResDB
import com.project.smolentsev.idleclick.data.room.repository.ItemResRepoImpl
import com.project.smolentsev.idleclick.domain.repo.ItemListRepo
import com.project.smolentsev.idleclick.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): ItemResDB {
        return Room.databaseBuilder(
            app,
            ItemResDB::class.java,
            ItemResDB.DATABASE_NAME
        )
            .createFromAsset("database/game.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: ItemResDB): ItemListRepo{
        return ItemResRepoImpl(db.itemResDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: ItemListRepo): UseCases {
        return UseCases(
            getItemUseCase = GetItemUseCase(repository),
            getItemListUseCase = GetItemListUseCase(repository),
            editItemUseCase = EditItemUseCase(repository),

            getCharUseCase = GetCharUseCase(repository),
            getCharListUseCase = GetCharListUseCase(repository),
            editCharUseCase = EditCharUseCase(repository),

            //updateShortDescUseCase = UpdateShortDescUseCase(repository)
        )
    }
}
