package com.tr.ksbg.graphql.resolver

import com.tr.ksbg.model.dto.Post
import com.tr.ksbg.model.dto.User
import com.tr.ksbg.service.PostService
import com.tr.ksbg.model.input.AddPostInput
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.lang.RuntimeException

@Controller
class PostResolver(
    private val postService: PostService
) {

    @QueryMapping
    fun getPosts(): List<Post> {
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
}