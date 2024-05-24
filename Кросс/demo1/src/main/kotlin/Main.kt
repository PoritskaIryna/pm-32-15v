import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun FootballApp() {
    var selectedOption by remember { mutableStateOf("Clubs") }
    var clubs by remember { mutableStateOf<List<Team>?>(null) }
    var leagues by remember { mutableStateOf<List<League>?>(null) }
    val options = listOf("Clubs", "Leagues")

    LaunchedEffect(Unit) {
        Fetcher.fetchClubs().let { clubs = it.teams }
        Fetcher.fetchLeagues().let {leagues = it.countries }
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        var expanded by remember { mutableStateOf(false) }

        Row {
            Button(onClick = { expanded = !expanded }) {
                Text("Selected Option: $selectedOption")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(onClick = {
                        selectedOption = option
                        expanded = false
                    }) {
                        Text(option)
                    }
                }
            }
        }

        if (selectedOption == "Clubs") {
            Row {
                Text("Team Name")
                Spacer(modifier = Modifier.width(16.dp))
                Text("Description")
            }
            clubs?.forEach { team ->
                Row {
                    Text("${team.strTeam} --")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(" ${team.strDescriptionEN.substring(0, 60)}")
                }
            }
        } else if (selectedOption == "Leagues") {
            Row {
                Text("League Name")
                Spacer(modifier = Modifier.width(16.dp))
                Text("Description")
            }
            leagues?.forEach { league ->
                Row {
                    Text("${league.strLeague} --")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(" ${league.strDescriptionEN.substring(0, 60)}")
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Football App") {
        FootballApp()
    }
}