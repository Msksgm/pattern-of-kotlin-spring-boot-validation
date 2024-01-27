package com.example.patternofkotlinspringbootvalidation.presentation.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * 新規記事の null 許容型のモデル
 *
 * @property title タイトル
 * @property description 説明
 * @property body 本文
 */
data class NewNullableSetNotNullArticle(
    @field:Size(max = 32)
    @field:NotBlank
    @field:JsonProperty("title", required = true) private val title: String? = null,

    @field:Size(max = 1024)
    @field:NotBlank
    @field:JsonProperty("description", required = true) private val description: String? = null,

    @field:Size(max = 2048)
    @field:NotBlank
    @field:JsonProperty("body", required = true) private val body: String? = null,
) {
    /**
     * null 非許容型のタイトル
     */
    val validatedTitle: String
        get() = title!!

    /**
     * null 非許容型の説明
     */
    val validatedDescription: String
        get() = description!!

    /**
     * null 非許容型の本文
     */
    val validatedBody: String
        get() = body!!
}
