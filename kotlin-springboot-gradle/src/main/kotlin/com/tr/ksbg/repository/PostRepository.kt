package com.tr.ksbg.repository

import com.tr.ksbg.model.entity.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PostRepository: JpaRepository<PostEntity, UUID> {

    fun findAllByAuthorId(authorId: UUID): List<PostEntity>
}