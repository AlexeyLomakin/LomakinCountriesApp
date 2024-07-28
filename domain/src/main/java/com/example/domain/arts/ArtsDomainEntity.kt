package com.example.domain.arts

data class ArtsDomainEntity(
    val id: Long,
    val artId: Long?,
    val title: String?,
    val artistDisplay: String?,
    val imageUrl: String?,
    val totalPage: Int?,
    val currentPage: Int?
)
