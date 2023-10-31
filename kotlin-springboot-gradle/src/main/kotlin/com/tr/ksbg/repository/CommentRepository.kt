package com.tr.ksbg.repository

import com.tr.ksbg.model.entity.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CommentRepository: JpaRepository<CommentEntity, UUID> {
    fun findAllByPostId(postId: UUID): List<CommentEntity>
    fun findAllByAuthorId(userId: UUID): List<CommentEntity>
}