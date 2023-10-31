package com.tr.ksbg.service

import com.tr.ksbg.model.dto.Comment
import com.tr.ksbg.repository.CommentRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CommentService(private val commentRepository: CommentRepository) {

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
}
