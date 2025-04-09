
# 🛒 Product Mart

**Product Mart** is a modern e-commerce prototype app built using **Kotlin** and **Jetpack Compose** with a clean MVVM architecture. It fetches product data from the [DummyJSON API](https://dummyjson.com/) and provides rich UI features such as:

- 🔍 Search Functionality  
- 🗂 Category Filtering  
- 📊 Product Sorting  
- 🖼 Product Gallery View  
- 📦 Detailed Product View  

---

## 📲 APK Download

You can download the latest APK from the link below:

🔗 [Download APK](https://drive.google.com/drive/folders/1lbYlgm4Jbz2qhAt6ikMEx5XNv3LOSkFE?usp=sharing)

---

## ✨ Features

### ✅ Home Screen
- Displays a list of all products fetched from DummyJSON API
- Shows title, image, description, and price
- Dynamic **search bar** to filter products by name
- **Category dropdown** to filter by categories like `smartphones`, `beauty`, etc.
- **Sort dropdown** to sort by `price`, `title`, or `rating`

### ✅ Detail Screen
- Navigates on clicking a product card
- Displays detailed info: images, title, brand, price, and description

---

## 📦 Tech Stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose (Material 3)  
- **Architecture:** MVVM  
- **Networking:** Retrofit with Moshi  
- **Image Loading:** Coil  
- **State Management:** StateFlow   

---
## 📸 Screenshots

![Home](images/img1%20(1).jpg)

![Search](images/img1%20(2).jpg)

![Category & Sort](images/img1%20(3).jpg)

![Product Detail](images/img1%20(4).jpg)

![Navigation](images/img1%20(5).jpg)


## 🚀 Getting Started

### Prerequisites
- Android Studio Giraffe or later
- Android device or emulator (API 30+)

### Run the app
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle and Run the app

---

## 🛠️ Project Structure

```
com.example.productmart/
│
├── model/               # Data models
├── network/             # Retrofit API Interface
├── repository/          # Repository pattern abstraction
├── ui/                  
│   ├── screens/         # Composable screens (Home, Detail)
│   ├── components/      # Reusable UI components
│   └── navigation/      # NavGraph
└── viewmodel/           # ProductViewModel and DetailViewModel
```