package com.example.patternofkotlinspringbootvalidation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * PatternOfKotlinSpringBootValidationApplication
 *
 * アプリケーションのエントリーポイント
 *
 */
@SpringBootApplication
class PatternOfKotlinSpringBootValidationApplication

/**
 * main
 *
 * アプリケーションを起動する際の main 関数
 *
 * @param args
 */
fun main(args: Array<String>) {
    @Suppress("SpreadOperator")
    runApplication<PatternOfKotlinSpringBootValidationApplication>(*args)
}
