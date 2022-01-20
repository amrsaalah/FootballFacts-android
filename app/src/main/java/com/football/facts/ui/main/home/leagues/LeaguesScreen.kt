package com.football.facts.ui.main.home.leagues

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.football.facts.R
import com.football.facts.ui.theme.FootballFactsTheme
import com.football.facts.ui.theme.appColors

@Composable
fun LeaguesScreen(state: LeaguesScreenState) {
    val model by state.leaguesDisplay.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        LazyColumn(Modifier.fillMaxSize()) {
            items(
                items = model.leagues,
                key = { it.id }) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            state.onLeagueClicked(item)
                        }, verticalAlignment = Alignment.Top) {
                    Image(
                        painter = rememberImagePainter(
                            data = item.icon,
                            builder = {
                                placeholder(R.drawable.ic_home)
                                error(R.drawable.ic_home)
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                    )

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.appColors.textPrimary,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = {
                        state.onFavoriteClicked(item)
                    }) {
                        Icon(
                            painter = if (item.isFavorite) painterResource(id = R.drawable.ic_favorite_filled_red) else painterResource(
                                id = R.drawable.ic_favorite_border_red
                            ),
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }
            }
        }

        if (model.isProgressVisible) {
            CircularProgressIndicator(
                Modifier.align(Alignment.Center),
                color = MaterialTheme.appColors.progressIndicatorColor
            )
        }
    }

}


@Composable
@Preview
fun LeaguesScreenLightPreview() {
    FootballFactsTheme(darkTheme = false) {
        LeaguesScreen(LeaguesScreenState.preview())
    }
}


@Composable
@Preview
fun LeaguesScreenDarkPreview() {
    FootballFactsTheme(darkTheme = true) {
        LeaguesScreen(LeaguesScreenState.preview())
    }
}