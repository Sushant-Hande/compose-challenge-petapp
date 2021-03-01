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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

/**
 * Created by shande on 27,February,2021
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    backgroundColor = Color.White,
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            modifier = Modifier.clickable(onClick = {}),
                            contentDescription = ""
                        )
                    }
                )
            }
        ) {

            Column {
                AddPetList(getPetList())
            }
        }
    }
}

@Composable
fun AddPetList(petList: List<Pet>) {
    LazyColumn {
        items(petList) { pet ->
            PetListItem(pet = pet, context = LocalContext.current)
        }
    }
}

@Composable
fun PetListItem(pet: Pet, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .clickable(
                onClick = {
                    Intent(context, PetDetailsActivity::class.java).apply {
                        putExtra(PetDetailsActivity.PET_DETAILS, pet)
                        context.startActivity(this)
                    }
                }
            ),
        elevation = 5.dp,
        shape = RoundedCornerShape(3.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .width(60.dp)
                    .height(60.dp),
                painter = painterResource(id = pet.image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )

            Column {
                Text(
                    text = pet.name,
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontFamily = FontFamily(Font(resId = R.font.montserrat_bold))
                )

                Text(
                    text = String.format(
                        stringResource(id = R.string.years_old),
                        pet.age
                    ),
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontFamily = FontFamily(Font(resId = R.font.montserrat_regular))
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp),
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    )
                    Text(
                        text = pet.location,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontFamily = FontFamily(Font(resId = R.font.montserrat_regular))
                    )
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

fun getPetList() = ArrayList<Pet>().apply {
    add(
        Pet(
            id = 1,
            name = "American Bulldog",
            age = 1,
            weight = 5,
            location = "San Francisco",
            image = R.drawable.american_bulldog
        )
    )

    add(
        Pet(
            id = 2,
            name = "Borador",
            age = 2,
            weight = 4,
            location = "Maharashtra",
            image = R.drawable.borador
        )
    )

    add(Pet(id = 3, name = "Bulldog", age = 3, weight = 6, location = "Ahmednagar", image = R.drawable.bulldog))

    add(Pet(id = 4, name = "Chinook", age = 4, weight = 7, location = "Sangali", image = R.drawable.chinook))

    add(Pet(id = 5, name = "Eurasier", age = 5, weight = 9, location = "Satara", image = R.drawable.eurasier))

    add(Pet(id = 6, name = "Frenchton", age = 6, weight = 4, location = "Nashik", image = R.drawable.frenchton))

    add(
        Pet(
            id = 7,
            name = "German Shepherd",
            age = 7,
            weight = 11,
            location = "Jalgao",
            image = R.drawable.german_shepherd
        )
    )

    add(Pet(id = 8, name = "Jack Chi", age = 8, weight = 11, location = "Pune", image = R.drawable.jack_chi))

    add(Pet(id = 9, name = "Whoodle", age = 9, weight = 9, location = "Mumbai", image = R.drawable.whoodle))

    add(
        Pet(
            id = 10,
            name = "Yorkipoo",
            age = 6,
            weight = 5,
            location = "Ratnagiri",
            image = R.drawable.yorkipoo
        )
    )
}
