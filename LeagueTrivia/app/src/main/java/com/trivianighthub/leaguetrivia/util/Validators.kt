package com.trivianighthub.leaguetrivia.util

import com.trivianighthub.leaguetrivia.model.League
import com.trivianighthub.leaguetrivia.model.Team

object Validators {
    fun validateLeagueName(name: String): ValidationResult {
        val errors = mutableListOf<String>()

        if (name.isBlank()) {
            errors.add("League name cannot be empty")
        } else {
            if (name.length < League.MIN_NAME_LENGTH) {
                errors.add("League name must be at least ${League.MIN_NAME_LENGTH} characters")
            }
            if (name.length > League.MAX_NAME_LENGTH) {
                errors.add("League name cannot exceed ${League.MAX_NAME_LENGTH} characters")
            }
        }

        return if (errors.isEmpty()) ValidationResult.Success else ValidationResult.Error(errors)
    }

    fun validateLeagueDescription(description: String): ValidationResult {
        val errors = mutableListOf<String>()

        if (description.length > League.MAX_DESCRIPTION_LENGTH) {
            errors.add("League description cannot exceed ${League.MAX_DESCRIPTION_LENGTH} characters")
        }

        return if (errors.isEmpty()) ValidationResult.Success else ValidationResult.Error(errors)
    }

    fun validateTeamName(name: String): ValidationResult {
        val errors = mutableListOf<String>()

        if (name.isBlank()) {
            errors.add("Team name cannot be empty")
        } else {
            if (name.length < Team.MIN_NAME_LENGTH) {
                errors.add("Team name must be at least ${Team.MIN_NAME_LENGTH} characters")
            }
            if (name.length > Team.MAX_NAME_LENGTH) {
                errors.add("Team name cannot exceed ${Team.MAX_NAME_LENGTH} characters")
            }
        }

        return if (errors.isEmpty()) ValidationResult.Success else ValidationResult.Error(errors)
    }
} 