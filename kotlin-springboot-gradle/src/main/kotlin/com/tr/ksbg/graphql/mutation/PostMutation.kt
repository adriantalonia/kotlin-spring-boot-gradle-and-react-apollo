package com.tr.ksbg.graphql.mutation

import com.tr.ksbg.service.PostService
import com.tr.ksbg.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class PostMutation(
    private val userService: UserService,
    private val postService: PostService
) {

    @MutationMapping
    fun addUser(@Argument addUserInput: AddUserInput): UUID {
        return userService.addUser(addUserInput)
    }
}

data class AddUserInput(
    val name: String
)