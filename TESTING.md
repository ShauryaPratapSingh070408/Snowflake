# 🧪 SNOWFLAKE AI - AUTOMATED TESTING GUIDE

## 📋 Overview

This document describes the automated testing suite for Snowflake AI Phase 1.

---

## 🎯 Test Coverage

### **Unit Tests** (Local JVM)
- ✅ `GeminiRepositoryTest` - API repository logic
- ✅ `PreferencesManagerTest` - Data storage
- ✅ `ChatViewModelTest` - Chat screen business logic
- ✅ `SettingsViewModelTest` - Settings screen business logic

### **UI Tests** (Android Instrumented)
- ✅ `ChatScreenTest` - Chat UI components
- ✅ `SettingsScreenTest` - Settings UI components
- ✅ `NavigationTest` - Tab and screen navigation

---

## 🚀 Running Tests

### **1. Run All Tests (Command Line)**

```bash
# Unit tests only (fast)
./gradlew test

# Instrumented tests only (requires emulator/device)
./gradlew connectedAndroidTest

# All tests
./gradlew test connectedAndroidTest
```

### **2. Run Tests in Android Studio**

#### **Unit Tests:**
1. Right-click on `app/src/test/` folder
2. Select "Run Tests in 'test'"
3. View results in "Run" panel

#### **Instrumented Tests:**
1. Start an emulator or connect a device
2. Right-click on `app/src/androidTest/` folder
3. Select "Run Tests in 'androidTest'"
4. View results in "Run" panel

### **3. Run Tests via GitHub Actions**

1. Push code to `main` branch
2. Go to: https://github.com/ShauryaPratapSingh070408/Snowflake/actions
3. Click "Run Automated Tests" workflow
4. View test results in workflow run

---

## 📊 Test Reports

### **Local Reports Location:**

```
Unit Tests:
app/build/reports/tests/testDebugUnitTest/index.html

Instrumented Tests:
app/build/reports/androidTests/connected/index.html
```

### **GitHub Actions Reports:**

Download from workflow artifacts:
- `unit-test-reports`
- `instrumented-test-reports`

---

## 🧪 Test Details

### **ChatViewModelTest (8 tests)**

| Test | What It Checks |
|------|----------------|
| `updateInputText updates input state` | Text input updates correctly |
| `sendMessage with blank text does nothing` | Empty messages rejected |
| `sendMessage adds user message to list` | User messages appear |
| `sendMessage clears input after sending` | Input cleared after send |
| `sendMessage sets loading state` | Loading indicator works |
| `sendMessage adds AI response` | AI responses received |
| `sendMessage handles error gracefully` | Errors don't crash app |
| `multiple messages maintain order` | Messages in correct order |

### **SettingsViewModelTest (7 tests)**

| Test | What It Checks |
|------|----------------|
| `saveApiKey with empty key shows error` | Empty validation works |
| `saveApiKey with invalid prefix shows error` | Prefix validation works |
| `saveApiKey with valid key succeeds` | Valid keys save |
| `saveApiKey trims whitespace` | Whitespace removed |
| `saveApiKey sets loading state` | Loading indicator works |
| `saveApiKey handles repository error` | Errors handled gracefully |

### **ChatScreenTest (5 tests)**

| Test | What It Checks |
|------|----------------|
| `displaysWelcomeMessage_whenNoMessages` | Welcome screen shows |
| `settingsButton_isClickable` | Settings button works |
| `inputField_acceptsText` | Text input works |
| `sendButton_isDisabled_whenInputEmpty` | Validation works |
| `sendButton_isEnabled_whenInputHasText` | Button enables correctly |

### **SettingsScreenTest (6 tests)**

| Test | What It Checks |
|------|----------------|
| `displaysTitle` | Title shows correctly |
| `backButton_isClickable` | Back button works |
| `apiKeyInput_acceptsText` | API key input works |
| `saveButton_isDisabled_whenInputEmpty` | Validation works |
| `displaysInstructions` | Instructions visible |
| `toggleVisibility_works` | Show/hide works |

### **NavigationTest (3 tests)**

| Test | What It Checks |
|------|----------------|
| `switchBetweenTabs` | Tab switching works |
| `openSettings_fromChat` | Settings opens |
| `backFromSettings_toChat` | Back navigation works |

---

## 📈 Expected Results

### **Target Metrics:**

```
Unit Tests:
  Total: 20+
  Pass Rate: 100%
  Duration: < 10 seconds

Instrumented Tests:
  Total: 14+
  Pass Rate: 100%
  Duration: < 2 minutes

Code Coverage:
  ViewModels: > 80%
  Repository: > 70%
  UI Screens: > 60%
```

---

## 🔧 Troubleshooting

### **Issue: Tests fail with "No API key"**
**Solution:** Tests use mocks, shouldn't need real API key. Check mock setup.

### **Issue: Instrumented tests timeout**
**Solution:** Ensure emulator is running and unlocked.

### **Issue: "Module not found" errors**
**Solution:** Run `./gradlew clean build` first.

### **Issue: Compose test failures**
**Solution:** Ensure test device has API 21+.

---

## 🎯 Adding New Tests

### **1. Unit Test Template:**

```kotlin
@Test
fun `test_name_describes_what_it_tests`() = runTest {
    // Given: Setup test conditions
    
    // When: Perform action
    
    // Then: Verify result
    assertTrue(result)
}
```

### **2. UI Test Template:**

```kotlin
@Test
fun componentName_behaviorToTest() {
    // Given: Set up UI
    composeTestRule.setContent {
        SnowflakeTheme {
            ScreenUnderTest()
        }
    }
    
    // When: Perform UI action
    composeTestRule.onNodeWithText("Button").performClick()
    
    // Then: Verify UI state
    composeTestRule.onNodeWithText("Result").assertIsDisplayed()
}
```

---

## 📝 Test Checklist

Before marking Phase 1 as complete:

- [ ] All unit tests pass (100%)
- [ ] All instrumented tests pass (100%)
- [ ] No flaky tests (run 3x to confirm)
- [ ] Test coverage > 70%
- [ ] CI/CD pipeline green
- [ ] Test reports generated
- [ ] No TODO/FIXME in tests
- [ ] All edge cases covered

---

## 🚀 Continuous Integration

Tests run automatically on:
- ✅ Every push to `main`
- ✅ Every pull request
- ✅ Manual trigger via Actions tab

Pipeline: `.github/workflows/run-tests.yml`

---

## 📊 Current Status

**Phase 1 Test Suite:**
- ✅ Unit Tests: 20 tests created
- ✅ UI Tests: 14 tests created  
- ✅ CI/CD: GitHub Actions configured
- ⏳ Coverage Report: Pending first run
- ⏳ Performance Tests: Phase 2

**Next Steps:**
1. Run test suite
2. Fix any failures
3. Add missing test cases
4. Generate coverage report
5. Mark Phase 1 complete

---

## 📞 Support

For test-related issues:
1. Check this documentation
2. Review test failure logs
3. Run tests locally to debug
4. Check GitHub Actions for CI failures

---

**Last Updated:** December 7, 2025  
**Test Suite Version:** 1.0.0  
**Phase:** 1 (Foundation)
