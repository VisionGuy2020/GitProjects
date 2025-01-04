package com.trivianighthub.leaguetrivia.util

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestoreException

object ErrorHandler {
    fun handleException(e: Exception): String {
        return when (e) {
            is FirebaseAuthException -> handleAuthError(e)
            is FirebaseFirestoreException -> handleFirestoreError(e)
            is FirebaseException -> "Firebase error: ${e.message}"
            else -> "An unexpected error occurred: ${e.message}"
        }
    }

    private fun handleAuthError(e: FirebaseAuthException): String {
        return when (e.errorCode) {
            "ERROR_INVALID_EMAIL" -> "Invalid email address"
            "ERROR_WRONG_PASSWORD" -> "Incorrect password"
            "ERROR_USER_NOT_FOUND" -> "Account not found"
            else -> "Authentication error: ${e.message}"
        }
    }

    private fun handleFirestoreError(e: FirebaseFirestoreException): String {
        return when (e.code) {
            FirebaseFirestoreException.Code.PERMISSION_DENIED -> "Permission denied"
            FirebaseFirestoreException.Code.NOT_FOUND -> "Document not found"
            else -> "Database error: ${e.message}"
        }
    }

    fun handleValidationResult(result: ValidationResult): String {
        return when (result) {
            is ValidationResult.Error -> result.errors.joinToString("\n")
            ValidationResult.Success -> ""
        }
    }
} 