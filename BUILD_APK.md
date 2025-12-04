# 📦 Building Snowflake APK - Complete Guide

## 🚀 Quick Build (GitHub Actions - Recommended)

### Automatic APK Generation

Every time you push code to the `main` branch, GitHub Actions automatically builds an APK for you!

### Steps:

1. **Push your code**
   ```bash
   git add .
   git commit -m "Your changes"
   git push origin main
   ```

2. **Wait for build** (~5-10 minutes)
   - Go to: https://github.com/ShauryaPratapSingh070408/Snowflake/actions
   - Click on the latest "Android CI/CD" workflow
   - Watch the build progress

3. **Download APK**
   - Once build completes successfully (✅ green checkmark)
   - Scroll down to "Artifacts" section
   - Click **"snowflake-debug"** to download
   - Extract the ZIP file
   - You'll find `app-debug.apk`

4. **Install on Android**
   - Transfer APK to your phone
   - Open file manager
   - Tap the APK
   - Install!

---

## 💻 Local Build Methods

### Method 1: Using Android Studio (Easiest)

#### Prerequisites
- Android Studio Hedgehog or later
- JDK 17+

#### Steps:

1. **Open Project**
   - Launch Android Studio
   - File → Open → Select Snowflake folder
   - Wait for Gradle sync

2. **Build APK**
   - Build → Build Bundle(s) / APK(s) → Build APK(s)
   - Wait for build (~5 minutes first time)
   - Click "locate" when done

3. **Find APK**
   - Location: `app/build/outputs/apk/debug/app-debug.apk`

---

### Method 2: Using Command Line (Faster)

#### Prerequisites
- JDK 17 installed
- Android SDK installed

#### Steps:

1. **Navigate to project**
   ```bash
   cd Snowflake
   ```

2. **Make gradlew executable** (Linux/Mac)
   ```bash
   chmod +x gradlew
   ```

3. **Build Debug APK**
   ```bash
   ./gradlew assembleDebug
   ```
   
   On Windows:
   ```cmd
   gradlew.bat assembleDebug
   ```

4. **Build Release APK** (Optimized)
   ```bash
   ./gradlew assembleRelease
   ```

5. **Find APK**
   - Debug: `app/build/outputs/apk/debug/app-debug.apk`
   - Release: `app/build/outputs/apk/release/app-release.apk`

---

## 🔐 Signing Release APK (For Distribution)

### Why Sign?
- Required for Google Play Store
- Enables app updates
- Proves authenticity

### Steps:

#### 1. Generate Keystore

```bash
keytool -genkey -v -keystore snowflake-release.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias snowflake
```

You'll be asked:
- Password (remember this!)
- Your name
- Organization
- City, State, Country

#### 2. Configure Signing in Android Studio

1. Build → Generate Signed Bundle/APK
2. Select "APK" → Next
3. Choose keystore file (snowflake-release.jks)
4. Enter passwords
5. Select "release" build variant
6. Choose V1 and V2 signatures
7. Click Finish

#### 3. Or Configure in build.gradle.kts

Add to `app/build.gradle.kts`:

```kotlin
android {
    signingConfigs {
        create("release") {
            storeFile = file("../snowflake-release.jks")
            storePassword = "YOUR_STORE_PASSWORD"
            keyAlias = "snowflake"
            keyPassword = "YOUR_KEY_PASSWORD"
        }
    }
    
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
```

Then build:
```bash
./gradlew assembleRelease
```

---

## 🤖 GitHub Actions - Advanced Configuration

### Setting Up Signed Releases

1. **Encode your keystore to Base64**
   ```bash
   base64 -i snowflake-release.jks -o keystore.txt
   ```

2. **Add GitHub Secrets**
   - Go to: Settings → Secrets and variables → Actions
   - Add New Repository Secret:
     - `SIGNING_KEY`: Content of keystore.txt
     - `ALIAS`: Your key alias (e.g., "snowflake")
     - `KEY_STORE_PASSWORD`: Your keystore password
     - `KEY_PASSWORD`: Your key password

