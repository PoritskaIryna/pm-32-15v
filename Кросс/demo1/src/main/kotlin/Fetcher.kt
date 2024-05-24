import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

object Fetcher {
    private val gson = Gson()

    fun fetchClubs(): ClubsResponse {
        val url = URL("https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?s=Soccer&c=Spain")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val responseCode = connection.responseCode

        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = BufferedReader(InputStreamReader(connection.inputStream, StandardCharsets.UTF_8))
            val response = inputStream.use { it.readText() }
            println("Raw JSON response: $response")

            return gson.fromJson(response, ClubsResponse::class.java)
        } else {
            throw RuntimeException("Failed to fetch weather data: HTTP response code $responseCode")
        }
    }

    fun fetchLeagues(): LeaguesResponse {
        val url = URL("https://www.thesportsdb.com/api/v1/json/3/search_all_leagues.php?c=Spain&s=Soccer")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val responseCode = connection.responseCode

        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = BufferedReader(InputStreamReader(connection.inputStream, StandardCharsets.UTF_8))
            val response = inputStream.use { it.readText() }
            println("Raw JSON response: $response")

            return gson.fromJson(response, LeaguesResponse::class.java)
        } else {
            throw RuntimeException("Failed to fetch weather data: HTTP response code $responseCode")
        }
    }
}
