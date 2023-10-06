package com.tr.ksbg.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import java.util.UUID

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