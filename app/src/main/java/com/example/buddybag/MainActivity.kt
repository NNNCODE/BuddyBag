package com.example.buddybag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buddybag.data.ChecklistItem
import com.example.buddybag.data.Phrase
import com.example.buddybag.data.ShoppingItem
import com.example.buddybag.ui.ChecklistScreen
import com.example.buddybag.ui.HomeScreen
import com.example.buddybag.ui.PhrasebookScreen
import com.example.buddybag.ui.ShoppingListScreen
import com.example.buddybag.ui.theme.BuddyBagTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Static list of sample phrases for testing
        val fakePhrases = listOf(
            Phrase(
                "Restaurant",
                "我想点这个",
                "Je voudrais commander ceci",
                "I'd like to order this"
            ),
            Phrase("Hospital", "我肚子疼", "J'ai mal au ventre", "I have a stomach ache"),
            Phrase("Supermarket", "这个多少钱？", "C'est combien ?", "How much is this?"),
            Phrase(
                "Transport",
                "请问地铁站在哪？",
                "Où est la station de métro ?",
                "Where is the metro?"
            )
        )

        val checklistItems = listOf(
            ChecklistItem("CAF Registration", "Housing subsidy", false),
            ChecklistItem("Ameli (Health Insurance)", "Create an Ameli account", false),
            ChecklistItem("Open a bank account", "LCL, SG, BNP, Revolut etc.", false),
            ChecklistItem("Get student card", "From your school", false),
            ChecklistItem("OFII registration", "For non-EU students", false)
        )
        // Static shopping list data
        val shoppingItems = listOf(
            ShoppingItem("Milk", false),
            ShoppingItem("Bread", false),
            ShoppingItem("Eggs", false),
            ShoppingItem("Toothpaste", false),
            ShoppingItem("Shampoo", false)
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
                            onNavigateToChecklist = {
                                navController.navigate("checklist")
                            },
                            onNavigateToShoppingList = {
                                navController.navigate("shopping")
                            },
                            onNavigateToHelp = { /* TODO: Navigate to Help Screen */ }

                        )
                    }
                    composable("phrasebook") {
                        PhrasebookScreen(phrases = fakePhrases)
                    }
                    composable("checklist") {
                        ChecklistScreen(initialItems = checklistItems)
                    }
                    composable("shopping") {
                        ShoppingListScreen(initialItems = shoppingItems)
                    }
                }
            }
        }
    }
}
