package com.example.todolist.di

import com.example.todolist.data.repository.ItemRepositoryImpl
import com.example.todolist.domain.repository.ItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ItemModule {

    @Binds
    @Singleton
    fun bindItemRepositoryImpl(itemRepositoryImpl: ItemRepositoryImpl) : ItemRepository
}