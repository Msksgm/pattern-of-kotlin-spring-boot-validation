package com.example.patternofkotlinspringbootvalidation.presentation

import com.example.patternofkotlinspringbootvalidation.presentation.model.Article
import com.example.patternofkotlinspringbootvalidation.presentation.model.NewNonNullArticleRequest
import com.example.patternofkotlinspringbootvalidation.presentation.model.NewNullableArticleRequest
import com.example.patternofkotlinspringbootvalidation.presentation.model.NewNullableSetNotNullArticleRequest
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

    /**
     * パターン 2 : null 許容型のモデルを受け取るエンドポイント
     *
     * メリット
     * - null だったときのバリデーションエラーのレスポンスをわかりやすくできる
     *
     * デメリット
     * - アノテーションの記述内容（`@NotNull`、`required = true`）とプロパティの型が一致しなくなる
     * - コントローラに null 非許容型の知識が漏れる
     * - not-null assertion operator（`!!`）をコントローラに書く必要があるので linter で `!!` を許容する設定が必要になる
     *
     */
    @PostMapping(
        value = ["/nullable-article-model"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    // not-null assertion operator（`!!`）をコントローラに書く必要があるので linter で `!!` を許容する設定が必要になる
    @Suppress("UnsafeCallOnNullableType")
    fun nullableModel(
        @Valid @RequestBody newNullableArticleRequest: NewNullableArticleRequest
    ): ResponseEntity<SingleArticleResponse> {
        // ユースケース層やアプリケーションサービス層に渡す前に、not-null assertion を実施する必要があり、アノテーションに対して null 非許容になっているのに冗長
        val nonNullValidatedArticle = newNullableArticleRequest.article!!
        val nonNullValidatedTitle = nonNullValidatedArticle.title!!
        val nonNullValidatedBody = nonNullValidatedArticle.body!!
        val nonNullValidatedDescription = nonNullValidatedArticle.description!!
        return ResponseEntity(
            SingleArticleResponse(
                article = Article(
                    slug = "non-null-article-slug",
                    title = nonNullValidatedTitle,
                    body = nonNullValidatedBody,
                    description = nonNullValidatedDescription
                )
            ),
            HttpStatus.OK
        )
    }

    /**
     * パターン 3 : null 許容型で取得したプロパティをセッターで null 非許容型にする
     *
     * メリット
     * - null だったときのバリデーションエラーのレスポンスをわかりやすくできる
     * - コントローラに null 非許容型の知識が漏れない
     * - `!!` は model 側で必要になるが、コントローラに記述するよりは許容しやすく、設定もわかりやすい
     *
     * デメリット
     * - 結局 `!!` が必要になる
     * - セッターで設定するプロパティ名についてルールを決める必要がある
     * - model 側で全体的な記述量が増える
     *
     */
    @PostMapping(
        value = ["/nullable-set-not-null-article-model"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun nullableSetNotNullModel(
        @Valid @RequestBody newNullableSetNotNullArticleRequest: NewNullableSetNotNullArticleRequest
    ): ResponseEntity<SingleArticleResponse> {
        return ResponseEntity(
            SingleArticleResponse(
                article = Article(
                    slug = "nullable-set-non-null-article-slug",
                    title = newNullableSetNotNullArticleRequest.validatedNotNullArticle.validatedTitle,
                    body = newNullableSetNotNullArticleRequest.validatedNotNullArticle.validatedBody,
                    description = newNullableSetNotNullArticleRequest.validatedNotNullArticle.validatedDescription,
                )
            ),
            HttpStatus.OK
        )
    }
}
