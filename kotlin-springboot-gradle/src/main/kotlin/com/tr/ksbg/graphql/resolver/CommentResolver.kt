package com.tr.ksbg.graphql.resolver

import com.tr.ksbg.model.dto.Comment
import com.tr.ksbg.model.dto.Post
import com.tr.ksbg.model.dto.User
import com.tr.ksbg.model.entity.CommentEntity
import com.tr.ksbg.model.input.AddCommentInput
import com.tr.ksbg.service.CommentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.BatchMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class CommentResolver(private val commentService: CommentService) {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }

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

    @BatchMapping
    fun comments(posts: List<Post>): Map<Post, List<Comment>> {
        LOGGER.info("Fetching comments for postIds: (${posts.map{it.id}})")

        return commentService.getCommentsByPosts(posts)

        /*val comments = commentService.getCommentsByPosts(posts.mapNotNull { it.id}.toList())
        return posts.associateWith {
            listOf(
                Comment(
                    id = UUID.randomUUID(),
                    text = "text"
                )
            )
        }*/

        /*return posts.map {
            Pair(
                it,
                listOf(
                    Comment(
                        id = UUID.randomUUID(),
                        text = "text"
                    )
                )
            )
        }.toMap()*/
    }

    /*@SchemaMapping(typeName = "Post")
    fun comments(post: Post): List<Comment> {
        LOGGER.info("Fetching comments from postId: ${post.id}")
        return commentService.getCommentsByPostId(post.id)
    }*/
    @SchemaMapping(typeName = "User")
    fun comments(user: User): List<Comment> {
        return commentService.getCommentsByUserId(user.id)
    }

    @MutationMapping
    fun addComment(@Argument("addCommentInput") addCommentInput: AddCommentInput): Comment {
        return commentService.addComment(addCommentInput)
    }
}