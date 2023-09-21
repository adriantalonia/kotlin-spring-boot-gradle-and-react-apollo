package com.tr.ksbg.repository

import com.tr.ksbg.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findByPostsId(postId: UUID): UserEntity
}