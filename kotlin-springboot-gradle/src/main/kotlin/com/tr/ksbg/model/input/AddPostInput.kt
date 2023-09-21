package com.tr.ksbg.model.input

import java.util.UUID

data class AddPostInput(
    val id: UUID?,
    val title: String,
    val description: String?,
    val authorId: UUID
)