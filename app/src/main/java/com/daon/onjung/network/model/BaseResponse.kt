package com.daon.onjung.network.model

/**
 * 반복되는 Response의 속성을 담은 클래스로 상속해서 사용
 *
 * @property isSuccess
 * @property code
 * @property message
 * @property result
 */
open class CommonResponse<out T>(
    val isSuccess: Boolean = false,
    val code: String = "",
    val message: String = "",
    val result: T
)