3. **Create a Release**
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```
   
   GitHub Actions will automatically:
   - Build release APK
   - Sign it
   - Upload to release

---

## 📥 Installing APK on Android Device

### Method 1: USB Install (ADB)

1. **Enable USB Debugging**
   - Settings → About Phone → Tap "Build Number" 7 times
   - Settings → Developer Options → Enable "USB Debugging"

2. **Connect device to computer**

3. **Install APK**
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

### Method 2: Manual Install

1. **Transfer APK to phone** (via email, cloud, USB)
2. **Open file manager** on phone
3. **Navigate to APK location**
4. **Tap the APK file**
5. **Allow "Install from Unknown Sources"** if prompted
6. **Tap "Install"**
7. **Tap "Open"** when done

### Method 3: QR Code

1. Upload APK to cloud (Google Drive, Dropbox)
2. Get shareable link
3. Generate QR code from link
4. Scan QR code with phone
5. Download and install

---

## ⚙️ Build Variants

Snowflake supports multiple build variants:

| Variant | Command | Output | Use Case |
|---------|---------|--------|----------|
| **Debug** | `./gradlew assembleDebug` | `app-debug.apk` | Development, testing |
| **Release** | `./gradlew assembleRelease` | `app-release.apk` | Distribution, production |

### Debug vs Release

**Debug APK:**
- ✅ Faster build time
- ✅ Includes debugging symbols
- ✅ Larger file size (~15MB)
- ❌ Not optimized
- ❌ Not signed for distribution

**Release APK:**
- ✅ Optimized code (faster performance)
- ✅ Smaller file size (~8MB)
- ✅ ProGuard obfuscation
- ✅ Production-ready
- ❌ Longer build time
- ❌ Requires signing

---

## 🔧 Troubleshooting

### Build Failed

#### Error: "SDK location not found"
```bash
# Create local.properties
echo "sdk.dir=/path/to/Android/Sdk" > local.properties

# On Mac:
echo "sdk.dir=/Users/YOUR_USERNAME/Library/Android/sdk" > local.properties

# On Windows:
echo "sdk.dir=C:\\Users\\YOUR_USERNAME\\AppData\\Local\\Android\\Sdk" > local.properties
```

#### Error: "Gradle sync failed"
```bash
# Clear Gradle cache
./gradlew clean
rm -rf .gradle build

# Re-download dependencies
./gradlew build --refresh-dependencies
```

#### Error: "Out of memory"
```properties
# In gradle.properties, increase memory:
org.gradle.jvmargs=-Xmx4096m -Dfile.encoding=UTF-8
```

### Installation Failed

#### Error: "App not installed"
- Uninstall old version first
- Enable "Install from Unknown Sources"
- Check storage space
- Redownload APK (might be corrupted)

#### Error: "Parse error"
- APK is corrupted, rebuild it
- Wrong Android version (need Android 8.0+)

---

## 📊 APK Size Optimization

Current sizes:
- Debug: ~15 MB
- Release: ~8 MB (with ProGuard)

### Further optimization:

1. **Enable R8/ProGuard** (✅ Already enabled)
   ```kotlin
   release {
       isMinifyEnabled = true
       isShrinkResources = true
   }
   ```

2. **Remove unused resources**
   - Automatically done by build system

3. **Use WebP instead of PNG**
   - Android Studio: Right-click image → Convert to WebP

4. **Enable ABI splits** (for specific devices)
   ```kotlin
   android {
       splits {
           abi {
               isEnable = true
               reset()
               include("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
               isUniversalApk = false
           }
       }
   }
   ```

---

## 📢 Distributing APK

### Option 1: GitHub Releases

1. Create release:
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

2. Go to: https://github.com/ShauryaPratapSingh070408/Snowflake/releases
3. Click "Draft a new release"
4. Upload APK
5. Publish

### Option 2: Google Play Store

1. Build signed release APK
2. Create Google Play Console account
3. Create app listing
4. Upload APK
5. Submit for review

### Option 3: Direct Distribution

1. Upload to cloud storage
2. Share download link
3. Users download and install manually

---

## ✅ Build Checklist

Before building:
- [ ] Gemini API key configured
- [ ] All dependencies downloaded
- [ ] Gradle sync successful
- [ ] No compilation errors
- [ ] Version code incremented (for updates)

For release builds:
- [ ] Keystore file ready
- [ ] Passwords known
- [ ] ProGuard rules tested
- [ ] All features tested
- [ ] Changelog prepared

---

## 🔗 Useful Commands

```bash
# Build everything
./gradlew build

# Build and install debug
./gradlew installDebug

# Build both debug and release
./gradlew assembleDebug assembleRelease

# Clean build artifacts
./gradlew clean

# View all tasks
./gradlew tasks

# Build with stack trace (for debugging)
./gradlew assembleDebug --stacktrace

# Build offline (use cached dependencies)
./gradlew assembleDebug --offline
```

---

## 📞 Support

Need help building?

- [GitHub Issues](https://github.com/ShauryaPratapSingh070408/Snowflake/issues)
- [Discussions](https://github.com/ShauryaPratapSingh070408/Snowflake/discussions)
- Check [SETUP_GUIDE.md](SETUP_GUIDE.md)

---

**Ready to build? Let's go!** 🚀

For quickest results: **Push to GitHub and let Actions build for you!**