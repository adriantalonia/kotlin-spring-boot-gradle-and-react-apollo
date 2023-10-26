package com.tr.ksbg.service

import com.tr.ksbg.model.dto.Post
import com.tr.ksbg.model.entity.PostEntity
import com.tr.ksbg.model.input.AddPostInput
import com.tr.ksbg.repository.PostRepository
import com.tr.ksbg.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.UUID

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) {

    fun getPosts(): List<Post> {
        return postRepository.findAll().map {
            Post(
                id = it.id,
                title = it.title,
                description = it.description
            )
        }
    }

    fun getPosts(page: Int, size: Int): List<Post> {
        val page = PageRequest.of(page, size)
        return postRepository.findAll(page).map {
            Post(
                id = it.id,
                title = it.title,
                description = it.description
            )
        }.toList()
    }

    fun getPotsByAuthor(userId: UUID): List<Post> {
        return postRepository.findAllByAuthorId(userId)
            .map {
                Post(
                    id = it.id,
                    title = it.title,
                    description = it.description
                )

            }
    }

    fun addPost(addPostInput: AddPostInput): Post {
        val user = userRepository.findById(addPostInput.authorId)
            .orElseThrow { RuntimeException("UserId is not valid, userId: ${addPostInput.authorId}") }

        val postEntity = PostEntity(
            title = addPostInput.title,
            description = addPostInput.description,
            author = user
        )

        val createdPost = postRepository.save(postEntity)

        return Post(
            id = createdPost.id,
            title = createdPost.title,
            description = createdPost.description
        )
    }

    fun getPostByCommentId(commentId: UUID): Post {
        commentId ?: throw RuntimeException("commentId can not be null")
        val postEntity = postRepository.findByCommentsId(commentId)
        return Post(
            id = postEntity.id,
            title = postEntity.title,
            description = postEntity.description
        )
    }
}
