package com.tr.ksbg.service

import com.tr.ksbg.model.dto.User
import com.tr.ksbg.model.entity.UserEntity
import com.tr.ksbg.model.input.AddUserInput
import com.tr.ksbg.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun findByPostId(postId: UUID): User {
        val userEntity = userRepository.findByPostsId(postId)
        return User(
            id = userEntity.id,
            name = userEntity.name
        )
    }

    fun addUser(addUserInput: AddUserInput): UUID {
        val userEntity = UserEntity(name = addUserInput.name)
        val user = userRepository.save(userEntity)
        user.id ?: throw RuntimeException("")
        return user.id
    }

    fun getUsers(page: Int, size: Int): List<User> {
        val users = userRepository.findAll(PageRequest.of(page, size))
        return users.map {
            User(
                id = it.id,
                name = it.name
            )
        }.toList()
    }

    fun findByCommentId(commentId: UUID): User {
        commentId ?: throw RuntimeException("comment id can not be null")
        val userEntity = userRepository.findByCommentsId(commentId)
        return User(
            id = userEntity.id,
            name = userEntity.name
        )
    }
}