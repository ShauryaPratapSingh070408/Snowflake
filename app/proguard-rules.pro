# Snowflake AI ProGuard Rules

# Keep BuildConfig
-keep class com.shaurya.snowflake.BuildConfig { *; }

# Keep Gemini SDK
-keep class com.google.ai.client.generativeai.** { *; }

# Keep Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Keep Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

# Kotlin Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

# Keep data classes
-keep class com.shaurya.snowflake.data.** { *; }
-keep class com.shaurya.snowflake.domain.model.** { *; }

# Retrofit
-keepattributes Signature
-keepattributes Exceptions
-keep class retrofit2.** { *; }

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }

# Compose
-dontwarn androidx.compose.**
-keep class androidx.compose.** { *; }