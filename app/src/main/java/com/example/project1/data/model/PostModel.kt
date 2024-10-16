package com.example.project1.data.model

import androidx.compose.ui.graphics.painter.Painter

data class PostModel(
    val id: Int,
    val title: String,
    val body: String,
    val image: Painter

)
