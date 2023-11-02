package com.tr.ksbg.model.input

import java.util.UUID

class AddCommentInput(
    val text: String,
    val authorId: UUID,
    val postId: UUID
)