package com.trivianighthub.leaguetrivia.util

import com.trivianighthub.leaguetrivia.model.League
import com.trivianighthub.leaguetrivia.model.Team

fun League.validateWithDetails(): ValidationResult {
    val errors = mutableListOf<String>()

    if (name.length < League.MIN_NAME_LENGTH) {
        errors.add("League name must be at least ${League.MIN_NAME_LENGTH} characters")
    }
    if (name.length > League.MAX_NAME_LENGTH) {
        errors.add("League name cannot exceed ${League.MAX_NAME_LENGTH} characters")
    }
    if (description.length > League.MAX_DESCRIPTION_LENGTH) {
        errors.add("League description cannot exceed ${League.MAX_DESCRIPTION_LENGTH} characters")
    }

    return if (errors.isEmpty()) ValidationResult.Success else ValidationResult.Error(errors)
}

fun Team.validateWithDetails(): ValidationResult {
    val errors = mutableListOf<String>()

    if (name.length < Team.MIN_NAME_LENGTH) {
        errors.add("Team name must be at least ${Team.MIN_NAME_LENGTH} characters")
    }
    if (name.length > Team.MAX_NAME_LENGTH) {
        errors.add("Team name cannot exceed ${Team.MAX_NAME_LENGTH} characters")
    }
    if (description.length > Team.MAX_DESCRIPTION_LENGTH) {
        errors.add("Team description cannot exceed ${Team.MAX_DESCRIPTION_LENGTH} characters")
    }

    return if (errors.isEmpty()) ValidationResult.Success else ValidationResult.Error(errors)
} 