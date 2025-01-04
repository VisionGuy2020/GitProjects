package com.trivianighthub.leaguetrivia.model

data class Team(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val leagueId: String = "",
    val captainId: String = "",
    val memberCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        const val MIN_NAME_LENGTH = 3
        const val MAX_NAME_LENGTH = 30
        const val MAX_DESCRIPTION_LENGTH = 200
        const val MIN_MEMBERS = 1
        const val MAX_MEMBERS = 10
    }
} 