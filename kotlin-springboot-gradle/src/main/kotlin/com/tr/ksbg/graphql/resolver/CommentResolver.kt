package com.tr.ksbg.graphql.resolver

import com.tr.ksbg.model.dto.Comment
import com.tr.ksbg.model.dto.Post
import com.tr.ksbg.model.dto.User
import com.tr.ksbg.service.CommentService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class CommentResolver(private val commentService: CommentService) {

    @QueryMapping
    fun getComments(@Argument("page") page: Int, @Argument size: Int): List<Comment> {
        return commentService.getComments(page, size)
        /*return listOf(
            Comment(
                id = UUID.randomUUID(),
                text = "comment test"
            )
        )*/
    }

    @SchemaMapping(typeName = "Post")
    fun comments(post: Post): List<Comment> {
        return commentService.getCommentsByPostId(post.id)
    }
    @SchemaMapping(typeName = "User")
    fun comments(user: User): List<Comment> {
        return commentService.getCommentsByUserId(user.id)
    }
}