package com.tr.ksbg.graphql.resolver

import com.tr.ksbg.model.dto.Comment
import com.tr.ksbg.model.dto.Post
import com.tr.ksbg.model.dto.User
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class CommentResolver {

    @QueryMapping
    fun getComments(@Argument("page") page: Int, @Argument size: Int): List<Comment> {
        return listOf(
            Comment(
                id = UUID.randomUUID(),
                text = "comment test"
            )
        )
    }
}