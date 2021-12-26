package com.football.facts.ui.main.home.searchTeams

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.football.facts.R
import com.football.facts.ui.theme.H1
import com.football.facts.ui.theme.Purple200
import com.football.facts.ui.theme.White

@Composable
fun SearchTeamsScreen(state: SearchTeamsScreenState) {
    val model by state.searchTeamsDisplay.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .background(White)
    ) {

        LazyColumn(Modifier.fillMaxSize()) {
            item {
                TextField(
                    value = model.querySearch,
                    onValueChange = { value ->
                        state.onSearchQueryChanged(value)
                    },
                    label = { Text("Search...") },
                    maxLines = 1,
                    textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
            }
            items(
                items = model.teams,
                key = { it.id }) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            state.onTeamClicked(item)
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
fun SearchTeamsScreenPreview() {
    SearchTeamsScreen(SearchTeamsScreenState.preview())
}