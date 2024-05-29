package com.example.Healsenior.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.Healsenior.R


data class Product(
    val name: String,
    val price: String,
    val points: Int,
    val imageRes: Int,
)

val products = listOf(
    Product("CU 교환권 5천원권", "5,000 P", 5000, R.drawable.avatar_0),
    Product("네이버페이 포인트 5천원권", "5,000 P", 5000, R.drawable.avatar_1),
    Product("파리바게트 교환권 5천원권", "5,000 P", 5000, R.drawable.avatar_2),
    Product("gs25 모바일 상품권 5천원권", "5,000 P", 5000, R.drawable.avatar_3),
    Product("메가커피, 아이스 아메리카노", "2,000 P", 2000, R.drawable.avatar_4),
    Product("이디아, 아이스 아메리카노", "4,200 P", 4200, R.drawable.avatar_5),
    Product("스타벅스, 카페 아메리카노 Tall", "4,200 P", 4200, R.drawable.avatar_6)
)

@Composable
fun ProductListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF87CEEB))
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    modifier = Modifier.padding(start = 7.dp),
                    onClick = { navController.popBackStack() }) {
                    Text("<", fontSize = 30.sp, color = Color.Black)
                }
                Spacer(modifier = Modifier.width(85.dp))
                Text(
                    text = "상품 리스트",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
        }

        // Product List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            items(products) { product ->
                ProductItem(product)
                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = product.name, fontSize = 18.sp, color = Color.Black)
                Text(text = product.price, fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductListScreen() {
    val navController = rememberNavController()
    ProductListScreen(navController)
}
