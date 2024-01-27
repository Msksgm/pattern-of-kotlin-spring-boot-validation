package com.example.patternofkotlinspringbootvalidation.presentation.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * 新規記事の null 非許容型のモデル
 *
 * @property title タイトル
 * @property description 説明
 * @property body 本文
 */
data class NewNonNullArticle(
    @field:Size(max = 32)
    @field:NotBlank
    @field:JsonProperty("title", required = true) val title: String,

    @field:Size(max = 1024)
    @field:NotBlank
    @field:JsonProperty("description", required = true) val description: String,

    @field:Size(max = 2048)
    @field:NotBlank
    @field:JsonProperty("body", required = true) val body: String,
)
