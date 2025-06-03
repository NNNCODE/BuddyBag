# 🎒 BuddyBag – Student Life Assistant App

**BuddyBag** is an Android app designed to support international students arriving in France. It combines multilingual language help, task checklists, and local life guidance into one simple tool.

---

## 🚀 Features

- 🌐 **Multilingual Support** (English, French, Chinese)
- 🔐 **Firebase Login/Logout** (email + password)
- 📖 **Phrasebook** – categorized common phrases (e.g., restaurant, hospital, supermarket)
- 📋 **Registration Checklist** – pre-filled + user-defined tasks (e.g., “Open bank account”)
- 🛒 **Shopping List** – grocery checklist with persistence
- ➕ **Add Custom Item** – add personalized checklist entries
- 🆘 **Local Guide** – SIM cards, transport cards, emergency numbers
- 👤 **Profile Page** – view user email and logout
- 📈 **Firebase Analytics** – click events logged with language parameter

---

## 🧱 Tech Stack

- Kotlin + Jetpack Compose
- MVVM architecture with ViewModel + StateFlow
- Firebase Authentication + Analytics
- Jetpack DataStore (checklist & language persistence)
- Navigation Compose for screen routing

---

## 📂 Folder Structure

├── ui/ # Screens (ChecklistScreen, LoginScreen, etc.)
├── data/ # Data models (ChecklistItem, Language, etc.)
├── viewmodel/ # ViewModels for state management
├── utils/ # DataStore helpers
└── MainActivity.kt # App entry & navigation

