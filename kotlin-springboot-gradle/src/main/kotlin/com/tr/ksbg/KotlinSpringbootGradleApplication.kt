package com.tr.ksbg

import com.tr.ksbg.model.entity.CommentEntity
import com.tr.ksbg.model.entity.PostEntity
import com.tr.ksbg.model.entity.UserEntity
import com.tr.ksbg.repository.CommentRepository
import com.tr.ksbg.repository.PostRepository
import com.tr.ksbg.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinSpringbootGradleApplication {
    @Bean
    fun runner(
        userRepository: UserRepository,
        postRepository: PostRepository,
        commentRepository: CommentRepository
    ): ApplicationRunner {
        return ApplicationRunner {
           /* val user = UserEntity(
                name = "Test User"
            )
            userRepository.save(user)

            val postEntity = PostEntity(
                title = "Test Title",
                description = "Test Description",
                author = user
            )
            postRepository.save(postEntity)

            val comment = CommentEntity(
                text = "testing comment",
                author = user,
                post = postEntity
            )

            commentRepository.save(comment)*/

        }
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinSpringbootGradleApplication>(*args)
}
