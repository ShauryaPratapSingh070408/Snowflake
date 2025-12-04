# 📲 Download Snowflake APK - Direct Installation Guide

## 🚀 Instant APK Build & Download

### Step 1: Trigger APK Build (Takes 5-10 minutes)

1. **Go to Actions tab**
   - Visit: https://github.com/ShauryaPratapSingh070408/Snowflake/actions

2. **Click "Build APK (Manual)"** on the left sidebar

3. **Click "Run workflow"** button (top right)

4. **Select build type:**
   - **Debug** - For testing (faster build, larger file)
   - **Release** - For distribution (optimized, smaller file)

5. **Click green "Run workflow"** button

6. **Wait for build** (~5-10 minutes)
   - Watch the progress with live logs
   - You'll see a ✅ green checkmark when done

### Step 2: Download APK

#### Option A: From Artifacts (Debug/Release)

1. Click on the completed workflow run
2. Scroll down to "Artifacts" section
3. Click on:
   - `snowflake-debug-apk` (for debug build)
   - `snowflake-release-apk` (for release build)
4. ZIP file downloads automatically
5. Extract the ZIP to get the APK file

#### Option B: From Releases (Release only)

If you built a **release** APK:

1. Go to: https://github.com/ShauryaPratapSingh070408/Snowflake/releases
2. Find the latest release
3. Download `app-release.apk` directly (no extraction needed!)

---

## 📱 Installing APK on Android

### Method 1: Direct Download on Phone

1. **Open this URL on your Android phone:**
   ```
   https://github.com/ShauryaPratapSingh070408/Snowflake/releases/latest
   ```

2. **Tap on `app-release.apk`** to download

3. **Open Downloads** folder

4. **Tap the APK file**

5. **Allow "Install from Unknown Sources"** if prompted:
   - Settings → Security → Unknown Sources → Enable
   - Or: Settings → Apps → Chrome → Install Unknown Apps → Allow

6. **Tap "Install"**

7. **Wait for installation** (~10 seconds)

8. **Tap "Open"** to launch Snowflake!

### Method 2: Transfer from Computer

1. **Download APK on computer** (from Releases or Artifacts)

2. **Connect phone via USB**

3. **Transfer APK** to phone storage:
   - Copy to Downloads folder
   - Or any accessible location

4. **On phone, open File Manager**

5. **Navigate to APK location**

6. **Tap APK file** and follow installation prompts

### Method 3: Using ADB (Advanced)

```bash
# Connect phone via USB with USB debugging enabled
adb install path/to/app-debug.apk
```

---

## 🔧 First-Time Setup After Installation

### 1. Open Snowflake App

Tap the Snowflake icon on your home screen or app drawer.

### 2. Grant Permissions

Snowflake will request:

- ✅ **Camera** - For Frost Vision
  - Tap "While using the app"

- ✅ **Microphone** - For voice commands
  - Tap "While using the app"

- ✅ **Display over other apps** - For floating bubble
  - Tap "Go to Settings"
  - Find Snowflake in the list
  - Toggle ON
  - Press Back

### 3. Enable Accessibility Service

1. App will prompt to enable Accessibility
2. Tap "Enable Accessibility Service"
3. You'll be taken to: **Settings → Accessibility → Snowflake**
4. **Toggle ON**
5. Read the permission description
6. Tap "Allow"
7. Press Back to return to app

### 4. Add Gemini API Key

1. In Snowflake, tap ⚙️ **Settings** icon

2. Scroll to **"API Configuration"**

3. **Get your FREE API key:**
   - Visit on phone or computer: https://makersuite.google.com/app/apikey
   - Sign in with Google
   - Click "Create API Key"
   - Copy the key

4. **Paste API key** in Snowflake settings

5. Tap **"Test API Key"**

6. If successful, tap **"Save"**

### 5. Start Using!

- Say **"Hey Snowflake"** to activate
- Or tap the **floating bubble**
- Try: **"Frost Vision, what am I looking at?"**

