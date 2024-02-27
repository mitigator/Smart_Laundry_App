package com.example.smartlaundryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlaundryapp.ui.theme.SmartLaundryAppTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartLaundryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    color = MaterialTheme.colorScheme.background

                ) {
                    LinearGradient()
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            LogoImage()
                            Row {
                                LoginButton()
                                SignUpButton()
                            }
                        }
                        Banner()
                        Cards()

                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun LogoImage() {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .wrapContentWidth()
                .size(50.dp)
                .clip(shape = MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun SignUpButton() {
    Button(
        onClick = { /* Handle sign-in button click */ },
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Text("Sign In")
    }
}

@Composable
fun LoginButton() {
    Button(
        onClick = { /* Handle login button click */ },
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Text("Login")
    }
}

@Composable
fun LinearGradient(){
    val gradient = Brush.linearGradient(
        0f to Color.White,
        500f to Color.Cyan,
        start= Offset.Zero,
        end= Offset.Infinite
    )
    Box(modifier = Modifier.background(gradient))
}

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(modifier: Modifier = Modifier) {

    val images = listOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3,
        R.drawable.banner4
    )
    val pagerState = rememberPagerState(
        pageCount =
        { images.size }
    )
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = modifier.wrapContentSize()) {
            HorizontalPager(
                state = pagerState,
                modifier
                    .wrapContentSize()

            ) { currentPage ->

                Card(
                    modifier
                        .wrapContentSize()
                        .padding(26.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = images[currentPage]),
                        contentDescription = ""
                    )
                }
            }

            
        }

        PageIndicator(
            pageCount = images.size,
            currentPage = pagerState.currentPage,
            modifier = modifier
        )

    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pageCount){
            IndicatorDots(isSelected = it == currentPage, modifier= modifier)
        }
    }
}

@Composable
fun IndicatorDots(isSelected: Boolean, modifier: Modifier) {
    val size = animateDpAsState(targetValue = if (isSelected) 12.dp else 10.dp, label = "")
    Box(modifier = modifier
        .padding(2.dp)
        .size(size.value)
        .clip(CircleShape)
        .background(if (isSelected) Color(0xff373737) else Color(0xA8373737))
    )
}
@Preview
@Composable
fun Cards(){
    Box(modifier = Modifier.padding(2.dp)){
        Column {
            Row(
                modifier= Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Image(
                    painter = painterResource(id = R.drawable.card1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)

                    )
                Image(
                    painter = painterResource(id = R.drawable.card2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                )

            }
            Row(
                modifier= Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Image(
                    painter = painterResource(id = R.drawable.card3),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp))
                Image(
                    painter = painterResource(id = R.drawable.card4),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                )

            }
        }
    }
}
