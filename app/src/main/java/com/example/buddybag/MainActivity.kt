package com.example.buddybag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buddybag.data.*
import com.example.buddybag.ui.*
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
        // 🆘 Local life help items (with full translation)
        val helpItems = listOf(
            HelpItem(
                title_en = "Buy a SIM Card",
                desc_en = "Go to Orange, SFR, Free Mobile, or Bouygues stores.",
                title_fr = "Acheter une carte SIM",
                desc_fr = "Rendez-vous chez Orange, SFR, Free Mobile ou Bouygues.",
                title_zh = "购买电话卡",
                desc_zh = "前往 Orange、SFR、Free 或 Bouygues 营业厅办理。"
            ),
            HelpItem(
                title_en = "Get a Navigo Card",
                desc_en = "For metro, RER and bus in Paris region.",
                title_fr = "Obtenir une carte Navigo",
                desc_fr = "Pour le métro, le RER et les bus en Île-de-France.",
                title_zh = "办理 Navigo 公交卡",
                desc_zh = "在巴黎地区乘坐公交、地铁、RER 的通用交通卡。"
            ),
            HelpItem(
                title_en = "Emergency Numbers",
                desc_en = "112 (EU), 15 (Medical), 17 (Police), 18 (Fire)",
                title_fr = "Numéros d'urgence",
                desc_fr = "112 (UE), 15 (Urgence), 17 (Police), 18 (Pompiers)",
                title_zh = "紧急联系电话",
                desc_zh = "112（欧洲通用），15（医疗），17（警察），18（火警）"
            )
        )


        setContent {
            val navController = rememberNavController()

            // 🌐 Language state (EN/FR/ZH)
            var currentLanguage by remember { mutableStateOf(Language.EN) }

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
                            onNavigateToHelp = {
                                navController.navigate("help")
                            }
                            ,
                            currentLanguage = currentLanguage,
                            onLanguageChange = { currentLanguage = it }
                        )
                    }
                    composable("phrasebook") {
                        PhrasebookScreen(
                            phrases = fakePhrases,
                            lang = currentLanguage
                        )
                    }
                    composable("checklist") {
                        ChecklistScreen(initialItems = checklistItems)
                    }
                    composable("shopping") {
                        ShoppingListScreen(initialItems = shoppingItems)
                    }
                    composable("help") {
                        HelpGuideScreen(helpItems = helpItems, lang = currentLanguage)
                    }

                }
            }
        }
    }
}
