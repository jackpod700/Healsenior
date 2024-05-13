package com.example.Healsenior.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Ranking(
    val rank: Int,
    val name: String,
    val details: String
)

val rankings = listOf(
    Ranking(1, "타미니타02, 63 위", "43세, 176cm, 79kg\n90일 동안, 5시간, 1512kcal"),
    Ranking(2, "철권, 1위", "26세, 180cm, 72kg\n30일 동안, 2시간, 216kcal"),
    Ranking(3, "몸만들어야, 2위", "31세, 172cm, 85kg\n124일 동안, 2.5시간, 28582kcal"),
    Ranking(4, "새벽, 3위", "37세, 165cm, 63kg\n73일 동안, 1.5시간, 19362kcal"),
    Ranking(5, "정면, 4위", "55세, 170cm, 70kg\n55일 동안, 1시간, 14721kcal")
)

@Composable
fun RankingPosts() {
    Column(modifier = Modifier.padding(16.dp)) {
        rankings.forEach { ranking ->
            RankingCard(ranking)
            Spacer(modifier = Modifier.height(8.dp))
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
        Column(modifier = Modifier.padding(16.dp)) {
            Text("${ranking.rank}위 ${ranking.name}", style = MaterialTheme.typography.titleMedium)
            Text(ranking.details, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRankingSection() {
    RankingPosts()
}