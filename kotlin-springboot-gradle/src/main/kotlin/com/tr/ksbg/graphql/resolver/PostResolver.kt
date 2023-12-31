package com.tr.ksbg.graphql.resolver

import com.tr.ksbg.model.dto.Comment
import com.tr.ksbg.model.dto.Post
import com.tr.ksbg.model.dto.User
import com.tr.ksbg.service.PostService
import com.tr.ksbg.model.input.AddPostInput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.lang.RuntimeException
import java.util.*

@Controller
class PostResolver(
    private val postService: PostService
) {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @QueryMapping
    fun getPosts(): List<Post> {
        LOGGER.info("Fetching post from database")
        return postService.getPosts()
    }

    @SchemaMapping(typeName = "User")
    fun posts(user: User): List<Post> {
        val userId = user.id ?: throw RuntimeException("user id can not be null")
        return postService.getPotsByAuthor(userId)
    }

    @QueryMapping
    fun recentPosts(@Argument page: Int, @Argument size: Int): List<Post> {
        return postService.getPosts(page, size)
    }

    @MutationMapping
    fun addPost(@Argument("addPostInput") addPostInput: AddPostInput): Post {
        return postService.addPost(addPostInput)
    }

    @SchemaMapping(typeName = "User")
    fun totalPost(user: User): Int {
        val userId = user.id ?: throw RuntimeException("UserId can not be null")
        return postService.getPotsByAuthor(user.id).size
    }
    @SchemaMapping(typeName = "Comment")
    fun post(comment: Comment): Post {
        return postService.getPostByCommentId(comment.id)
    }
}