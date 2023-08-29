package com.exalt.profile.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
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
import com.exalt.profile.ui.theme.Azure
import com.exalt.profile.ui.theme.GreyBackground
import com.exalt.profile.viewobjects.Location
import com.exalt.profile.viewobjects.User

@Composable
fun ProfilePage(
    user: User? = null,
    isLoading: Boolean,
    onBackCLick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = user?.let {
                        "${user.firstName} ${user.lastName}"
                    } ?: "Profile Page")
                },
                navigationIcon = {
                    IconButton(onClick = onBackCLick) {
                        Image(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                backgroundColor = Azure
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = GreyBackground),
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            } else {
                UserInformations(user = user)
            }
        }
    }
}

@Composable
private fun UserInformations(user: User?) {
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
                    .data(user?.picture)
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
                        text = "${user?.firstName} ${user?.lastName}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Icon(
                        painterResource(
                            id = if (user?.gender == "male") {
                                R.drawable.baseline_male_24
                            } else {
                                R.drawable.baseline_female_24
                            }
                        ), contentDescription = "",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = user?.dateOfBirth ?: "",
                    color = Color.White
                )
            }


        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = "Contact Information".uppercase(),
            color = Color.White
        )

        TextProfilePage(
            spacerModifier = Modifier
                .padding(bottom = 10.dp, top = 6.dp)
                .background(Color.LightGray)
                .fillMaxWidth()
                .height(1.dp),
            textModifier = Modifier.padding(start = 10.dp),
            titleText = "Email",
            bodyText = user?.email ?: ""
        )

        TextProfilePage(
            spacerModifier = Modifier
                .height(20.dp),
            textModifier = Modifier.padding(start = 10.dp),
            titleText = "Phone",
            bodyText = user?.phone ?: ""
        )

        AdressText(
            spacerModifier = Modifier
                .height(20.dp),
            textModifier = Modifier.padding(start = 10.dp),
            location = user?.location
        )

    }
}

@Composable
private fun TextProfilePage(
    spacerModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    titleText: String,
    bodyText: String
) {
    Spacer(
        modifier = spacerModifier
    )

    Text(
        modifier = textModifier,
        text = titleText,
        color = Color.White
    )

    Text(
        modifier = textModifier,
        text = bodyText,
        color = Color.White
    )
}

@Composable
private fun AdressText(
    spacerModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    location: Location?
) {
    TextProfilePage(
        spacerModifier = spacerModifier,
        textModifier = textModifier,
        titleText = "Adress",
        bodyText = location?.street ?: ""
    )

    Text(
        modifier = textModifier,
        text = "${location?.city} ${location?.state}",
        color = Color.White
    )
    Text(
        modifier = textModifier,
        text = location?.country ?: "",
        color = Color.White
    )
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun Preview() {
    ProfilePage(
        isLoading = false,
        user = User(
            dateOfBirth = "epicurei",
            email = "aurelio.carney@example.com",
            firstName = "Archie Kerr",
            gender = "posuere",
            lastName = "Joesph Gallagher",
            location = Location(
                city = "Cachan",
                country = "France",
                state = "Val de Marne",
                street = "163 avenue Aristide Briand"
            ),
            phone = "(602) 954-7969",
            picture = "https://randomuser.me/api/portraits/med/women/5.jpg"
        ),
        onBackCLick = {}
    )
}