package com.exalt.profile.ui.compose

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.exalt.profile.R

@Composable
fun ProfilePage() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile Page") },
                navigationIcon = {
                    IconButton(onClick = { Log.d("pouet", "COUCOU") }) {
                        Image(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },

            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    val (card, image, name) = createRefs()
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .constrainAs(card) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 15.dp,
                            bottomEnd = 15.dp
                        ),
                        backgroundColor = Color.LightGray
                    ) {
                    }
                    AsyncImage(
                        modifier = Modifier
                            .width(150.dp)
                            .height(150.dp)
                            .clip(CircleShape)
                            .border(
                                border = BorderStroke(width = 1.dp, color = Color.DarkGray),
                                shape = CircleShape
                            )
                            .background(color = Color.Blue)
                            .constrainAs(image) {
                                top.linkTo(card.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(card.start)
                                end.linkTo(card.end)
                            },
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://randomuser.me/api/portraits/med/women/5.jpg")
                            .build(),
                        placeholder = painterResource(id = R.drawable.ic_android_black_24dp),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier
                            .constrainAs(name) {
                                start.linkTo(image.start)
                                end.linkTo(image.end)
                                top.linkTo(image.bottom)
                            }
                            .padding(top = 15.dp),
                    ) {
                        Row {
                            Text(
                                text = "Kayla Bredesen",

                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(painterResource(id = R.drawable.baseline_male_24) , contentDescription = "")
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = "25/08/2022")
                    }
                    

                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.padding(start = 10.dp, bottom = 6.dp),
                    text = "Contact Information".uppercase())

                Spacer(modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .height(1.dp))

                Text(
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                    text = "Email"
                )

                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Bloup@gmail.com"
                )

                Spacer(modifier = Modifier
                    .height(20.dp))

                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Phone"
                )

                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "1234567890"
                )

                Spacer(modifier = Modifier
                    .height(20.dp))

                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Email"
                )

                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "163 avenue aristide Briand"
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "94230 Cachan"
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "France"
                )

            }
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun Preview() {
    ProfilePage()
}