package com.football.facts.ui.main.home.leagues

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.football.facts.R
import com.football.facts.ui.theme.H1
import com.football.facts.ui.theme.Purple200
import com.football.facts.ui.theme.White

@Composable
fun LeaguesScreen(state: LeaguesScreenState) {
    val model by state.leaguesDisplay.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .background(White)
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
                        style = H1,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = {
                        state.onFavoriteClicked(item)
                    }) {
                        Icon(
                            imageVector = if (item.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
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
                color = Purple200
            )
        }
    }

}


@Composable
@Preview
fun LeaguesScreenPreview() {
    LeaguesScreen(LeaguesScreenState.preview())
}