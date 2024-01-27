package com.example.patternofkotlinspringbootvalidation.presentation.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid

/**
 * GenericErrorModel
 *
 * エラーレスポンスを返す時に利用するモデル
 *
 * @property errors
 */
data class GenericErrorModel(
    @field:Valid
    @field:JsonProperty("errors", required = true) val errors: GenericErrorModelErrors
)
