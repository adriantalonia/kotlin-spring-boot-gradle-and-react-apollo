package com.tr.ksbg.model.dto

import java.util.UUID

data class Post(
    val id: UUID?,
    val title: String,
    val description: String?
)