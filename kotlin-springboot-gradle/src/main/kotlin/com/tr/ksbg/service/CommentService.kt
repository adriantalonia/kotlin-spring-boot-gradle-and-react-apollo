package com.tr.ksbg.service

import com.tr.ksbg.model.dto.Comment
import com.tr.ksbg.model.dto.Post
import com.tr.ksbg.model.entity.CommentEntity
import com.tr.ksbg.model.input.AddCommentInput
import com.tr.ksbg.repository.CommentRepository
import com.tr.ksbg.repository.PostRepository
import com.tr.ksbg.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository) {

    fun getComments(page: Int, size: Int): List<Comment> {
        return commentRepository.findAll(PageRequest.of(page, size)).map {
            Comment(
                id = it.id!!,
                text = it.text
            )
        }.toList()
    }

    fun getCommentsByPostId(postId: UUID?): List<Comment> {
        postId ?: throw RuntimeException("post id can not be null")
        return commentRepository.findAllByPostId(postId).map {
            Comment(
                id = it.id!!,
                text = it.text
            )
        }.toList()
    }

    fun getCommentsByUserId(userId: UUID?): List<Comment> {
        userId ?: throw RuntimeException("user id can not be null")
        return commentRepository.findAllByAuthorId(userId).map {
            Comment(
                id = it.id!!,
                text = it.text
            )
        }.toList()
    }

    fun addComment(addCommentInput: AddCommentInput): Comment {
        val user = userRepository.findById(addCommentInput.authorId)
            .orElseThrow { RuntimeException("user does not exist with id: ${addCommentInput.authorId}") }

        val post = user.posts.firstOrNull{ it.id == addCommentInput.postId}
            ?: throw RuntimeException("Post does not exist with id: ${addCommentInput.postId}")

        val comment = CommentEntity(
            text = addCommentInput.text,
            author = user,
            post = post
        )

        val commentCreated = commentRepository.save(comment)

        return Comment(
            id = commentCreated.id!!,
            text = commentCreated.text
        )
    }

    fun getCommentsByPosts(posts: List<Post>): Map<Post, List<Comment>> {
        /*return commentRepository.findAllByPostIds(postIds).map {
            Comment(
                id = it.id!!,
                text = it.text
            )
        }*/

        val comments = commentRepository.findAllByPostIds(posts.mapNotNull {
            it.id
        }.toList())

        return posts.associateWith { post ->
            comments.filter { comment -> comment.post.id == post.id }
                .map { comment -> Comment(id = comment.id!!, text = comment.text) }
                .toList()
        }

        /*return posts.map {
                post -> Pair(
            post,
            comments.filter { comment-> comment.post.id == post.id }
                .map { comment -> Comment(id= comment.id!!, text = comment.text)}
                .toList()
        )
        }.toMap()*/
    }

}
