# 🚀 BUILD SNOWFLAKE APK IN GOOGLE COLAB

**Complete Guide to Build APK Without Android Studio**

---

## ✅ QUICK START

**Just need the APK?**
1. Open Google Colab: https://colab.research.google.com/
2. Create new notebook
3. Copy code from Step 2 onwards
4. Replace API key
5. Run all cells
6. Download APK!

**Time:** 16 minutes | **Cost:** Free | **No Installation Needed**

---

## ✅ PREREQUISITES

- ✅ Google Account (for Colab)
- ✅ GitHub Account (to clone repo)
- ✅ Gemini API Key from https://makersuite.google.com/app/apikey
- ✅ Internet connection
- ✅ ~16 minutes time

---

## 🎯 COMPLETE COLAB SCRIPT

**Copy this entire script and paste into a Colab notebook:**

```python
# ❄️ SNOWFLAKE AI APK BUILDER - GOOGLE COLAB
# Just run this script!

print("🚀 Starting Snowflake APK Build...\n")
print("This will take about 16 minutes.\n")

import os
import subprocess
from google.colab import files

# ===== STEP 1: Install Java =====
print("[1/8] Installing Java...")
!apt-get update > /dev/null 2>&1
!apt-get install -y openjdk-17-jdk > /dev/null 2>&1
os.environ['JAVA_HOME'] = '/usr/lib/jvm/java-17-openjdk-amd64'
print("✅ Java installed\n")

# ===== STEP 2: Download Android SDK =====
print("[2/8] Setting up Android SDK (this may take a few minutes)...")
!mkdir -p ~/android-sdk
os.chdir(os.path.expanduser('~/android-sdk'))
!wget -q https://dl.google.com/android/repository/commandlinetools-linux-10406996_latest.zip
!unzip -q commandlinetools-linux-10406996_latest.zip
os.environ['ANDROID_SDK_ROOT'] = os.path.expanduser('~/android-sdk')
os.environ['ANDROID_HOME'] = os.environ['ANDROID_SDK_ROOT']
print("✅ Android SDK downloaded\n")

# ===== STEP 3: Accept licenses and install components =====
print("[3/8] Installing Android build tools...")
android_sdk = os.environ['ANDROID_SDK_ROOT']
!echo y | {android_sdk}/cmdline-tools/bin/sdkmanager --licenses > /dev/null 2>&1
!{android_sdk}/cmdline-tools/bin/sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0" --no_https > /dev/null 2>&1
print("✅ Android tools installed\n")

# ===== STEP 4: Clone Repository =====
print("[4/8] Cloning Snowflake repository...")
os.chdir(os.path.expanduser('~'))
!git clone -q https://github.com/ShauryaPratapSingh070408/Snowflake.git
print("✅ Repository cloned\n")

# ===== STEP 5: Create Configuration =====
print("[5/8] Creating configuration...")
os.chdir(os.path.expanduser('~/Snowflake'))

# 🔑 REPLACE THIS WITH YOUR GEMINI API KEY
api_key = "AIza-YOUR-ACTUAL-API-KEY-HERE"

if "YOUR-ACTUAL-API-KEY-HERE" in api_key:
    print("⚠️  WARNING: Please replace the API key first!")
    print("   Find 'YOUR-ACTUAL-API-KEY-HERE' and replace with your real API key")
    print("   Get your API key from: https://makersuite.google.com/app/apikey")
else:
    with open('local.properties', 'w') as f:
        f.write(f'GEMINI_API_KEY={api_key}\n')
    print("✅ Configuration created\n")

# ===== STEP 6: Build APK =====
print("[6/8] Building APK (this will take 8-10 minutes)...")
print("Please wait for 'Build successful' message...\n")

os.environ['PATH'] = os.environ['ANDROID_SDK_ROOT'] + '/cmdline-tools/cmdline-tools/bin:' + os.environ['ANDROID_SDK_ROOT'] + '/platform-tools:' + os.environ['PATH']

!chmod +x gradlew
result = !./gradlew assembleRelease 2>&1

if "BUILD SUCCESSFUL" in str(result):
    print("\n✅ BUILD SUCCESSFUL!\n")
else:
    print("\n⚠️ Build completed (check output above)\n")

# ===== STEP 7: Verify APK =====
print("[7/8] Verifying APK...")
apk_path = os.path.expanduser('~/Snowflake/app/build/outputs/apk/release/app-release.apk')

if os.path.exists(apk_path):
    size_mb = os.path.getsize(apk_path) / (1024*1024)
    print(f"📱 APK: app-release.apk")
    print(f"📦 Size: {size_mb:.1f} MB")
    print(f"📍 Ready to download\n")
    print("✅ APK verified\n")
else:
    print("❌ APK not found - Build may have failed")
    print("Check the build output above for errors\n")

# ===== STEP 8: Download =====
print("[8/8] Preparing download...\n")
if os.path.exists(apk_path):
    files.download(apk_path)
    print("\n✅ Download started!")
    print("\n🎉 SUCCESS! Your APK is downloading.")
    print("\nNext steps:")
    print("1. Transfer APK to your Android phone")
    print("2. Enable 'Unknown Sources' in Settings")
    print("3. Tap APK to install")
    print("4. Add your Gemini API key in app Settings")
    print("5. Start chatting!\n")
else:
    print("❌ Could not download APK")
```

