# 🌨️ Snowflake AI

**Advanced Android AI Assistant with Frost Vision™**

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0+-7F52FF.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.6+-4285F4.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-26-green.svg)](https://developer.android.com/about/versions/oreo)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/ShauryaPratapSingh070408/Snowflake?style=social)](https://github.com/ShauryaPratapSingh070408/Snowflake/stargazers)

Snowflake is a next-generation AI assistant that brings **real-time visual intelligence**, **universal app automation**, and **infinite contextual memory** to your Android device. Built with Kotlin and powered by Google Gemini 2.5 Flash.

---

## 📱 Download APK - Ready to Install!

### 🚀 Quick Install (For Users)

**Want to use Snowflake right now? No coding needed!**

#### Option 1: Download Pre-Built APK (When Available)

📥 **[Download Latest APK](https://github.com/ShauryaPratapSingh070408/Snowflake/releases/latest)**

1. Click the link above on your Android phone
2. Download `app-release.apk`
3. Open and install
4. Done! 🎉

#### Option 2: Build APK Instantly (5-10 minutes)

**No Android Studio needed! GitHub builds it for you:**

1. **Go to Actions:** [Click Here](https://github.com/ShauryaPratapSingh070408/Snowflake/actions/workflows/build-apk-manual.yml)
2. **Click "Run workflow"** (green button, top right)
3. **Select "debug" or "release"**
4. **Click "Run workflow"** again
5. **Wait 5-10 minutes** for build to complete ⏱️
6. **Download APK** from "Artifacts" section
7. **Transfer to phone and install!**

📖 **Detailed Instructions:** [DOWNLOAD_APK.md](DOWNLOAD_APK.md)

---

## ✨ Key Features

### 👁️ Frost Vision™ - Real-Time Visual AI
- **Live Camera Analysis** - See what Snowflake sees in real-time
- **Object Recognition** - Identify anything instantly
- **Scene Understanding** - Contextual awareness of surroundings
- **Live OCR** - Read text from camera feed
- **Visual Translation** - Translate text in real-time
- **Product Scanner** - Find products online instantly
- **Accessibility Mode** - Visual assistance for the visually impaired

### 🤖 Universal Automation
- **WhatsApp Integration** - Send messages via voice commands
- **Screen Analysis** - AI understands what's on screen
- **Smart Actions** - Auto-fill forms, click buttons, scroll
- **Cross-App Workflows** - Chain actions across apps
- **Voice Control** - Control any app with voice

### 🧠 Infinite Memory System
- **Semantic Search** - Find any past conversation instantly
- **Context Retention** - Remembers everything across sessions
- **Smart Deletion** - Granular control over what to keep/delete
- **Knowledge Graph** - Builds understanding over time
- **Export/Import** - Backup and restore memories

### 🎯 Always-On Assistant
- **Wake Word Detection** - "Hey Snowflake" from any screen
- **Floating Bubble** - Beautiful overlay interface
- **System-Wide Access** - Works in every app
- **Battery Optimized** - Smart power management

---

## 🔧 Build from Source (For Developers)

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or higher
- Android SDK 26+ (Android 8.0 Oreo)
- Google Gemini API Key ([Get free key](https://makersuite.google.com/app/apikey))

### Installation Steps

1. **Clone the repository**
```bash
git clone https://github.com/ShauryaPratapSingh070408/Snowflake.git
cd Snowflake
```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory
   - Click "OK"

3. **Configure API Key**
   - Create `local.properties` file in root directory
   - Add your Gemini API key:
   ```properties
   GEMINI_API_KEY=your_api_key_here
   ```

4. **Sync Gradle**
   - Wait for Gradle sync to complete
   - Download dependencies (first time may take a few minutes)

5. **Build APK**
   ```bash
   # Debug APK
   ./gradlew assembleDebug
   
   # Release APK
   ./gradlew assembleRelease
   ```

6. **Install on Device**
   - Connect Android device via USB
   - Enable USB debugging
   - Click "Run" in Android Studio or:
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

---

## 📚 Usage Guide

### First-Time Setup

1. **Grant Permissions**
   - Camera (for Frost Vision)
   - Microphone (for voice commands)
   - Display over other apps (for floating bubble)
   - Accessibility service (for automation)

2. **Enable Accessibility Service**
   - Go to: Settings → Accessibility → Snowflake
   - Toggle ON

3. **Add API Key** (if not added during build)
   - Open Snowflake app
   - Navigate to Settings
   - Enter your Gemini API key
   - Tap "Save & Test"

### Voice Commands

#### Activation
```
"Hey Snowflake"
"Snowflake, wake up"
```

#### Frost Vision
```
"Frost Vision, what am I looking at?"
"Enable Frost Vision"
"Translate this to Hindi"
"Read this document"
"Identify this object"
```

#### Communication
```
"Send 'Hello' to John on WhatsApp"
"Call Mom"
"Read my messages"
"Reply 'Yes' to last message"
```

#### Screen Interaction
```
"What's on my screen?"
"Summarize this article"
"Fill this form"
"Scroll down"
```

#### Automation
```
"Navigate to nearest coffee shop"
"Set alarm for 7 AM"
"Play my favorite playlist"
"Turn on WiFi"
```

---

## 🛠️ Technology Stack

### Core Technologies
- **Language**: Kotlin 2.0+
- **UI Framework**: Jetpack Compose (Material 3)
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt
- **Async Processing**: Coroutines + Flow

### AI/ML Integration
- **Gemini 2.5 Flash** - Conversational AI (10 RPM, 250K TPM)
- **Gemini 2.5 Flash Live** - Real-time Vision (Unlimited RPM, 1M TPM)
- **ML Kit** - OCR & Text Recognition
- **Google Speech-to-Text** - Voice input
- **Google Text-to-Speech** - Voice output

### Android Components
- **CameraX** - Camera processing for Frost Vision
- **Accessibility Service** - Universal app automation
- **WindowManager** - Floating overlay bubble
- **Room Database** - Local memory storage
- **DataStore** - Preferences management
- **WorkManager** - Background task scheduling

### Libraries
- **Retrofit + OkHttp** - Network communication
- **Coil** - Image loading
- **Lottie** - Animations
- **Accompanist** - Permissions handling

---

## 💾 Project Structure

```
Snowflake/
├── app/
│   ├── src/main/
│   │   ├── java/com/shaurya/snowflake/
│   │   │   ├── data/              # Data layer
│   │   │   │   ├── local/         # Room database, DAOs
│   │   │   │   ├── remote/        # Gemini API service
│   │   │   │   └── repository/    # Data repositories
│   │   │   ├── domain/            # Domain layer
│   │   │   │   ├── model/         # Data models
│   │   │   │   └── usecase/       # Business logic
│   │   │   ├── presentation/      # Presentation layer
│   │   │   │   ├── ui/            # Compose screens
│   │   │   │   ├── viewmodel/     # ViewModels
│   │   │   │   └── theme/         # Material 3 theme
│   │   │   ├── service/           # Android services
│   │   │   │   ├── accessibility/ # Accessibility Service
│   │   │   │   ├── overlay/       # Floating bubble
│   │   │   │   ├── voice/         # Voice recognition
│   │   │   │   └── vision/        # Frost Vision
│   │   │   └── di/                # Hilt DI modules
│   │   ├── res/
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── .github/workflows/        # CI/CD
├── build.gradle.kts
├── settings.gradle.kts
├── CONTRIBUTING.md
├── LICENSE
└── README.md
```

---

## 🔧 Building APK with GitHub Actions

### Automatic Builds

Every push to `main` triggers automatic APK building:

1. **Push your changes**
   ```bash
   git push origin main
   ```

2. **GitHub Actions builds APK automatically**
   - Navigate to: Actions tab on GitHub
   - Wait for workflow to complete
   - Download APK from Artifacts

### Manual Build Commands

```bash
# Debug APK (for testing)
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk

# Release APK (for distribution)
./gradlew assembleRelease
# Output: app/build/outputs/apk/release/app-release.apk

# Install directly to connected device
./gradlew installDebug
```

---

## 🧑‍💻 Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

### How to Contribute

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

### Areas We Need Help With

- ⭐ Frost Vision enhancements
- 🔧 Additional automation features
- 🌟 UI/UX improvements
- 🐛 Bug fixes
- 📚 Documentation
- 🌍 Translations

---

## 📝 License

This project is licensed under the **MIT License** - see [LICENSE](LICENSE) file for details.

---

## 👨‍💻 About the Developer

**Shaurya Pratap Singh**

- 🏛️ Systems Architect & AI Engineer
- 🤖 Specializing in Autonomous Intelligence Systems
- 🌟 Google Developer Program Member
- 🌙 R&D Contributor - Moonshot AI (Kimi Model)
- ⭐ Alibaba Cloud Magic Developer
- 📧 New Delhi, India

### Notable Projects
- **Nexus** - Advanced autonomous intelligence system
- **Darshana** - Hybrid architecture AI platform  
- **Panda** - Practical AI for edge and cloud scale
- **Snowball AI** - Gemini-powered conversational assistant

### Connect
- GitHub: [@ShauryaPratapSingh070408](https://github.com/ShauryaPratapSingh070408)
- Project: [Snowflake AI](https://github.com/ShauryaPratapSingh070408/Snowflake)

---

## 🚀 Roadmap

### Version 1.0 (Current)
- [x] Core conversation AI
- [x] Project structure setup
- [x] Gradle configuration
- [x] CI/CD pipeline
- [ ] Frost Vision implementation
- [ ] Infinite memory system
- [ ] Universal automation
- [ ] Floating bubble UI

### Version 2.0 (Planned)
- [ ] Multi-user profiles
- [ ] Cloud sync (optional)
- [ ] Wear OS companion
- [ ] Widget support
- [ ] Tasker integration
- [ ] Smart home integration

---

## 📊 Repository Stats

![GitHub commit activity](https://img.shields.io/github/commit-activity/m/ShauryaPratapSingh070408/Snowflake)
![GitHub last commit](https://img.shields.io/github/last-commit/ShauryaPratapSingh070408/Snowflake)
![GitHub repo size](https://img.shields.io/github/repo-size/ShauryaPratapSingh070408/Snowflake)
![GitHub language count](https://img.shields.io/github/languages/count/ShauryaPratapSingh070408/Snowflake)
![GitHub top language](https://img.shields.io/github/languages/top/ShauryaPratapSingh070408/Snowflake)

---

## ❤️ Acknowledgments

- **Google Gemini AI** team for the amazing models
- **Android team** for Jetpack Compose
- **Open source community** for inspiration and tools
- All **contributors** who help make Snowflake better

---

## 💬 Support

- **Issues**: Report bugs or request features via [GitHub Issues](https://github.com/ShauryaPratapSingh070408/Snowflake/issues)
- **Discussions**: Join conversations in [GitHub Discussions](https://github.com/ShauryaPratapSingh070408/Snowflake/discussions)
- **Documentation**: Check our [Wiki](https://github.com/ShauryaPratapSingh070408/Snowflake/wiki) (coming soon)

---

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=ShauryaPratapSingh070408/Snowflake&type=Date)](https://star-history.com/#ShauryaPratapSingh070408/Snowflake&Date)

---

<div align="center">

**Made with ❤️ by Shaurya Pratap Singh**

*Building practical AI that works reliably at both edge and cloud scale.*

[⬆ Back to top](#-snowflake-ai)

</div>