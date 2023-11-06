package com.tr.ksbg.graphql.resolver

import com.tr.ksbg.model.dto.Comment
import com.tr.ksbg.model.dto.Post
import com.tr.ksbg.model.dto.User
import com.tr.ksbg.model.input.AddUserInput
import com.tr.ksbg.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class UserResolver(
    private val userService: UserService
) {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }
    @QueryMapping
    fun getUsers(@Argument page: Int, @Argument size: Int): List<User> {
        return userService.getUsers(page, size)
    }

    @MutationMapping
    fun addUser(@Argument("addUserInput") userInput: AddUserInput): UUID {
        return userService.addUser(userInput)
    }

    //field Resolver
    @SchemaMapping(typeName = "Post")
    fun author(post: Post): User {
        LOGGER.info("Fetching author data fro postId: ${post.id}")
        val postId = post.id ?: throw RuntimeException("post id can not be null")
        return userService.findByPostId(postId)
    }

    @SchemaMapping(typeName = "Comment")
    fun author(comment: Comment): User {
        return userService.findByCommentId(comment.id)
    }
}