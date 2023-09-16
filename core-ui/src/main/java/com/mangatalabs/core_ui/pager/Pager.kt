package com.mangatalabs.core_ui.pager

interface Pager<Key,Item> {
    suspend fun loadNext()
    fun reset()
}