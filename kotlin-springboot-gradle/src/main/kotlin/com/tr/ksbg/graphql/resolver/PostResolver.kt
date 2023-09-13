package com.tr.ksbg.graphql.resolver

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class PostResolver {

    @QueryMapping
    fun getPosts(): List<Post> {
        return listOf(
            Post(
                UUID.randomUUID(),
                "test title,",
                "test description",

                ),
            Post(
                UUID.randomUUID(),
                "test title,",
                "test description",

                ),
            Post(
                UUID.randomUUID(),
                "test title,",
                "test description",

                )
        )
    }

    //field Resolver
    @SchemaMapping(typeName = "Post")
    fun author(post: Post): User {
        return User(
            UUID.randomUUID(),
            "title = ${post.title} id = ${post.id}"
        )
    }

    @SchemaMapping(typeName = "User")
    fun posts(user: User): List<Post> {
        return listOf(
            Post(
                UUID.randomUUID(),
                "test title,",
                "test description",
            )
        )
    }
}

data class Post(
    val id: UUID,
    val title: String,
    val description: String
)

data class User(
    val id: UUID,
    val name: String
)