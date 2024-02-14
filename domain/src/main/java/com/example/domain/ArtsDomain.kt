package com.example.domain

data class ArtsDomain(
    val id: Long,
    val title: String,
    val artistDisplay: String,
    val imageUrl: String,
    val totalPage: Int,
    val currentPage: Int
)
