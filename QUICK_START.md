# 🚀 Snowflake AI - Quick Start Guide

## 📲 For Users (Install Pre-built APK)

### Download & Install

1. **Download APK**
   - Go to [Releases](https://github.com/ShauryaPratapSingh070408/Snowflake/releases)
   - Download `snowflake-v1.0.0.apk` (latest version)

2. **Install**
   - Open downloaded APK
   - Allow "Install from Unknown Sources" if prompted
   - Tap "Install"
   - Wait for installation
   - Tap "Open"

3. **Setup** (5 minutes)
   - Grant Camera, Microphone permissions
   - Enable "Display over other apps"
   - Enable Accessibility Service
   - Add your Gemini API key (get free at [Google AI Studio](https://makersuite.google.com/app/apikey))

4. **Start Using**
   - Say "Hey Snowflake"
   - Or tap the floating bubble
   - Try: "Frost Vision, what am I looking at?"

---

## 🛠️ For Developers (Build from Source)

### Prerequisites
- Android Studio Hedgehog+
- JDK 17+
- Gemini API Key

### 3-Minute Setup

```bash
# 1. Clone
git clone https://github.com/ShauryaPratapSingh070408/Snowflake.git
cd Snowflake

# 2. Add API Key
echo "GEMINI_API_KEY=your_key_here" > local.properties

# 3. Build
./gradlew assembleDebug

# 4. Install
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Or Use Android Studio

1. Open project in Android Studio
2. Wait for Gradle sync
3. Click "Run" (Shift + F10)

---

## ✨ First Commands to Try

```
"Hey Snowflake, introduce yourself"
"Frost Vision, what's in front of me?"
"Send 'Hello' to [Contact] on WhatsApp"
"What's on my screen?"
"Set alarm for 7 AM"
```

---

## 🎯 Key Features

- **Frost Vision** - Real-time camera AI
- **Universal Automation** - Control any app
- **Infinite Memory** - Never forgets
- **Voice Control** - Hands-free everything
- **Privacy First** - All data stays on device

---

## 🔗 Links

- [Full Documentation](README.md)
- [Setup Guide](SETUP_GUIDE.md)
- [Contributing](CONTRIBUTING.md)
- [Issues](https://github.com/ShauryaPratapSingh070408/Snowflake/issues)

---

**Questions?** Open an [issue](https://github.com/ShauryaPratapSingh070408/Snowflake/issues) or [discussion](https://github.com/ShauryaPratapSingh070408/Snowflake/discussions)!