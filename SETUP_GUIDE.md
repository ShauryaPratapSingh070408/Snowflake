# 🚀 Snowflake AI - Complete Setup Guide

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Development Environment Setup](#development-environment-setup)
3. [API Key Configuration](#api-key-configuration)
4. [Building the Project](#building-the-project)
5. [Installing on Device](#installing-on-device)
6. [First-Time App Setup](#first-time-app-setup)
7. [Troubleshooting](#troubleshooting)

---

## Prerequisites

### Required Software
- **Android Studio**: Hedgehog (2023.1.1) or newer
- **JDK**: Version 17 or higher
- **Git**: Latest version
- **Android SDK**: Level 26 (Android 8.0) or higher

### Hardware Requirements
- **RAM**: Minimum 8GB (16GB recommended)
- **Storage**: 10GB free space
- **Android Device**: Running Android 8.0 or higher

---

## Development Environment Setup

### Step 1: Install Android Studio

1. Download from [Android Studio website](https://developer.android.com/studio)
2. Run the installer
3. During setup, select:
   - Android SDK
   - Android SDK Platform
   - Android Virtual Device

### Step 2: Configure Android SDK

1. Open Android Studio
2. Go to: **Settings** → **Appearance & Behavior** → **System Settings** → **Android SDK**
3. Select SDK Platforms tab
4. Install:
   - Android 14.0 (API 34)
   - Android 8.0 (API 26)
5. Select SDK Tools tab
6. Install:
   - Android SDK Build-Tools
   - Android SDK Platform-Tools
   - Android Emulator

### Step 3: Install JDK 17

```bash
# On macOS (using Homebrew)
brew install openjdk@17

# On Ubuntu/Debian
sudo apt install openjdk-17-jdk

# On Windows
# Download from: https://adoptium.net/
```

Verify installation:
```bash
java -version
# Should show: openjdk version "17.x.x"
```

---

## API Key Configuration

### Step 1: Get Gemini API Key

1. Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Sign in with your Google account
3. Click **"Create API Key"**
4. Select your Google Cloud project (or create new one)
5. Copy the generated API key

### Step 2: Add API Key to Project

#### Method 1: local.properties (Recommended)

1. Create `local.properties` in project root (if not exists)
2. Add your API key:
   ```properties
   GEMINI_API_KEY=AIzaSy...
   ```
3. Save file
4. **Note**: This file is git-ignored and won't be committed

#### Method 2: In-App Configuration

1. Leave `local.properties` empty or use placeholder
2. Build and install app
3. Open app → Settings → API Configuration
4. Paste API key
5. Tap "Save & Test"

---

## Building the Project

### Step 1: Clone Repository

```bash
git clone https://github.com/ShauryaPratapSingh070408/Snowflake.git
cd Snowflake
```

### Step 2: Open in Android Studio

1. Launch Android Studio
2. Select **"Open"**
3. Navigate to cloned `Snowflake` directory
4. Click **"OK"**
5. Wait for Gradle sync (may take 5-10 minutes first time)

### Step 3: Sync Gradle

If Gradle doesn't sync automatically:
1. Click **"File"** → **"Sync Project with Gradle Files"**
2. Wait for completion
3. Check for errors in "Build" panel

### Step 4: Build APK

#### Via Android Studio GUI:
1. Click **"Build"** → **"Build Bundle(s) / APK(s)"** → **"Build APK(s)"**
2. Wait for build to complete
3. Click **"locate"** to find APK

#### Via Command Line:

```bash
# Debug APK (for testing)
./gradlew assembleDebug

# Release APK (for distribution)
./gradlew assembleRelease

# Clean build
./gradlew clean assembleDebug
```

**Output locations:**
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release.apk`

---

## Installing on Device

### Method 1: USB Install

#### Enable USB Debugging:
1. On Android device:
   - Settings → About Phone
   - Tap "Build Number" 7 times
   - Go back → Developer Options
   - Enable "USB Debugging"

#### Install APK:
```bash
# Connect device via USB
adb devices  # Verify device is connected

# Install debug APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Or use Gradle
./gradlew installDebug
```

### Method 2: Manual Install

1. Copy APK to device (email, USB, cloud storage)
2. On device, open file manager
3. Navigate to APK location
4. Tap APK file
5. Allow "Install from Unknown Sources" if prompted
6. Tap "Install"
7. Wait for installation
8. Tap "Open"

### Method 3: Wireless Install (ADB over WiFi)

```bash
# First time: connect via USB
adb tcpip 5555
adb connect <device-ip>:5555

# Disconnect USB, then:
adb install app/build/outputs/apk/debug/app-debug.apk
```

---

## First-Time App Setup

### Step 1: Grant Permissions

On first launch, Snowflake will request:

1. **Camera** - For Frost Vision
   - Tap "Allow"

2. **Microphone** - For voice commands
   - Tap "Allow"

3. **Display over other apps** - For floating bubble
   - Tap "Go to Settings"
   - Enable for Snowflake
   - Press back

### Step 2: Enable Accessibility Service

1. App will prompt to enable Accessibility Service
2. Tap "Enable"
3. You'll be taken to: Settings → Accessibility
4. Find "Snowflake" in the list
5. Tap it
6. Toggle ON
7. Read permission description
8. Tap "Allow"
9. Press back to return to app

### Step 3: Configure API Key (if not set during build)

1. In Snowflake app, tap **"Settings"** (gear icon)
2. Scroll to **"API Configuration"**
3. Paste your Gemini API key
4. Tap **"Test API Key"**
5. Wait for validation
6. If successful, tap **"Save"**

### Step 4: Test Features

1. **Test Voice**: Say "Hey Snowflake"
2. **Test Frost Vision**: Tap Frost Vision button, point camera at something
3. **Test Chat**: Type a message and send

---

## Troubleshooting

### Build Issues

#### Gradle Sync Failed
```bash
# Clear Gradle cache
./gradlew clean
rm -rf .gradle
rm -rf build

# Re-sync
./gradlew build --refresh-dependencies
```

#### Out of Memory
```bash
# Increase Gradle memory in gradle.properties
org.gradle.jvmargs=-Xmx4096m -Dfile.encoding=UTF-8
```

#### Dependency Resolution Failed
```bash
# Update Gradle wrapper
./gradlew wrapper --gradle-version=8.2
```

### Installation Issues

#### Device Not Detected
```bash
# Check ADB
adb devices

# Restart ADB server
adb kill-server
adb start-server
```

#### Installation Failed (INSTALL_FAILED_UPDATE_INCOMPATIBLE)
```bash
# Uninstall old version
adb uninstall com.shaurya.snowflake

# Install fresh
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Runtime Issues

#### API Key Error
- Verify key is correct in Settings
- Check internet connection
- Ensure API key has no extra spaces
- Try generating new API key

#### Accessibility Service Not Working
- Disable and re-enable in Settings → Accessibility
- Restart device
- Reinstall app

#### Frost Vision Not Working
- Grant camera permission in Settings → Apps → Snowflake → Permissions
- Ensure camera is not used by another app
- Restart app

#### Voice Recognition Issues
- Grant microphone permission
- Check microphone is working (test with voice recorder)
- Speak clearly and close to device
- Reduce background noise

---

## Advanced Configuration

### Custom Build Variants

```groovy
// In app/build.gradle.kts
android {
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
        }
        release {
            minifyEnabled = true
            shrinkResources = true
        }
    }
}
```

### Signing Release APK

1. Generate keystore:
```bash
keytool -genkey -v -keystore snowflake-release.jks -keyalg RSA -keysize 2048 -validity 10000 -alias snowflake
```

2. Add to `app/build.gradle.kts`:
```kotlin
android {
    signingConfigs {
        create("release") {
            storeFile = file("../snowflake-release.jks")
            storePassword = "your_password"
            keyAlias = "snowflake"
            keyPassword = "your_password"
        }
    }
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
        }
    }
}
```

---

## Getting Help

- **GitHub Issues**: [Report bugs](https://github.com/ShauryaPratapSingh070408/Snowflake/issues)
- **Discussions**: [Ask questions](https://github.com/ShauryaPratapSingh070408/Snowflake/discussions)
- **Documentation**: [Read Wiki](https://github.com/ShauryaPratapSingh070408/Snowflake/wiki)

---

**Happy Building! 🌨️**