package com.example.patternofkotlinspringbootvalidation.presentation.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * GenericErrorModelErrors
 *
 * エラーレスポンスの詳細を記述するモデル
 *
 * @property body エラーの詳細
 */
data class GenericErrorModelErrors(
    @field:JsonProperty("body", required = true) val body: List<String>
)
