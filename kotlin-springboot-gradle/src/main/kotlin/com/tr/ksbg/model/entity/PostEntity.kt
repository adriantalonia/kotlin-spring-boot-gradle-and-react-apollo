package com.tr.ksbg.model.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "posts", schema = "public")
class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    val id: UUID? = null,
    @Column
    val title: String,
    val description: String? = null,
    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: UserEntity
)