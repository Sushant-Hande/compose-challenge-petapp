/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.purple500
import com.example.androiddevchallenge.ui.theme.purple700

/**
 * Created by shande on 27,February,2021
 */
class PetDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                intent.getParcelableExtra<Pet>(PET_DETAILS)?.let { MyApp(pet = it) }
            }
        }
    }

    companion object {
        const val PET_DETAILS = "petDetails"
    }

    @Composable
    fun MyApp(pet: Pet) {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = pet.name,
                                fontFamily = FontFamily(Font(resId = R.font.montserrat_bold))
                            )
                        },
                        backgroundColor = Color.White,
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                modifier = Modifier.clickable(onClick = { finish() }),
                                contentDescription = ""
                            )
                        }
                    )
                }
            ) {

                Column(modifier = Modifier.padding(10.dp)) {

                    Image(
                        modifier = Modifier
                            .padding(start = 50.dp)
                            .width(200.dp)
                            .height(200.dp),
                        painter = painterResource(id = pet.image),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.CenterStart
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        RoundCard(
                            title = stringResource(id = R.string.age),
                            text = "${pet.age} years"
                        )
                        RoundCard(
                            title = stringResource(id = R.string.weight),
                            text = "${pet.weight} kg"
                        )
                        RoundCard(
                            title = stringResource(id = R.string.location),
                            text = pet.location
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 10.dp)) {

                        Image(
                            modifier = Modifier
                                .padding(10.dp)
                                .width(60.dp)
                                .height(60.dp)
                                .border(
                                    width = 1.dp,
                                    brush = Brush.horizontalGradient(
                                        listOf(purple500, purple700)
                                    ),
                                    shape = CircleShape
                                ),
                            painter = painterResource(id = R.drawable.owner),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                        )

                        Column {
                            Text(
                                text = "Sushant Hande",
                                fontFamily = FontFamily(Font(resId = R.font.montserrat_bold))
                            )
                            Text(
                                text = stringResource(id = R.string.owner),
                                modifier = Modifier.padding(vertical = 5.dp),
                                fontFamily = FontFamily(Font(resId = R.font.montserrat_regular))
                            )
                        }
                    }

                    Text(
                        text = stringResource(id = R.string.reason),
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily(Font(resId = R.font.montserrat_regular))
                    )

                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(10.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.adoption),
                            fontFamily = FontFamily(Font(resId = R.font.montserrat_bold))
                        )
                    }
                }
            }
        }

    }

    @Composable
    fun RoundCard(title: String, text: String) {
        Card(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .size(100.dp, 64.dp)
                .clip(MaterialTheme.shapes.medium),
            elevation = 8.dp,
            border = BorderStroke(1.dp, MaterialTheme.colors.primary)
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyTheme {
            MyApp(
                pet = Pet(
                    id = 6,
                    name = "Frenchton",
                    age = 6,
                    weight=10,
                    location = "Nashik",
                    image = R.drawable.frenchton
                )
            )
        }
    }
}
