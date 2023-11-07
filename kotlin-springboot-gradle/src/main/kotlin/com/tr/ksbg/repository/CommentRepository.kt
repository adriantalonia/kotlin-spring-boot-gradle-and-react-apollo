package com.tr.ksbg.repository

import com.tr.ksbg.model.entity.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface CommentRepository: JpaRepository<CommentEntity, UUID> {
    fun findAllByPostId(postId: UUID): List<CommentEntity>
    fun findAllByAuthorId(userId: UUID): List<CommentEntity>
    @Query("select c from CommentEntity c where c.post.id in ?1")
    fun findAllByPostIds(postIds: List<UUID>): List<CommentEntity>
}