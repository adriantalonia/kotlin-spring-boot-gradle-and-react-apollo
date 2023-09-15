package com.tr.ksbg.graphql.resolver

import com.tr.ksbg.service.PostService
import com.tr.ksbg.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.lang.RuntimeException
import java.util.UUID

@Controller
class PostResolver(
    private val userService: UserService,
    private val postService: PostService
) {

    @QueryMapping
    fun getPosts(): List<Post> {
        return postService.getPosts()
    }

    //field Resolver
    @SchemaMapping(typeName = "Post")
    fun author(post: Post): User {
        val postId = post.id ?: throw RuntimeException("post id can not be null")
        return userService.findByPostId(postId)
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
}

data class Post(
    val id: UUID?,
    val title: String,
    val description: String?
)

data class User(
    val id: UUID?,
    val name: String
)