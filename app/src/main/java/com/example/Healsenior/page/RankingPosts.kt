package com.example.Healsenior.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Ranking(
    val rank: Int,
    val name: String,
    val details: String
)

val myRanking = Ranking(63, "터미네이터02", "43세, 176cm, 79kg\n90세트, 5시간, 1512kcal")

val rankings = listOf(
    Ranking(1, "흰콩", "2,462세트, 210시간, 30,216kcal"),
    Ranking(2, "물먹는하마", "1,627세트, 124시간, 28,582kcal"),
    Ranking(3, "씨범", "1,281세트, 73시간, 19,362kcal"),
    Ranking(4, "정맨", "904세트, 55시간, 14,721kcal")
)

@Composable
fun RankingPosts() {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            MyRankingCard(myRanking)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "전체 랭킹 순위",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(rankings) { ranking ->
            RankingCard(ranking)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MyRankingCard(ranking: Ranking) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0F7FA))
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("${ranking.name}, ${ranking.rank}위", style = MaterialTheme.typography.titleMedium)
                Text(ranking.details, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Details",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun RankingCard(ranking: Ranking) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("${ranking.name}, ${ranking.rank}위", style = MaterialTheme.typography.titleMedium)
                Text(ranking.details, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRankingSection() {
    RankingPosts()
}