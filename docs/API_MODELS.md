# 🤖 Snowflake AI - Model Configuration Guide

## Available Gemini Models

Based on your Gemini API rate limits for the "Darshana AI" project:

| Model | Category | RPM | TPM | Use Case |
|-------|----------|-----|-----|----------|
| **gemini-2.5-flash** ⭐ | Text-output | 10 | 250K | Primary conversational AI |
| **gemini-2.5-flash-lite** | Text-output | 15 | 250K | Lightweight tasks |
| **gemini-2.5-flash-live** 🔥 | Live API | Unlimited | 1M | Frost Vision real-time |
| **gemini-2.5-flash-tts** | Multi-modal | 3 | 10K | Voice generation |
| **gemini-2.0-flash** | Text-output | 15 | 1M | Long conversations |
| **gemini-2.0-flash-lite** | Text-output | 30 | 1M | High-frequency requests |
| **gemma-3-12b** | Other | 30 | 15K | On-device inference |
| **gemma-3-27b** | Other | 30 | 15K | Complex reasoning |

## Model Selection Strategy

### Snowflake's Intelligent Routing

```kotlin
val modelRouter = ModelRouter(
    quickChat = "gemini-2.5-flash",          // Best quality
    frostVision = "gemini-2.5-flash-live",   // Real-time unlimited
    longConversation = "gemini-2.0-flash",    // High TPM
    batchOperations = "gemini-2.0-flash-lite", // High RPM
    background = "gemini-2.5-flash-lite"      // Efficient
)
```

### Usage Recommendations

#### For Frost Vision (Priority: Performance)
- **Primary**: `gemini-2.5-flash-live`
- **Why**: Unlimited RPM, real-time processing, 1M TPM
- **Perfect for**: Continuous camera feed analysis

#### For Conversations (Priority: Quality)
- **Primary**: `gemini-2.5-flash`
- **Fallback**: `gemini-2.5-flash-lite`
- **Why**: Best quality responses, adequate rate limits

#### For Automation (Priority: Speed)
- **Primary**: `gemini-2.0-flash-lite`
- **Why**: 30 RPM handles multiple quick commands
- **Perfect for**: Batch WhatsApp messages, quick queries

#### For Long Context (Priority: Capacity)
- **Primary**: `gemini-2.0-flash`
- **Why**: 1M TPM for extended conversations
- **Perfect for**: Document summarization, long chats

## Rate Limit Management

### Automatic Throttling

Snowflake automatically:
- Tracks requests per minute
- Switches to alternative model when limit reached
- Queues requests during rate limiting
- Alerts at 80% quota usage

### Manual Configuration

In app settings, you can:
- Choose primary model
- Set fallback models
- Configure auto-switching behavior
- View real-time usage stats

## Cost Optimization

### Free Tier Limits

Gemini API free tier:
- **1,500 requests per day**
- **1 million tokens per month**

### Best Practices

1. **Use Lite Models** for simple queries
2. **Enable Auto Model Selection**
3. **Cache Responses** when appropriate
4. **Minimize Token Usage** with concise prompts
5. **Use Frost Vision Live** efficiently (stream only when needed)

## API Key Setup

### Get API Key

1. Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Sign in with Google account
3. Create new API key
4. Copy key

### Add to Snowflake

#### Method 1: During Build
```properties
# local.properties
GEMINI_API_KEY=AIzaSy...
```

#### Method 2: In App
1. Open Snowflake
2. Settings → API Configuration
3. Paste key
4. Tap "Save & Test"

## Monitoring Usage

### In Snowflake App

Settings → API Configuration → Usage Dashboard:
- Requests today
- Tokens consumed
- Rate limit status
- Time until reset

### In Google AI Studio

[Google AI Studio Dashboard](https://makersuite.google.com/app/apikey):
- Detailed usage metrics
- Historical data
- Quota management

## Troubleshooting

### API Key Invalid
- Regenerate key in Google AI Studio
- Ensure no extra spaces when pasting
- Verify key has Gemini API access

### Rate Limit Exceeded
- Wait for rate limit reset (shown in app)
- Enable auto model switching
- Add backup API key
- Reduce request frequency

### Model Not Available
- Check model is enabled in Google AI Studio
- Verify API key has access to model
- Try alternative model

## Future Models

Snowflake will automatically support new Gemini models as they're released. Check for app updates regularly.

---

**Last Updated**: December 2025  
**API Version**: Gemini 2.5