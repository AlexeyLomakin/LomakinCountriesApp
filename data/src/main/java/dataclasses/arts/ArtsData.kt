package dataclasses.arts

data class ArtsData(
    val id: Long,
    val title: String,
    val artistDisplay: String,
    val imageUrl: String,
    val totalPage: Int,
    val currentPage: Int
)
