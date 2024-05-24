data class LeaguesResponse (
    val countries: List<League>
)
data class League(
    val strLeague: String,
    val strDescriptionEN: String,
)
