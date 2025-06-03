package com.example.buddybag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buddybag.data.*
import com.example.buddybag.ui.*
import com.example.buddybag.ui.theme.BuddyBagTheme
import com.example.buddybag.utils.ChecklistDataStore
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)

        val dataStore = ChecklistDataStore(applicationContext)

        setContent {
            val navController = rememberNavController()

            // 🌐 Language state
            var currentLanguage by remember { mutableStateOf(Language.EN) }

            // ✅ 启动时恢复语言
            LaunchedEffect(Unit) {
                dataStore.currentLanguage.collect { savedLang ->
                    savedLang?.let {
                        currentLanguage = Language.valueOf(it)
                    }
                }
            }

            BuddyBagTheme {
                // Navigation host controlling screen routes
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onNavigateToPhrasebook = {
                                val bundle = Bundle().apply {
                                    putString("language", currentLanguage.name)
                                }
                                firebaseAnalytics.logEvent("click_phrasebook", bundle)
                                navController.navigate("phrasebook")
                            },
                            onNavigateToChecklist = {
                                val bundle = Bundle().apply {
                                    putString("language", currentLanguage.name)
                                }
                                firebaseAnalytics.logEvent("click_checklist", bundle)
                                navController.navigate("checklist")
                            },
                            onNavigateToShoppingList = {
                                val bundle = Bundle().apply {
                                    putString("language", currentLanguage.name)
                                }
                                firebaseAnalytics.logEvent("click_shopping", bundle)
                                navController.navigate("shopping")
                            },
                            onNavigateToHelp = {
                                val bundle = Bundle().apply {
                                    putString("language", currentLanguage.name)
                                }
                                firebaseAnalytics.logEvent("click_help", bundle)
                                navController.navigate("help")
                            },
                            currentLanguage = currentLanguage,
                            onLanguageChange = {
                                currentLanguage = it
                                lifecycleScope.launch {
                                    dataStore.saveLanguage(it.name)
                                }
                            }
                        )
                    }

                    composable("phrasebook") {
                        PhrasebookScreen(
                            phrases = listOf(
                                Phrase("Restaurant", "我想点这个", "Je voudrais commander ceci", "I'd like to order this"),
                                Phrase("Hospital", "我肚子疼", "J'ai mal au ventre", "I have a stomach ache"),
                                Phrase("Supermarket", "这个多少钱？", "C'est combien ?", "How much is this?"),
                                Phrase("Transport", "请问地铁站在哪？", "Où est la station de métro ?", "Where is the metro?")
                            ),
                            lang = currentLanguage
                        )
                    }

                    composable("checklist") {
                        ChecklistScreen()
                    }

                    composable("shopping") {
                        ShoppingListScreen()
                    }

                    composable("help") {
                        HelpGuideScreen(
                            helpItems = listOf(
                                HelpItem(
                                    "Buy a SIM Card",
                                    "Go to Orange, SFR, Free Mobile, or Bouygues stores.",
                                    "Acheter une carte SIM",
                                    "Rendez-vous chez Orange, SFR, Free Mobile ou Bouygues.",
                                    "购买电话卡",
                                    "前往 Orange、SFR、Free 或 Bouygues 营业厅办理。"
                                ),
                                HelpItem(
                                    "Get a Navigo Card",
                                    "For metro, RER and bus in Paris region.",
                                    "Obtenir une carte Navigo",
                                    "Pour le métro, le RER et les bus en Île-de-France.",
                                    "办理 Navigo 公交卡",
                                    "在巴黎地区乘坐公交、地铁、RER 的通用交通卡。"
                                ),
                                HelpItem(
                                    "Emergency Numbers",
                                    "112 (EU), 15 (Medical), 17 (Police), 18 (Fire)",
                                    "Numéros d'urgence",
                                    "112 (UE), 15 (Urgence), 17 (Police), 18 (Pompiers)",
                                    "紧急联系电话",
                                    "112（欧洲通用），15（医疗），17（警察），18（火警）"
                                )
                            ),
                            lang = currentLanguage
                        )
                    }
                }
            }
        }
    }
}
