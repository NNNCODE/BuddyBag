package com.example.buddybag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buddybag.data.Phrase
import com.example.buddybag.ui.HomeScreen
import com.example.buddybag.ui.PhrasebookScreen
import com.example.buddybag.ui.theme.BuddyBagTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Static list of sample phrases for testing
        val fakePhrases = listOf(
            Phrase("Restaurant", "我想点这个", "Je voudrais commander ceci", "I'd like to order this"),
            Phrase("Hospital", "我肚子疼", "J'ai mal au ventre", "I have a stomach ache"),
            Phrase("Supermarket", "这个多少钱？", "C'est combien ?", "How much is this?"),
            Phrase("Transport", "请问地铁站在哪？", "Où est la station de métro ?", "Where is the metro?")
        )

        setContent {
            val navController = rememberNavController()

            BuddyBagTheme {
                // Navigation host controlling screen routes
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onNavigateToPhrasebook = {
                                navController.navigate("phrasebook")
                            },
                            onNavigateToChecklist = { /* TODO: Navigate to Checklist Screen */ },
                            onNavigateToShoppingList = { /* TODO: Navigate to Shopping List Screen */ },
                            onNavigateToHelp = { /* TODO: Navigate to Help Screen */ }
                        )
                    }

                    composable("phrasebook") {
                        PhrasebookScreen(phrases = fakePhrases)
                    }
                }
            }
        }
    }
}
