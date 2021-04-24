package com.example.famousquotes.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CitataWithAuthor(
        @Embedded val citata: Citata,
        @Relation(
                parentColumn  = "author_id",
                entityColumn  = "id"
        )
        val author: Author
)
