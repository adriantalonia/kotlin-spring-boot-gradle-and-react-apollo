package com.tr.ksbg.service

import com.tr.ksbg.graphql.resolver.User
import com.tr.ksbg.repository.PostRepository
import com.tr.ksbg.repository.UserRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {

    fun findByPostId(postId: UUID): User {
        val postEntity = postRepository.findById(postId).orElseThrow {
            RuntimeException("Post does not exists for this user portId: $postId")
        }

        return User(
            id = postEntity.author.id,
            name = postEntity.author.name
        )

    }
}