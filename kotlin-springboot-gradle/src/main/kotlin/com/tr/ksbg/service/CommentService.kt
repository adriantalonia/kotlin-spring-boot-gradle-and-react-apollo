package com.tr.ksbg.service

import com.tr.ksbg.model.dto.Comment
import com.tr.ksbg.repository.CommentRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

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
}
