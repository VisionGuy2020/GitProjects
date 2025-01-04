package com.trivianighthub.leaguetrivia.util

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val errors: List<String>) : ValidationResult()
}

val ValidationResult.errorMessages: List<String>
    get() = when (this) {
        is ValidationResult.Error -> errors
        ValidationResult.Success -> emptyList()
    } 