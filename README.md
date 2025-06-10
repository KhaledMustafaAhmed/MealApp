# YumYum: Meal Planning App for Android

## Overview
YumYum is an Android application that helps users plan their weekly meals by providing recipe discovery, meal organization tools, and offline access to saved content. The app integrates with TheMealDB API and uses modern Android development practices.

## App Demo
### Main Feature Walkthrough
![YumYum1](https://github.com/user-attachments/assets/8bd66429-779b-4381-9f29-0489b3b2d8d8)

## Features

### 🍽️ Meal Discovery
- **Daily Inspiration**: Meal of the day suggestion
- **Advanced Search**:
  - By country (Italian, Mexican, etc.)
  - By ingredient (chicken, pasta, etc.)
  - By category (vegetarian, desserts)
- **Regional Cuisines**: Browse meals by origin country

### 💙 Favorites System
- Save/remove favorite meals
- Offline access using Room database
- Sync across devices via Firebase

### 📅 Weekly Planner
- Drag-and-drop meal scheduling
- Visual weekly calendar
- Offline editing capabilities

### 👨‍🍳 Recipe Details
- High-quality meal images
- Ingredients with measurements
- Step-by-step instructions
- Embedded cooking video
- Quick favorite toggle

### 🔐 User Authentication
- Email/password login
- Social login (Google, Facebook)
- Guest mode (limited functionality)
- Auto-login via SharedPreferences
  
### 📴 Offline Capabilities  
- Access saved favorites without internet  
- View/modify current week's meal plan offline  
- Auto-sync when connection restores  

### ✨ Enhanced UX  
- Animated splash screen (Lottie)  
- Material Design  
- RX-Java implementation  

## Technical Implementation  
| Component            | Technology Used          |
|----------------------|--------------------------|
| Architecture         | MVP Pattern              |
| Local Database       | Room                     |
| Remote API           | TheMealDB + Retrofit     |
| Authentication       | Firebase Auth            |
| Data Sync            | Firebase Realtime DB     |
| Async Operations     | RX-Java                  |

### Architecture
```mermaid
graph TD
    A[UI Layer] --> B[Presenter]
    B --> C[Repository]
    C --> D[Local DB - Room]
    C --> E[Remote API - Retrofit]
    C --> F[Firebase]

