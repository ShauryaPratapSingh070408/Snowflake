# Contributing to Snowflake AI

Thank you for your interest in contributing to Snowflake! 🌨️

## Getting Started

1. **Fork the repository**
2. **Clone your fork**
   ```bash
   git clone https://github.com/YOUR_USERNAME/Snowflake.git
   cd Snowflake
   ```
3. **Create a branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

## Development Setup

1. Install Android Studio Hedgehog or later
2. Open the project in Android Studio
3. Wait for Gradle sync to complete
4. Get a Gemini API key from [Google AI Studio](https://makersuite.google.com/app/apikey)
5. Add it to `local.properties`:
   ```properties
   GEMINI_API_KEY=your_api_key_here
   ```

## Code Style

- Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Add comments for complex logic
- Keep functions small and focused
- Use Jetpack Compose for UI

## Commit Guidelines

Follow conventional commits:

- `feat:` New feature
- `fix:` Bug fix
- `docs:` Documentation changes
- `style:` Code style changes
- `refactor:` Code refactoring
- `test:` Adding or updating tests
- `chore:` Maintenance tasks

Examples:
```
feat: add Frost Vision continuous mode
fix: resolve memory leak in overlay service
docs: update API configuration guide
```

## Pull Request Process

1. Update README.md if needed
2. Update documentation for new features
3. Ensure all tests pass
4. Create a Pull Request with clear description
5. Wait for review and address feedback

## Areas for Contribution

### High Priority
- ⭐ Frost Vision enhancements
- ⭐ Memory system optimizations
- ⭐ UI/UX improvements
- ⭐ Performance optimizations

### Medium Priority
- 🔧 Additional automation features
- 🔧 Widget implementation
- 🔧 Wear OS companion
- 🔧 Testing coverage

### Good First Issues
- 🌟 UI polish and animations
- 🌟 Documentation improvements
- 🌟 Bug fixes
- 🌟 Translations

## Testing

Before submitting:

```bash
# Run lint
./gradlew lint

# Run tests
./gradlew test

# Build debug APK
./gradlew assembleDebug
```

## Need Help?

- Check existing issues
- Read the documentation
- Ask in discussions
- Reach out to maintainers

## Code of Conduct

Be respectful, inclusive, and collaborative. We're building something amazing together!

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

**Thank you for making Snowflake better!** ❤️