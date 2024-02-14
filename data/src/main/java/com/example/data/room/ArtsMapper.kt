package com.example.data.room

interface ArtsMapper<I,O> {
    fun map(input: I): O
}