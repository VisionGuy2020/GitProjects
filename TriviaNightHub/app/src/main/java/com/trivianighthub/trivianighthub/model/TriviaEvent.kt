data class TriviaEvent(
    val id: String,
    val name: String,
    val venue: String,
    val address: String,
    val dateTime: Long,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val prizePool: String? = null,
    val maxTeamSize: Int? = null
) 