---

## 📦 APK Download Links

### Latest Release

🔗 **Direct Download:** https://github.com/ShauryaPratapSingh070408/Snowflake/releases/latest

### All Releases

🔗 **Browse All Versions:** https://github.com/ShauryaPratapSingh070408/Snowflake/releases

### Build Your Own

🔗 **Manual Build:** https://github.com/ShauryaPratapSingh070408/Snowflake/actions/workflows/build-apk-manual.yml

---

## ❓ Troubleshooting

### "App not installed" Error

**Solution 1:** Uninstall old version first
- Settings → Apps → Snowflake → Uninstall
- Then try installing again

**Solution 2:** Enable Unknown Sources
- Settings → Security → Unknown Sources → ON
- Or: Settings → Apps → [Your Browser] → Install Unknown Apps → Allow

**Solution 3:** Check storage space
- Need at least 50MB free
- Delete some apps/files if needed

### "Parse Error" Message

**Cause:** APK file is corrupted

**Solution:**
- Re-download the APK
- Make sure download completed fully
- Try different browser if download keeps failing

### APK Download is Very Slow

**Solution:**
- Use WiFi instead of mobile data
- Try downloading on computer, then transfer
- Use a download manager app

### App Crashes on Launch

**Solution 1:** Clear app data
- Settings → Apps → Snowflake → Storage → Clear Data

**Solution 2:** Reinstall
- Uninstall completely
- Download fresh APK
- Install again

**Solution 3:** Check Android version
- Snowflake requires Android 8.0 or higher
- Settings → About Phone → Android Version

---

## 🔒 Security Notice

### Is This Safe?

✅ **YES!** This APK is built from open-source code in this repository.

- All code is visible and auditable
- Built automatically by GitHub Actions
- No hidden malware or tracking
- MIT License (free and open)

### Why "Unknown Sources" Warning?

Android shows this for ANY app not from Google Play Store. It's just a precaution, not a security threat.

### Verify APK Authenticity

Check the APK was built by official GitHub Actions:

1. Go to: https://github.com/ShauryaPratapSingh070408/Snowflake/actions
2. Find the build that created your APK
3. Verify it shows "Success" with ✅
4. Check the build logs are clean

---

## 📊 APK Specifications

| Property | Debug APK | Release APK |
|----------|-----------|-------------|
| **File Size** | ~15 MB | ~8 MB |
| **Min Android** | 8.0 (API 26) | 8.0 (API 26) |
| **Target Android** | 14 (API 34) | 14 (API 34) |
| **Architecture** | Universal | Universal |
| **Obfuscated** | No | Yes (ProGuard) |
| **Optimized** | No | Yes |
| **Best For** | Testing | Daily use |

---

## 🆘 Need Help?

### Can't Build APK?

1. Check GitHub Actions status
2. Look for error messages in build logs
3. Open an issue: https://github.com/ShauryaPratapSingh070408/Snowflake/issues

### Can't Install APK?

1. Check Android version (need 8.0+)
2. Enable Unknown Sources
3. Ensure enough storage space
4. Try re-downloading APK

### Can't Get It Working?

1. Check you added Gemini API key
2. Verify all permissions granted
3. Try restarting the app
4. Check internet connection

---

## 🎯 Quick Reference

### For Users Who Just Want the App

1. **On your Android phone, visit:**
   ```
   https://github.com/ShauryaPratapSingh070408/Snowflake/releases/latest
   ```

2. **Download `app-release.apk`**

3. **Install and enjoy!**

### For Developers Who Want Latest Build

1. **Visit Actions:**
   ```
   https://github.com/ShauryaPratapSingh070408/Snowflake/actions/workflows/build-apk-manual.yml
   ```

2. **Click "Run workflow"**

3. **Wait 5-10 minutes**

4. **Download from Artifacts**

---

**Happy Snowflaking! 🌨️**