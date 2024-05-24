data class ClubsResponse(
    val teams: List<Team>
)

data class Team(
    val idTeam: String,
    val strTeam: String,
    val strDescriptionEN: String,
)
