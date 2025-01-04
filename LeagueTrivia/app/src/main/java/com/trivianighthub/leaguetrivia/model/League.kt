package com.trivianighthub.leaguetrivia.model

data class League(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val adminId: String = "",
    val teamCount: Int = 0,
    val isOpenLeague: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        const val MIN_NAME_LENGTH = 3
        const val MAX_NAME_LENGTH = 50
        const val MAX_DESCRIPTION_LENGTH = 500
        const val MAX_TEAMS = 32
    }
} 