---

## 🔑 IMPORTANT: Add Your API Key

**Before running the script:**

1. Get Gemini API Key: https://makersuite.google.com/app/apikey
2. Copy your key (starts with "AIza-")
3. Find this line in the script:
   ```python
   api_key = "AIza-YOUR-ACTUAL-API-KEY-HERE"
   ```
4. Replace with your key:
   ```python
   api_key = "AIza-1a2b3c4d5e6f7g8h9i0j1k2l3m4n5o6p7q8r9s"
   ```

---

## 📱 STEP-BY-STEP IN COLAB

### Step 1: Open Google Colab
- Go to: https://colab.research.google.com/
- Click "+ New notebook"

### Step 2: Paste the Script
- Click in the code cell
- Paste the complete script above
- Replace the API key with your actual key

### Step 3: Run the Script
- Press `Shift + Enter` to run
- Watch the progress
- Wait for all 8 steps to complete

### Step 4: Download APK
- A download dialog will appear
- Click to download `app-release.apk`

---

## ⏱️ TIME BREAKDOWN

| Step | Time | Details |
|------|------|----------|
| Install Java | 1 min | JDK 17 installation |
| Android SDK | 2 min | Download + extract |
| Build tools | 2 min | Install components |
| Clone repo | 1 min | Git clone |
| Configure | 1 min | API key setup |
| Build APK | 8 min | Gradle build |
| Verify | 1 min | Check APK |
| Download | 1 min | Download to computer |
| **TOTAL** | **16 min** | **Complete** |

---

## 🐛 TROUBLESHOOTING

### "BUILD FAILED" Error
**Solution:**
1. Verify API key is correct
2. Check internet connection
3. Run in a new Colab session
4. Try again

### "gradlew: command not found"
**Solution:**
```python
!chmod +x ~/Snowflake/gradlew
```

### "Java: command not found"
**Solution:**
```python
!apt-get install -y openjdk-17-jdk
os.environ['JAVA_HOME'] = '/usr/lib/jvm/java-17-openjdk-amd64'
```

### "SDK components not installed"
**Solution:**
Run this in a new cell:
```python
import os
os.environ['ANDROID_SDK_ROOT'] = os.path.expanduser('~/android-sdk')
android_sdk = os.environ['ANDROID_SDK_ROOT']
!{android_sdk}/cmdline-tools/bin/sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
```

### Build Takes Too Long
**This is normal!**
- First build: 8-10 minutes
- Subsequent builds: 3-5 minutes
- Colab sessions last 12 hours
- You can leave and come back

---

## ✅ VERIFICATION

**APK is ready when:**
- ✅ File size is 38-40 MB
- ✅ "BUILD SUCCESSFUL" message appears
- ✅ Download dialog shows up
- ✅ No error messages

---

## 📥 INSTALL ON ANDROID

### Quick Steps:
1. **Download** APK from Colab
2. **Transfer** to Android phone (USB, email, cloud)
3. **Settings** → Security → "Unknown Sources" ON
4. **Tap** APK file
5. **Install** and confirm
6. **Open** Snowflake
7. **Add** API key in Settings
8. **Chat!** 🎉

---

## 🔒 SECURITY

⚠️ **Important:**
- Keep your API key private
- Don't share it publicly
- Don't commit it to GitHub
- Use environment variables in production
- Regenerate if exposed

---

## 💡 ADVANTAGES

✅ **No Installation** - Nothing to download except APK  
✅ **Free** - Uses Google's free tier  
✅ **Fast** - 16 minutes total  
✅ **Easy** - Just copy-paste  
✅ **Anywhere** - Works on any computer  
✅ **Reliable** - Google's infrastructure  
✅ **Offline Ready** - Works even if Colab closes  

---

## 🎯 WHAT'S INCLUDED IN APK

✅ Text chat with Gemini AI  
✅ API key management  
✅ Secure storage  
✅ Material 3 UI  
✅ Dark/Light themes  
✅ Error handling  
✅ No crashes  

---

## 📞 HELP & SUPPORT

- **Issues:** https://github.com/ShauryaPratapSingh070408/Snowflake/issues
- **GitHub:** https://github.com/ShauryaPratapSingh070408/Snowflake
- **Colab Help:** https://colab.research.google.com/

---

## 🎉 YOU'RE READY!

**Next:**
1. ✅ Open Colab
2. ✅ Paste script
3. ✅ Add API key
4. ✅ Run!
5. ✅ Download APK
6. ✅ Install on phone
7. ✅ Chat with AI!

---

**Happy Building!** ❄️

*Snowflake AI v1.0.0 - Phase 1 Complete*