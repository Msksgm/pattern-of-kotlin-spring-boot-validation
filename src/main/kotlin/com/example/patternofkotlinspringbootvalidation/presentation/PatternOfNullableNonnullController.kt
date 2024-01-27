package com.example.patternofkotlinspringbootvalidation.presentation

import com.example.patternofkotlinspringbootvalidation.presentation.model.Article
import com.example.patternofkotlinspringbootvalidation.presentation.model.NewNonNullArticleRequest
import com.example.patternofkotlinspringbootvalidation.presentation.model.SingleArticleResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * null バリデーションのパターンごとにエンドポイントを作成するサンプルコントローラ
 *
 */
@RestController
@Validated
class PatternOfNullableNonnullController {
    /**
     * パターン 1 : null 非許容型のモデルを受け取るエンドポイント
     *
     * メリット
     * - null 許容型から null 非許容型への変換が不要なのでミスやコードの記述量が減る
     *
     * デメリット
     * - null やプロパティが欠損していたときのエラーメッセージが一律でプロパティごとに変更できないためわかりづらい
     * - OpenAPI Generator の利用やクライアント側の徹底などで、null 非許容型を送信しない対策が必要
     *
     */
    @PostMapping(
        value = ["/non-null-article-model"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun nonNullModel(
        @Valid @RequestBody newNonNullArticleRequest: NewNonNullArticleRequest
    ): ResponseEntity<SingleArticleResponse> {
        return ResponseEntity(
            SingleArticleResponse(
                article = Article(
                    slug = "non-null-article-slug",
                    title = newNonNullArticleRequest.newNonNullArticle.title,
                    body = newNonNullArticleRequest.newNonNullArticle.body,
                    description = newNonNullArticleRequest.newNonNullArticle.description
                )
            ),
            HttpStatus.OK
        )
    }
}
