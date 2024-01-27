package com.example.patternofkotlinspringbootvalidation.presentation.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

/**
 * 新規記事作成の null 非許容型のリクエストモデル
 *
 * @property newNonNullArticle
 */
data class NewNonNullArticleRequest(
    @field:Valid
    @field:NotNull
    @field:JsonProperty("article", required = true) val newNonNullArticle: NewNonNullArticle
)
