package com.example.patternofkotlinspringbootvalidation.presentation.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

/**
 * 新規記事作成のリクエスト null 許容型でセッターが null 非許容型のリクエストモデル
 *
 * @property article
 */
data class NewNullableSetNotNullArticleRequest(
    @field:Valid
    @field:NotNull
    @field:JsonProperty("article", required = true) private val article:
    NewNullableSetNotNullArticle? = null,
) {
    /**
     * null 非許容型のリクエストモデル
     */
    val validatedNotNullArticle: NewNullableSetNotNullArticle
        get() = article!!
}
